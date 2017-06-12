package com.vitos.mvvm.di.modules;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.vitos.mvvm.db.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Victor on 05.06.2017.
 */

@Module
public class ContextModule {

    Context mContext;

    public ContextModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return mContext;
    }

    @Singleton
    @Provides
    AppDatabase provideAppDatabase (){
        return Room.databaseBuilder(mContext, AppDatabase.class, "users").build();
    }
}
