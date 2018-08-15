package com.rdev.chatapp.db;

import android.arch.lifecycle.LiveData;

import com.rdev.chatapp.db.dao.ChatAppDao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ritadacostaferreira on 12/08/18.
 */

@Singleton
public class AppDbChatAppHelper implements  DBChatAppHelper {

    private final AppDatabase mAppDatabase;
    private final ChatAppDao mChatAppDao;

    @Inject
    public AppDbChatAppHelper(@DbsInfo.DbInfo AppDatabase appDatabase)
    {
        mAppDatabase = appDatabase;
        mChatAppDao = mAppDatabase.chatAppDao();
    }

    //insert
    @Override
    public void saveMessages(List<Message> messages) {
        mChatAppDao.insertMessages(messages);
    }

    @Override
    public void saveUsers(List<User> users) {
        mChatAppDao.insertUsers(users);
    }


    //delete
    @Override
    public void deleteMessage(Message message) {
        mChatAppDao.deleteMessage(message);
    }

    @Override
    public void deleteUser(User user) {
        mChatAppDao.deleteUser(user);
    }

    @Override
    public void deleteAllMessages() {
        mChatAppDao.deleteAllMessages();
    }

    @Override
    public void deleteAllUsers() {
        mChatAppDao.deleteAllUsers();
    }

    //select
    @Override
    public LiveData<Message> findMessageById(long id){
        return mChatAppDao.findMessageById(id);
    }

    @Override
    public LiveData<User> findUserById(long id){
        return mChatAppDao.findUserById(id);
    }

    @Override
    public LiveData<List<Message> >findAllMessages(){
        return mChatAppDao.findAllMessages();
    }

    @Override
    public LiveData<List<User> >findAllUsers(){
        return mChatAppDao.findAllUsers();
    }

    @Override
    public LiveData<List<Message>>get20Message(int offset){
        return mChatAppDao.get20Message(offset);
    }

}
