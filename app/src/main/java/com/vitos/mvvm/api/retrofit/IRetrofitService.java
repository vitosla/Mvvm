package com.vitos.mvvm.api.retrofit;

import com.vitos.mvvm.models.UserDTO;

import java.util.List;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by Victor on 05.06.2017.
 */

public interface IRetrofitService {

    @POST("/api/users")
    Flowable<Void> updateUser(
            @Body UserDTO value
    );

    @GET("/api/users/{id}")
    Flowable<UserDTO> getUser(@Path("id") String id);

    @GET("/api/users")
    Flowable<List<UserDTO>> getAllUsers();

    @Multipart
    @POST("/api/users/{id}/image")
    Flowable<Void> postImage(@Path("id") String id, @Part MultipartBody.Part filePart);
}
