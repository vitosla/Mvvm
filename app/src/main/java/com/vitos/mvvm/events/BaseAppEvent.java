package com.vitos.mvvm.events;

/**
 * Created by Victor on 05.06.2017.
 */

abstract class BaseAppEvent<T> {

    private final T data;

    BaseAppEvent(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}