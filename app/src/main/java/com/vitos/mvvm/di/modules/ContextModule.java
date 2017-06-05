package com.vitos.mvvm.di.modules;

import android.content.Context;

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
}
