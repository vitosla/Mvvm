package com.vitos.mvvm.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.vitos.mvvm.MvvmApp;
import com.vitos.mvvm.api.Api;
import com.vitos.mvvm.api.BaseSingleObserver;
import com.vitos.mvvm.api.repo.RepositoryFactory;
import com.vitos.mvvm.models.User;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Victor on 05.06.2017.
 */

public class UserListViewModel extends ViewModel {

    private Api mApi;
    private MutableLiveData<List<User>> mUsers;

    public UserListViewModel() {
        mApi = MvvmApp.getAppComponent().getApi();
        mApi.setRepositoryFactory(new RepositoryFactory());
    }

    public LiveData<List<User>> getUsers(){
        if (mUsers == null){
            mUsers = new MutableLiveData<>();
            mApi.getAllUsers()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseSingleObserver<List<User>>() {
                        @Override
                        public void onSingleSuccess(List<User> data) {
                            mUsers.setValue(data);
                        }
                    });
        }
        return mUsers;
    }
}
