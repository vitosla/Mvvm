package com.vitos.mvvm.api;

import android.graphics.Bitmap;

import com.vitos.mvvm.MvvmApp;
import com.vitos.mvvm.api.repo.IRepositoryFactory;
import com.vitos.mvvm.db.AppDatabase;
import com.vitos.mvvm.db.UserEntity;
import com.vitos.mvvm.db.UserEntityMapper;
import com.vitos.mvvm.events.SuccessfulUserUpdateEvent;
import com.vitos.mvvm.models.User;
import com.vitos.mvvm.models.repo.IUserRepository;

import org.greenrobot.eventbus.EventBus;
import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Victor on 05.06.2017.
 */

public class Api {

    private AppDatabase mDb = MvvmApp.getAppComponent().getDB();
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

    public Maybe<User> getUser(String id){
        final IUserRepository repository = mRepositoryFactory.getUserRepository();
        return repository.getUser(id);
    }

    public Maybe<List<User>> getAllUsers(){
        final IUserRepository repository = mRepositoryFactory.getUserRepository();
        Maybe<List<User>> netSource = repository.getAllUsers();
        Maybe<List<User>> dbEntities = mDb.userDao().getAllUsers()
                .map(userEntities -> new UserEntityMapper().callUser(userEntities))
                .firstElement();
          //      .elementAt(0, new ArrayList<>());

        return Maybe.concat(dbEntities, netSource)
                .takeUntil(users -> !users.isEmpty())
                .lastElement();
//                .filter(users -> !users.isEmpty())
//                .firstElement();

           //     .toObservable();
//                .filter(users -> !users.isEmpty())
//                .first(new ArrayList<>());
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
