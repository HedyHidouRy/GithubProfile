package com.hedyhidoury.githubprofile.data.repositories;

import com.hedyhidoury.githubprofile.data.models.UserModel;

import io.reactivex.Flowable;

/**
 * Created by Hedy HidouRy on 16/01/2018.
 */

/**
 * Pratically this is used for local db only, no remote data is managed
 */
public interface UserManagerRepository {

    /**
     * Add new user
     * @param userModel to be added
     */
    void addUser(UserModel userModel);

    /**
     * Remove existing user
     * @param userModel to be deleted
     */
    void removeUser(UserModel userModel);

    /**
     * get existed user
     * @param userId user id
     */
    Flowable<UserModel> fetchUser(long userId);
}
