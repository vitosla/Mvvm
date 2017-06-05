package com.vitos.mvvm.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.vitos.mvvm.MvvmApp;
import com.vitos.mvvm.api.Api;
import com.vitos.mvvm.api.repo.RepositoryFactory;
import com.vitos.mvvm.models.User;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Victor on 05.06.2017.
 */

public class UserListViewModel extends ViewModel {

    Api mApi;

    public UserListViewModel() {
        mApi = MvvmApp.getAppComponent().getApi();
        mApi.setRepositoryFactory(new RepositoryFactory());
    }

    public Flowable<List<User>> getUsers(){
        return mApi.getAllUsers();
    }

    public Flowable<User> getUser(String id){
        return mApi.getUser(id);
    }

}
