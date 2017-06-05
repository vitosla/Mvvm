package com.vitos.mvvm.events;

/**
 * Created by Victor on 05.06.2017.
 */

abstract class BaseSuccessfulEvent<T> {

    private final T data;

    BaseSuccessfulEvent(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}