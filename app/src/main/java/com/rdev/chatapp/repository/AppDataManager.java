package com.rdev.chatapp.repository;

import android.arch.lifecycle.LiveData;

import com.rdev.chatapp.api.ApiResponse;
import com.rdev.chatapp.api.MainApiHelper;
import com.rdev.chatapp.preferences.PreferencesHelper;
import com.rdev.chatapp.vo.Conversation;

import javax.inject.Inject;

/**
 * Created by ritadacostaferreira on 11/08/18.
 */

public class AppDataManager implements DataManager {

    private MainApiHelper mMainApiHelper;
    //private DBVenuesHelper mDBVenuesHelper;
    private PreferencesHelper mPreferenceHelper;


    //injectar os diferentes modulos
    @Inject
    public AppDataManager(MainApiHelper mainApiHelper, PreferencesHelper preferencesHelper) {
        this.mMainApiHelper = mainApiHelper;
        this.mPreferenceHelper = preferencesHelper;
    }


    @Override
    public boolean isFirstRun() {
        return mPreferenceHelper.isFirstRun();
    }

    @Override
    public void setFirstRun(boolean first) {
         mPreferenceHelper.setIsFirstRun(first);
    }

    @Override
    public LiveData<ApiResponse<Conversation>> getConversation() {
        return mMainApiHelper.getConversation();
    }
}
