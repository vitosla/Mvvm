package com.vitos.mvvm.db;

import com.vitos.mvvm.models.User;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Victor on 07.06.2017.
 */

public class UserEntityMapper {

    public UserEntity map(User user) {
        UserEntity item = new UserEntity();
        item.setPhoneId(user.getId());
        item.setProvider(user.getProvider());
        item.setEmail(user.getEmail());
        item.setPassword(user.getPassword());
        item.setData(user.getData());
        item.setThumb(user.getThumb());
        item.setImage(user.getImage());
        return item;
    }

    public List<UserEntity> callUserEntity(List<User> users) {

        if (users == null)
            return new ArrayList<>();

        return Observable
                .fromIterable(users)
                .map(this::map)
                .toList()
                .blockingGet();
    }

    public User map(UserEntity user) {
        User item = new User();
        item.setId(user.getPhoneId());
        item.setProvider(user.getProvider());
        item.setEmail(user.getEmail());
        item.setPassword(user.getPassword());
        item.setData(user.getData());
        item.setThumb(user.getThumb());
        item.setImage(user.getImage());
        return item;
    }

    public List<User> callUser(List<UserEntity> users) {
        if (users == null) return new ArrayList<>();

        return Observable
                .fromIterable(users)
                .map(this::map)
                .toList()
                .blockingGet();
    }
}
