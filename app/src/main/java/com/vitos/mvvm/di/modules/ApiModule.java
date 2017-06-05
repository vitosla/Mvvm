package com.vitos.mvvm.di.modules;

import com.vitos.mvvm.api.Api;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Victor on 05.06.2017.
 */

@Module
public class ApiModule {

    @Provides
    @Singleton
    Api provideApi(){
        return new Api();
    }
}
