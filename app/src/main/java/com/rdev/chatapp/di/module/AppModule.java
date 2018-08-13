package com.rdev.chatapp.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.rdev.chatapp.db.AppDatabase;
import com.rdev.chatapp.db.AppDbChatAppHelper;
import com.rdev.chatapp.db.DBChatAppHelper;
import com.rdev.chatapp.db.DbsInfo;
import com.rdev.chatapp.db.dao.ChatAppDao;
import com.rdev.chatapp.preferences.AppPreferencesHelper;
import com.rdev.chatapp.preferences.PreferencesHelper;
import com.rdev.chatapp.preferences.PreferencesInfo;
import com.rdev.chatapp.repository.AppDataManager;
import com.rdev.chatapp.repository.DataManager;
import com.rdev.chatapp.utils.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ritadacostaferreira on 11/08/18.
 */

//Providenciar certos modulos para a app. Certos objetos podem injetados em certas coisas como actividades.
//vou provindenciar o contexto.
    //Tenho varios modulos, aqui tenho coisas comuns em toda a appl, como o contexto. Digo as coisas q vao estar disponiveis em qq parte.
@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    @DbsInfo.DbInfo
    AppDatabase provideAppDatabase(Application app) {
        return Room.databaseBuilder(app, AppDatabase.class,"chatApp.db").allowMainThreadQueries().build();
    }
    @Singleton
    @Provides
    ChatAppDao provideLocalCoinDao(AppDatabase db) {
        return db.chatAppDao();
    }

    @Provides
    @Singleton
    DBChatAppHelper provideDbHelper(AppDbChatAppHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @PreferencesInfo.PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREFERENCE_NAME;
    }
    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

}
