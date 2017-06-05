package com.vitos.mvvm.di.modules;

import com.vitos.mvvm.api.retrofit.RetrofitService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Victor on 05.06.2017.
 */

@Module
public class RetrofitModule {

    @Provides
    @Singleton
    RetrofitService provideRetrofitService(){
        return new RetrofitService();
    }
}
