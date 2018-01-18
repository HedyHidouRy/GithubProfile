package com.hedyhidoury.githubprofile.data.local.datasources;

import com.hedyhidoury.githubprofile.data.local.daos.UserDao;
import com.hedyhidoury.githubprofile.data.models.UserModel;
import com.hedyhidoury.githubprofile.data.repositories.UserManagerRepository;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Hedy HidouRy on 16/01/2018.
 */

public class UserLocalDataSource implements UserManagerRepository {

    private UserDao userDao;

    @Inject
    public UserLocalDataSource(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public void addUser(UserModel userModel) {
        userDao.insert(userModel);
    }

    @Override
    public void removeUser(UserModel userModel) {
        userDao.delete(userModel);
    }

    @Override
    public Flowable<UserModel> fetchUser(long userId) {
        return userDao.findUserById(userId);
    }
}
