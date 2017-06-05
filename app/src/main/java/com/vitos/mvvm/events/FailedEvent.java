package com.vitos.mvvm.events;

/**
 * Created by Victor on 05.06.2017.
 */

public class FailedEvent {

    private final String message;

    public FailedEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
