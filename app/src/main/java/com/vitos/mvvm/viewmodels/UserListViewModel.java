package com.vitos.mvvm.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.vitos.mvvm.MvvmApp;
import com.vitos.mvvm.api.Api;
import com.vitos.mvvm.api.BaseMaybeObserver;
import com.vitos.mvvm.api.repo.RepositoryFactory;
import com.vitos.mvvm.db.AppDatabase;
import com.vitos.mvvm.db.UserEntity;
import com.vitos.mvvm.db.UserEntityMapper;
import com.vitos.mvvm.models.User;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Victor on 05.06.2017.
 */

public class UserListViewModel extends ViewModel {

    @Inject
    Api mApi;

    @Inject
    AppDatabase mDB;

    private MutableLiveData<List<User>> mUsers;

    public UserListViewModel() {
        MvvmApp.getAppComponent().inject(this);
        mApi.setRepositoryFactory(new RepositoryFactory());
    }

    public LiveData<List<User>> getUsers(){

        if (mUsers == null){
            mUsers = new MutableLiveData<>();
            mApi.getAllUsers()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseMaybeObserver<List<User>>() {
                        @Override
                        public void onMaybeSuccess(List<User> data) {
                            mUsers.setValue(data);
                            updateDB(data);
                        }
                    });
        }

        return mUsers;
    }

    private void updateDB(List<User> data){

        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            mDB.userDao().deleteAllUser();
            List<UserEntity> users = new UserEntityMapper().callUserEntity(data);
            mDB.userDao().insertAllUsers(users);
        });
    }
}
