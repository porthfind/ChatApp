package com.rdev.chatapp.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rdev.chatapp.api.ApiResponse;
import com.rdev.chatapp.api.MainApiHelper;
import com.rdev.chatapp.db.DBChatAppHelper;
import com.rdev.chatapp.db.Message;
import com.rdev.chatapp.db.User;
import com.rdev.chatapp.preferences.PreferencesHelper;
import com.rdev.chatapp.utils.AbsentLiveData;
import com.rdev.chatapp.vo.Attachment;
import com.rdev.chatapp.vo.CardViewItem;
import com.rdev.chatapp.vo.Conversation;

import java.lang.reflect.Type;
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

    /*@Override
    public LiveData<List<Message>> get20Message(int offset){
        return mDBChatAppHelper.get20Message(offset);
    }*/

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
    public LiveData<List<Message>> getAllMessages(){
        return mDbHelper.findAllMessages();
    }

    @Override
    public LiveData<List<CardViewItem>> findAllMessages() {

        LiveData<List<CardViewItem>> result = AbsentLiveData.create();
        final MutableLiveData<List<CardViewItem>> updatedResult = new MediatorLiveData<>();
        List<CardViewItem> tmp = new ArrayList<>();

        //This method waits until it has the data to process
        result = Transformations.switchMap(getAllMessages(), dbResponse->{
            List<Attachment> attachments = new ArrayList<>();

            if(!dbResponse.isEmpty()){

                for(int i=0; i<dbResponse.size(); i++){
                    CardViewItem item = new CardViewItem();

                    item.setId(dbResponse.get(i).getId());
                    item.setContent(dbResponse.get(i).getContent());
                    item.setUserId(dbResponse.get(i).getUserId());


                    if(dbResponse.get(i).getAttachments().compareToIgnoreCase("null")!=0) //Has attachments
                    {

                        //transforms the srting into list of attachments
                        Type listType = new TypeToken<List<Attachment>>(){}.getType();
                        attachments = new Gson().fromJson(dbResponse.get(i).getAttachments(), listType);

                        for(int j=0; j<attachments.size() ; j++) {

                            item.setTitulo(attachments.get(j).getTitle());
                            item.setUrl(attachments.get(j).getUrl());
                            item.setThumbnailurl(attachments.get(j).getThumbnailUrl());
                        }
                    }
                    tmp.add(item);
                }
                updatedResult.postValue(tmp);
            }
                return updatedResult;
            });
        return result;



    }

    @Override
    public LiveData<Boolean> getCount(){

        LiveData<Boolean> result = AbsentLiveData.create();
        final MutableLiveData<Boolean> updatedResult = new MediatorLiveData<>();

        result = Transformations.switchMap(getAllMessages(), allMessagesResponse->{

            updatedResult.postValue(false);
            return updatedResult;
        });
        return result;

    }
}
