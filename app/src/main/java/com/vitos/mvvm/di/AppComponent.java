package com.vitos.mvvm.di;

import android.content.Context;

import com.vitos.mvvm.api.Api;
import com.vitos.mvvm.api.retrofit.RetrofitService;
import com.vitos.mvvm.di.modules.ApiModule;
import com.vitos.mvvm.di.modules.ContextModule;
import com.vitos.mvvm.di.modules.RetrofitModule;
import com.vitos.mvvm.models.repo.RemoteUserRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Victor on 05.06.2017.
 */

@Singleton
@Component(modules = {ContextModule.class, RetrofitModule.class, ApiModule.class})
public interface AppComponent {

    Context getContext();
    RetrofitService getRetrofitService();
    Api getApi();

    void inject (RemoteUserRepository remoteUserRepository);
}
