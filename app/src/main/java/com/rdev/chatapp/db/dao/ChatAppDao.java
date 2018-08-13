package com.rdev.chatapp.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.rdev.chatapp.db.Message;
import com.rdev.chatapp.db.User;

import java.util.List;

/**
 * Created by ritadacostaferreira on 12/08/18.
 */

@Dao
public interface ChatAppDao {

    //INSERT

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Message message);


    //SELECT

    @Query("SELECT * FROM Message WHERE id = :id")
    LiveData<Message> findMessageById(long id);

    @Query("SELECT * FROM Message")
    List<Message> findAllMessages();

    @Query("SELECT * FROM User WHERE id = :id")
    LiveData<User> findUserById(long id);

    @Query("SELECT * FROM User")
    List<User> findAllUsers();




    //DELETE
    @Query("DELETE FROM Message")
    public void deleteAllMessages();

    @Query("DELETE FROM User")
    public void deleteAllUsers();

    @Delete
    public void deleteMessage (Message message);

    @Delete
    public void deleteUser (User user);
}
