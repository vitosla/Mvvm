package com.vitos.mvvm.ui;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;

import com.vitos.mvvm.R;

public class MainActivity extends LifecycleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_frame, new UserListFragment())
                .commit();
    }
}
