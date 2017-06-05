package com.vitos.mvvm.api.repo;

import com.vitos.mvvm.models.repo.IUserRepository;
import com.vitos.mvvm.models.repo.RemoteUserRepository;

/**
 * Created by Victor on 05.06.2017.
 */

public class RepositoryFactory implements IRepositoryFactory {
    @Override
    public IUserRepository getUserRepository() {
        return new RemoteUserRepository();
    }
}
