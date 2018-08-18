package com.rdev.chatapp.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.rdev.chatapp.utils.AppConstants;

import javax.inject.Inject;

import timber.log.Timber;


public class AppPreferencesHelper implements PreferencesHelper{

    private final SharedPreferences mPrefs;


    @Inject
    public AppPreferencesHelper(Context context,
                                @PreferencesInfo.PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }


    @Override
    public boolean isFirstRun() {
        Timber.d("6");
        return mPrefs.getBoolean(AppConstants.FIRST_RUN, true);
    }

    @Override
    public void setIsFirstRun() {

        Timber.d("7");
        mPrefs.edit().putBoolean(AppConstants.FIRST_RUN, false).apply();
    }
}
