package com.vitos.mvvm.models.repo;

import android.content.Context;
import android.graphics.Bitmap;

import com.vitos.mvvm.MvvmApp;
import com.vitos.mvvm.api.retrofit.RetrofitService;
import com.vitos.mvvm.models.User;
import com.vitos.mvvm.models.UserDTO;
import com.vitos.mvvm.models.UserMapper;
import com.vitos.mvvm.tools.ImageUtils;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Victor on 05.06.2017.
 */

public class RemoteUserRepository implements IUserRepository {

    @Inject
    RetrofitService mRetrofitService;
    @Inject
    Context mAppContext;

    public RemoteUserRepository() {
        MvvmApp.getAppComponent().inject(this);
    }

    @Override
    public Flowable<Void> updateUser(User user) {
        UserDTO value = new UserMapper().map(user);
        return mRetrofitService.updateUser(value);
    }

    @Override
    public Flowable<User> getUser(String id) {
        return mRetrofitService
                .getUser(id)
                .map(userDTO -> new UserMapper().map(userDTO));
    }

    @Override
    public Flowable<List<User>> getAllUsers() {
        return mRetrofitService
                .getAllUsers()
                .map(userDTO -> new UserMapper().call(userDTO));
    }

    @Override
    public Flowable<Void> postImage(String id, Bitmap bitmap) {
        File file = ImageUtils.bitmapToFile(mAppContext, bitmap);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData(
                "file", file.getName(),
                RequestBody.create(MediaType.parse("image/*"), file));

        return mRetrofitService.postImage(id, filePart);
    }
}
