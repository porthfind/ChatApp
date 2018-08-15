package com.rdev.chatapp.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;

import com.google.gson.Gson;
import com.rdev.chatapp.api.ApiResponse;
import com.rdev.chatapp.api.MainApiHelper;
import com.rdev.chatapp.db.DBChatAppHelper;
import com.rdev.chatapp.db.Message;
import com.rdev.chatapp.db.User;
import com.rdev.chatapp.preferences.PreferencesHelper;
import com.rdev.chatapp.utils.AbsentLiveData;
import com.rdev.chatapp.vo.Conversation;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by ritadacostaferreira on 11/08/18.
 */

public class AppDataManager implements DataManager {

    private MainApiHelper mMainApiHelper;
    private DBChatAppHelper mDBChatAppHelper;
    private PreferencesHelper mPreferenceHelper;
    private DBChatAppHelper mDbHelper;


    //injectar os diferentes modulos
    @Inject
    public AppDataManager(MainApiHelper mainApiHelper, PreferencesHelper preferencesHelper, DBChatAppHelper dbChatAppHelper) {
        this.mMainApiHelper = mainApiHelper;
        this.mPreferenceHelper = preferencesHelper;
        this.mDbHelper = dbChatAppHelper;
    }


    @Override
    public boolean isFirstRun() {
        return mPreferenceHelper.isFirstRun();
    }

    @Override
    public void setFirstRun() {
         mPreferenceHelper.setIsFirstRun();
    }

    @Override
    public LiveData<ApiResponse<Conversation>> getConversation() {
        return mMainApiHelper.getConversation();
    }

    @Override
    public LiveData<List<Message>> get20Message(int offset){
        return mDBChatAppHelper.get20Message(offset);
    }

    //read from api and save on db - transforms the data from api to data from db
    public LiveData<Boolean> saveValues()
    {
        LiveData<Boolean> result = AbsentLiveData.create();
        final MutableLiveData<Boolean> updatedResult = new MediatorLiveData<>();

        //This method waits until it has the data to process
        result = Transformations.switchMap(getConversation(), apiResponse->{
            ArrayList<Message> list = new ArrayList<>();
            ArrayList<User> userList = new ArrayList<>();


            if(apiResponse.isSuccessful())
            {
                if(apiResponse.body != null)
                {
                    List<com.rdev.chatapp.vo.Message> tmpList = apiResponse.body.getMessages();
                    for(int i=0; i<tmpList.size(); i++)
                    {
                        Message message = new Message();
                        message.setId(tmpList.get(i).getId());
                        message.setUserId(tmpList.get(i).getUserId());
                        message.setContent(tmpList.get(i).getContent());
                        Gson gson = new Gson();
                        String json = gson.toJson(tmpList.get(i).getAttachments());
                        message.setAttachments(json);
                        list.add(message);
                    }

                    List<com.rdev.chatapp.vo.User> tmpList1 = apiResponse.body.getUsers();

                    for(int i=0; i<tmpList1.size(); i++)
                    {
                        User user = new User(tmpList1.get(i).getId(),tmpList1.get(i).getName(),tmpList1.get(i).getAvatarId());
                        userList.add(user);
                    }
                    saveMessages(list);
                    saveUsers(userList);
                    updatedResult.postValue(true);
                }

            }
            else
            {
                updatedResult.postValue(false);
                Timber.d("=== error");
            }



            return updatedResult;

        });
        return result;
    }

    @Override
    public void saveUsers(List<User> users) {
        mDbHelper.saveUsers(users);
    }

    @Override
    public void saveMessages(List<Message> messages) {
        mDbHelper.saveMessages(messages);
    }

    @Override
    public LiveData<List<User>> findAllUsers() {
        return mDbHelper.findAllUsers();
    }

    @Override
    public LiveData<List<Message>> findAllMessages() {
        return mDbHelper.findAllMessages();

        /*
        *  receber as mensagens
        *  for (mensage)
        *  [ CONVERSAO ---UserSimple userObject = gson.fromJson(userJson, UserSimple.class);
        *   for(attachments)
        *  ]
        *  devolve estrutura nova.
        * */
    }
}
