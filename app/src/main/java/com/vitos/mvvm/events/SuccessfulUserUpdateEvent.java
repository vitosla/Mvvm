package com.vitos.mvvm.events;

import com.vitos.mvvm.models.User;

/**
 * Created by Victor on 05.06.2017.
 */

public class SuccessfulUserUpdateEvent extends BaseAppEvent<User> {

    public SuccessfulUserUpdateEvent(User data) {
        super(data);
    }
}
