package com.vitos.mvvm.ui;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.vitos.mvvm.models.User;
import com.vitos.mvvm.viewmodels.UserListViewModel;

import java.util.List;

/**
 * Created by Victor on 05.06.2017.
 */

public class UserListFragment extends LifecycleFragment {

    private UserListViewModel mViewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {

            }
        });
    }
}
