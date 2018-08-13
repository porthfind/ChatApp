package com.rdev.chatapp.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.databinding.adapters.Converters;

import com.rdev.chatapp.ChatApp;
import com.rdev.chatapp.db.dao.ChatAppDao;

/**
 * Created by ritadacostaferreira on 12/08/18.
 */

@Database(entities = {Message.class, User.class}, version = 1)
@TypeConverters({Converter.class})
public abstract class AppDatabase extends RoomDatabase {
    abstract public ChatAppDao chatAppDao();
}
