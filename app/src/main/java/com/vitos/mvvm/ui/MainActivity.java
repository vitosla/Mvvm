package com.vitos.mvvm.ui;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.vitos.mvvm.R;

public class MainActivity extends LifecycleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Удаляю и добавляю фрагмент, чтобы посмотреть как работает ViewModel
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_frame);
        if (fragment == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_frame, new UserListFragment())
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .remove(getSupportFragmentManager().findFragmentById(R.id.fragment_frame))
                    .add(R.id.fragment_frame, new UserListFragment())
                    .commit();
        }
    }
}
