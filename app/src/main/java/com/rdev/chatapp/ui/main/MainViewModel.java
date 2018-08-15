package com.rdev.chatapp.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.Nullable;

import com.rdev.chatapp.api.ApiResponse;
import com.rdev.chatapp.db.User;
import com.rdev.chatapp.repository.DataManager;
import com.rdev.chatapp.ui.base.BaseViewModel;
import com.rdev.chatapp.utils.AbsentLiveData;
import com.rdev.chatapp.vo.Conversation;
import com.rdev.chatapp.db.Message;


import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

//ViewModel
public class MainViewModel extends BaseViewModel<MainNavigator> {

    private final DataManager mDataManager;
    LiveData<List<User>>  user;
    LiveData<List<Message>> mMessage;

    //AbsentLiveData LiveData null object
    public MainViewModel(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
        mMessage = AbsentLiveData.create();
    }


    public LiveData<List<User>> getUser() {
        return user;
    }

    public void setUser(LiveData<List<User>> user) {
        this.user = user;
    }


    public LiveData<List<Message>> getmMessage() {
        return mMessage;
    }

    public void setmMessage(LiveData<List<Message>> mMessage) {
        this.mMessage = mMessage;
    }





    //verifies if is the first time we are running the app
    public LiveData<Boolean> firstTime(){
        if(mDataManager.isFirstRun())
        {
            return mDataManager.saveValues();
        }
        else
        {

            Timber.d("-------------- Is not first read from database ");
            return null;
        }
    }

    //initializes BD
    public void getValuesFromDb()
    {
        user = AbsentLiveData.create();
        user = mDataManager.findAllUsers();
        mMessage = AbsentLiveData.create();
        mMessage = mDataManager.findAllMessages();//get20Message
    }

    public void setisFirstRun(){
        mDataManager.setFirstRun();
    }

    //db - repository
  /*  public LiveData<List<Message>> get20Message(int offset){
        return mDataManager.get20Message(offset);
    }


    public LiveData<List<Message>> get20Messages() {
        return mMessages;
    }*/


}
