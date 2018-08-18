package com.rdev.chatapp.ui.main;

import android.arch.lifecycle.ViewModelProvider;


import com.rdev.chatapp.repository.DataManager;
import com.rdev.chatapp.utils.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;



    @Module
    public class MainActivityModule {

        @Provides
        MainViewModel provideStartViewModel(DataManager dataManager) {
            return new MainViewModel(dataManager);
        }

        @Provides
        ViewModelProvider.Factory mainViewModelProvider(MainViewModel mainViewModel) {
            return new ViewModelProviderFactory<>(mainViewModel);
        }

    }
