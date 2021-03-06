package com.vitos.mvvm.api;

import android.accounts.NetworkErrorException;
import android.content.Context;

import com.vitos.mvvm.MvvmApp;
import com.vitos.mvvm.R;
import com.vitos.mvvm.events.FailedEvent;
import com.vitos.mvvm.tools.AppLog;

import org.greenrobot.eventbus.EventBus;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by Victor on 06.06.2017.
 */

public abstract class BaseMaybeObserver<T> extends DisposableMaybeObserver<T> {

    Context mAppContext;

    protected BaseMaybeObserver() {
        mAppContext = MvvmApp.getAppComponent().getContext();
    }

    @Override
    public void onSuccess(T data) {
        onMaybeSuccess(data);
    }

    public abstract void onMaybeSuccess(T data);

    @Override
    public void onError(Throwable e) {
        String message = e.getMessage();
        AppLog.e(message);

        if (e instanceof ConnectException) {
            message = mAppContext.getString(R.string.connect_exception_error);
        }
        else if (e instanceof SocketTimeoutException){
            message = mAppContext.getString(R.string.timeout_error);
        }
        else if (e instanceof UnknownHostException || e instanceof NetworkErrorException) {
            message = mAppContext.getString(R.string.network_error);
        }
        else {
            message = mAppContext.getString(R.string.something_went_wrong);
        }

        EventBus.getDefault().post(new FailedEvent(message));
    }

    @Override
    public void onComplete() {

        int uu=0;
        uu++;
    }
}
