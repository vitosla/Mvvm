package com.vitos.mvvm.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Victor on 07.06.2017.
 */

@Dao
public interface UserDao {

    @Insert(onConflict = REPLACE)
    void insertAllUsers(List<UserEntity> users);

    @Query("DELETE FROM users")
    void deleteAllUser();

    @Query("select * from users")
    Flowable<List<UserEntity>> getAllUsers();


}
