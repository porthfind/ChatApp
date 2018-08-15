package com.rdev.chatapp.repository;

import android.arch.lifecycle.LiveData;

import com.rdev.chatapp.api.ApiResponse;
import com.rdev.chatapp.db.Message;
import com.rdev.chatapp.db.User;
import com.rdev.chatapp.vo.Conversation;

import java.util.List;

/**
 * Created by ritadacostaferreira on 11/08/18.
 */

public interface DataManager {
    boolean isFirstRun();
    void setFirstRun();
    LiveData<ApiResponse<Conversation>> getConversation();
    LiveData<List<Message>> get20Message(int offset);

    LiveData<List<User>> findAllUsers();
    LiveData<List<Message>> findAllMessages();
    void saveUsers(List<User> users);
    void saveMessages(List<Message> messages);
    LiveData<Boolean> saveValues();

}
