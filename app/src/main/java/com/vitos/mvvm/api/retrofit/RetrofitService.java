package com.vitos.mvvm.api.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vitos.mvvm.BuildConfig;
import com.vitos.mvvm.models.UserDTO;
import com.vitos.mvvm.tools.DateHelper;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Part;
import retrofit2.http.Path;

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
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(mClient)
            .build()
            .create(IRetrofitService.class);

    @Override
    public Flowable<Void> updateUser(@Body UserDTO value) {
        return mCaller.updateUser(value);
    }

    @Override
    public Flowable<UserDTO> getUser(@Path("id") String id) {
        return mCaller.getUser(id);
    }

    @Override
    public Flowable<List<UserDTO>> getAllUsers() {
        return mCaller.getAllUsers();
    }

    @Override
    public Flowable<Void> postImage(@Path("id") String id, @Part MultipartBody.Part filePart) {
        return mCaller.postImage(id, filePart);
    }
}
