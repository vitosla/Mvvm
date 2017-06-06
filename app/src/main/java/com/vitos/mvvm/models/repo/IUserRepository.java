package com.vitos.mvvm.models.repo;

import android.graphics.Bitmap;

import com.vitos.mvvm.models.User;
import com.vitos.mvvm.models.UserDTO;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by Victor on 05.06.2017.
 */

public interface IUserRepository {

    Completable updateUser(User user);

    Single<User> getUser(String id);

    Single<List<User>> getAllUsers();

    Completable postImage(String id, Bitmap bitmap);
}
