package com.vitos.mvvm.api;

import android.graphics.Bitmap;

import com.vitos.mvvm.api.repo.IRepositoryFactory;
import com.vitos.mvvm.events.SuccessfulUserUpdateEvent;
import com.vitos.mvvm.models.User;
import com.vitos.mvvm.models.repo.IUserRepository;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Victor on 05.06.2017.
 */

public class Api {

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private IRepositoryFactory mRepositoryFactory;


    public void setRepositoryFactory(IRepositoryFactory repositoryFactory) {
        this.mRepositoryFactory = repositoryFactory;
    }

    public void updateUser(final User user) {
        IUserRepository repository = mRepositoryFactory.getUserRepository();
        mCompositeDisposable
                .add(repository.updateUser(user)
                        .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new BaseCompletableObserver() {
                    @Override
                    public void onSuccess() {
                        EventBus.getDefault().post(new SuccessfulUserUpdateEvent(user));
                    }
                }));
    }

    public Single<User> getUser(String id){
        final IUserRepository repository = mRepositoryFactory.getUserRepository();
        return repository.getUser(id);
    }

    public Single<List<User>> getAllUsers(){
        final IUserRepository repository = mRepositoryFactory.getUserRepository();
        return repository.getAllUsers();
    }

    public void postImage(String id, Bitmap bitmap) {
        IUserRepository repository = mRepositoryFactory.getUserRepository();
        mCompositeDisposable
                .add(repository.postImage(id, bitmap)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new BaseCompletableObserver() {
                            @Override
                            public void onSuccess() {
                                //TODO
                            }
                        }));
    }

    public void cancelAll() {
        if (mCompositeDisposable!=null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
        mCompositeDisposable = new CompositeDisposable();
    }
}
