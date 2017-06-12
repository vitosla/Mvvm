package com.vitos.mvvm.ui;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.vitos.mvvm.R;
import com.vitos.mvvm.ui.adapters.UserListAdapter;
import com.vitos.mvvm.viewmodels.UserListViewModel;

/**
 * Created by Victor on 05.06.2017.
 */

public class UserListFragment extends LifecycleFragment {

    private UserListViewModel mViewModel;

    private ListView mListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);
        mListView = (ListView) view.findViewById(R.id.lview);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(getActivity()).get(UserListViewModel.class);
        mViewModel.getUsers().observe(this, users -> {
            UserListAdapter adapter = new UserListAdapter(users);
            mListView.setAdapter(adapter);

            mListView.setOnItemClickListener((parent, view, position, id) -> {
                //TODO
            });
        });
    }
}
