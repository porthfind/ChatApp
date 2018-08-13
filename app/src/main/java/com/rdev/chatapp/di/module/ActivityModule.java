package com.rdev.chatapp.di.module;

import com.rdev.chatapp.ui.main.MainActivityModule;
import com.rdev.chatapp.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by ritadacostaferreira on 11/08/18.
 */

//onde vai ser injectado- apenas na class Main
@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = {MainActivityModule.class})
    abstract MainActivity contributeMainActivity();
}
