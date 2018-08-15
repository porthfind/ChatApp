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
    LiveData<List<Message>> findAllMessages();
    LiveData<List<User>> findAllUsers();
    void saveMessages(List<Message> messages);
    void saveUsers(List<User> users);
    LiveData<List<Message>>get20Message(int offset);
}
