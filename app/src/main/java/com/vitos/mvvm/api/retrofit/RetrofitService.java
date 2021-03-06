package com.vitos.mvvm.api.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vitos.mvvm.BuildConfig;
import com.vitos.mvvm.models.UserDTO;
import com.vitos.mvvm.tools.DateHelper;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Part;
import retrofit2.http.Path;

//import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
//import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by Victor on 05.06.2017.
 */

public class RetrofitService implements IRetrofitService {

    private final OkHttpClient mClient = new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(new HttpLoggingInterceptor().setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE))
            .build();

    private final Gson mGson = new GsonBuilder()
            .setDateFormat(DateHelper.DATE_PATTERN_yyyy_MM_ddTHH_mm_ss)
            .create();

    private final IRetrofitService mCaller = new Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create(mGson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(mClient)
            .build()
            .create(IRetrofitService.class);

    @Override
    public Completable updateUser(@Body UserDTO value) {
        return mCaller.updateUser(value);
    }

    @Override
    public Maybe<UserDTO> getUser(@Path("id") String id) {
        return mCaller.getUser(id);
    }

    @Override
    public Maybe<List<UserDTO>> getAllUsers() {
        return mCaller.getAllUsers();
    }

    @Override
    public Completable postImage(@Path("id") String id, @Part MultipartBody.Part filePart) {
        return mCaller.postImage(id, filePart);
    }
}
