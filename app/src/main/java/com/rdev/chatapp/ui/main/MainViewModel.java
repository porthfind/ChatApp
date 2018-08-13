package com.rdev.chatapp.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;

import com.rdev.chatapp.api.ApiResponse;
import com.rdev.chatapp.repository.DataManager;
import com.rdev.chatapp.ui.base.BaseViewModel;
import com.rdev.chatapp.utils.AbsentLiveData;
import com.rdev.chatapp.vo.Conversation;
import com.rdev.chatapp.vo.Message;
import com.rdev.chatapp.vo.User;


import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;


public class MainViewModel extends BaseViewModel<MainNavigator> {
    private final DataManager mDataManager;


    LiveData<ApiResponse<Conversation>> mConversation;


    //AbsentLiveData objeto vazio de LiveData
    public MainViewModel(DataManager mDataManager) {
        super(mDataManager);
        this.mDataManager = mDataManager;
        mConversation = AbsentLiveData.create();

       




    }

    //network
    public LiveData<ApiResponse<Conversation>> getConversations(){
        return mDataManager.getConversation();
    }

    public LiveData<ApiResponse<Conversation>> getmConversation() {
        return mConversation;
    }

    public void setmConversation(LiveData<ApiResponse<Conversation>> mConversation) {
        this.mConversation = mConversation;
    }






}
