package com.vitos.mvvm.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.vitos.mvvm.models.User;

import java.util.List;

/**
 * Created by Victor on 05.06.2017.
 */

public class UserListViewModel extends ViewModel {

    private LiveData<List<User>> mUsers;

    public LiveData<List<User>> getUsers(){
        return mUsers;
    }
}
