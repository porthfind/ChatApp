package com.rdev.chatapp.db;

import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * Created by ritadacostaferreira on 12/08/18.
 */

public interface DBChatAppHelper {

    void deleteMessage(Message message);
    void deleteUser(User user);
    void deleteAllMessages();
    void deleteAllUsers();
    LiveData<Message> findMessageById(long id);
    LiveData<User> findUserById(long id);
    List<Message> findAllMessages();
    List<User> findAllUsers();
    void saveMessage(Message message);
    void saveUser(User user);
}
