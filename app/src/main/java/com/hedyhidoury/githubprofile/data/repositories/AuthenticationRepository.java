package com.hedyhidoury.githubprofile.data.repositories;

/**
 * Created by Hedy HidouRy on 1/7/2018.
 */


import com.hedyhidoury.githubprofile.data.models.UserModel;
import com.hedyhidoury.githubprofile.di.scopes.AuthScope;

import java.util.List;

import io.reactivex.Observable;

/**
 * This is not really for authentication
 * Search for users and pass to the next activity, for user details
 */
@AuthScope
public interface AuthenticationRepository {

    /**
     * Get List of Users from Github
     * @param searchQuery search query content
     * @return List of UserModel Objects
     */
    Observable<List<UserModel>> getUsers(String searchQuery);


    /**
     * Get an identified user
     * @param userLoginName search query content
     * @return a {@link UserModel} object
     */
    Observable<UserModel> getUser(String userLoginName);

    /**
     * Insert a new User ( usually used for local db )
     * @param userModel user Model
     */
    void insertUser(UserModel userModel);



}
