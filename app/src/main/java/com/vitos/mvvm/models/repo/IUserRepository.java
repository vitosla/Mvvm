package com.vitos.mvvm.models.repo;

import android.arch.lifecycle.LiveData;
import android.graphics.Bitmap;

import com.vitos.mvvm.models.User;

import java.util.List;

import rx.Observable;

/**
 * Created by Victor on 05.06.2017.
 */

public interface IUserRepository {

    Observable<Void> updateUser(User user);

    LiveData<User> getUser(String id);

    LiveData<List<User>> getAllUsers();

    Observable<Void> postImage(String id, Bitmap bitmap);
}
