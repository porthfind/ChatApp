package com.rdev.chatapp.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.rdev.chatapp.vo.Attachment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ritadacostaferreira on 12/08/18.
 */

@Entity(tableName = "Message")
public class Message {

    @PrimaryKey
    @NonNull
    private long id;

    @ColumnInfo(name = "userId")
    private long userId;

    @ColumnInfo(name = "content")
    private String content;

    @ColumnInfo(name = "attachments")
    private ArrayList<String> attachments;

    @NonNull
    public long getId() {
        return id;
    }

    public void setId(@NonNull long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(ArrayList<String> attachments) {
        this.attachments = attachments;
    }
}
