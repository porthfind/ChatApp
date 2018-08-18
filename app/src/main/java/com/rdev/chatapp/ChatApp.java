package com.rdev.chatapp;

import android.app.Activity;
import android.app.Application;

import com.rdev.chatapp.di.component.DaggerMainComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import timber.log.Timber;

/**
 * Created by ritadacostaferreira on 11/08/18.
 */


public class ChatApp extends Application implements HasActivityInjector{

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;


        @Override
        public void onCreate() {
            super.onCreate();

            DaggerMainComponent.builder().application(this).build().inject(this);

            if (BuildConfig.DEBUG) {
                Timber.plant(new Timber.DebugTree());
            }

        }


    @Override
    public AndroidInjector<Activity> activityInjector() {
       return activityDispatchingAndroidInjector;
    }
}
