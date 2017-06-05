package com.vitos.mvvm.models.repo;

import android.graphics.Bitmap;

import com.vitos.mvvm.models.User;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Victor on 05.06.2017.
 */

public interface IUserRepository {

    Flowable<Void> updateUser(User user);

    Flowable<User> getUser(String id);

    Flowable<List<User>> getAllUsers();

    Flowable<Void> postImage(String id, Bitmap bitmap);
}
