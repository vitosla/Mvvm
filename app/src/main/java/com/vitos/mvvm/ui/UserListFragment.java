package com.vitos.mvvm.ui;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.vitos.mvvm.R;
import com.vitos.mvvm.api.BaseDisposableSubscriber;
import com.vitos.mvvm.events.SuccessfulUserUpdateEvent;
import com.vitos.mvvm.models.User;
import com.vitos.mvvm.ui.adapters.UserListAdapter;
import com.vitos.mvvm.viewmodels.UserListViewModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

import static android.arch.lifecycle.LiveDataReactiveStreams.fromPublisher;

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

//        mViewModel.getUser("3bace808c0f800ce")
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(new BaseDisposableSubscriber<Void>() {
//
//                    @Override
//                    public void postSuccessful(Void user) {
//                        EventBus.getDefault().post(new SuccessfulUserUpdateEvent(user));
//                    }
//                }));

        mViewModel.getUsers()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<List<User>>() {
                    @Override
                    public void onNext(List<User> users) {
                        if (users == null) users = Collections.emptyList();
                        UserListAdapter adapter = new UserListAdapter(users);
                        mListView.setAdapter(adapter);

                        mListView.setOnItemClickListener((parent, view, position, id) -> {
                            //TODO
                        });
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

//        fromPublisher(mViewModel.getUser("3bace808c0f800ce")).observe(this, user -> {
//            List<User> listUser = new ArrayList<>();
//            if (user == null) {
//                listUser = Collections.emptyList();
//            } else {
//                listUser.add(user);
//            }
//            UserListAdapter adapter = new UserListAdapter(listUser);
//            mListView.setAdapter(adapter);
//        });


//        fromPublisher(mViewModel.getUsers()).observe(this, users -> {
//            if (users == null) users = Collections.emptyList();
//            UserListAdapter adapter = new UserListAdapter(users);
//            mListView.setAdapter(adapter);
//        });


    }
}
