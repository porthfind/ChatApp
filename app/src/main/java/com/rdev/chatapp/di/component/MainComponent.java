package com.rdev.chatapp.di.component;

import android.app.Application;

import com.rdev.chatapp.ChatApp;
import com.rdev.chatapp.di.module.ActivityModule;
import com.rdev.chatapp.di.module.AppModule;
import com.rdev.chatapp.di.module.NetModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by ritadacostaferreira on 11/08/18.
 */

//Ponto onde se ligam todas as coisas
@Singleton
@Component(modules = {AndroidInjectionModule.class, NetModule.class, AppModule.class, ActivityModule.class})
public interface MainComponent
{
    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder application(Application application);


        MainComponent build();
    }
    void inject(ChatApp app);
}
