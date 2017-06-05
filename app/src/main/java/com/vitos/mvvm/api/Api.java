package com.vitos.mvvm.api;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.LiveDataReactiveStreams;
import android.graphics.Bitmap;

import com.vitos.mvvm.api.repo.IRepositoryFactory;
import com.vitos.mvvm.events.SuccessfulUserUpdateEvent;
import com.vitos.mvvm.models.User;
import com.vitos.mvvm.models.UserDTO;
import com.vitos.mvvm.models.repo.IUserRepository;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static android.arch.lifecycle.LiveDataReactiveStreams.fromPublisher;

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
                .subscribeWith(new BaseDisposableSubscriber<Void>() {

                    @Override
                    public void postSuccessful(Void data) {
                        EventBus.getDefault().post(new SuccessfulUserUpdateEvent(user));
                    }
                }));
    }

    public Flowable<User> getUser(String id){
        final IUserRepository repository = mRepositoryFactory.getUserRepository();
        return repository.getUser(id);
        //     return Observable.defer(() -> repository.getUser(id));
    }

    public Flowable<List<User>> getAllUsers(){
        final IUserRepository repository = mRepositoryFactory.getUserRepository();
        return repository.getAllUsers();
   //     LiveData<List<UserDTO>> result = fromPublisher(flu);
   //     return null;
        //     return Observable.defer(() -> repository.getUser(id));
    }

    public void postImage(String id, Bitmap bitmap) {
        IUserRepository repository = mRepositoryFactory.getUserRepository();
        mCompositeDisposable
                .add(repository.postImage(id, bitmap)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(aVoid -> {
                            //TODO
                        }));
    }

    public void cancelAll() {
        if (mCompositeDisposable!=null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
        mCompositeDisposable = new CompositeDisposable();
    }
}
