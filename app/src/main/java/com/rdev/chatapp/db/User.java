package com.rdev.chatapp.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by ritadacostaferreira on 12/08/18.
 */

@Entity(tableName = "User")
public class User {

    @PrimaryKey
    @NonNull
    private long id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo (name = "avatarID")
    private String avatarID;


    public User(long id, String name, String avatarID) {
        this.id = id;
        this.name = name;
        this.avatarID = avatarID;
    }

    /*gets & sets*/

    @NonNull
    public long getId() {
        return id;
    }

    public void setId(@NonNull long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarID() {
        return avatarID;
    }

    public void setAvatarID(String avatarID) {
        this.avatarID = avatarID;
    }
}
