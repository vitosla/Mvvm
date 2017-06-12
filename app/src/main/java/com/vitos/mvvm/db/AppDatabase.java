package com.vitos.mvvm.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Victor on 07.06.2017.
 */

@Database(entities = {UserEntity.class}, version = 4)
public abstract class AppDatabase extends RoomDatabase{

    public abstract UserDao userDao();
}
