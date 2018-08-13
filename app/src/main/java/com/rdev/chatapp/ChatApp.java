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

//a tua ap vai usar di.
    //vou usar di nas activities
    //vou injector em toda a app
    //overrite androidinjec: esta disponivel p toda a app este dispatching Andoird
    //class global
    //injector o contexto da app em toda a app (extends Application

public class ChatApp extends Application implements HasActivityInjector{ //tem depency injector

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    //diz onde vai ser injectado
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
