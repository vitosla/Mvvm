package com.vitos.mvvm.events;

/**
 * Created by Victor on 05.06.2017.
 */

public class FailedEvent extends BaseAppEvent<String>{

    public FailedEvent(String message) {
        super(message);
    }
}
