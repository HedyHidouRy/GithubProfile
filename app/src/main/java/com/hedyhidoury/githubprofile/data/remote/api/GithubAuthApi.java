package com.hedyhidoury.githubprofile.data.remote.api;

import com.hedyhidoury.githubprofile.data.models.UserModel;
import com.hedyhidoury.githubprofile.data.remote.reponses.UserSearchResponse;
import com.hedyhidoury.githubprofile.di.scopes.AuthScope;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Hedy HidouRy on 1/7/2018.
 */
@AuthScope
public interface GithubAuthApi {

    @GET("search/users")
    Observable<UserSearchResponse> searchUser(@Query("q") String userQuery);

    @GET("users/{username}")
    Observable<UserModel> getUser(@Path("username") String userQuery);

}
