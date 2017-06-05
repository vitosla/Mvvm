package com.vitos.mvvm.api.repo;

import com.vitos.mvvm.models.repo.IUserRepository;

/**
 * Created by Victor on 05.06.2017.
 */

public interface IRepositoryFactory {

    IUserRepository getUserRepository();
}
