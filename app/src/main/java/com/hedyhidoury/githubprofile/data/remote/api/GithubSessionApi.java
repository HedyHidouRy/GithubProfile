package com.hedyhidoury.githubprofile.data.remote.api;

/**
 * Created by Hedy HidouRy on 16/01/2018.
 */

import com.hedyhidoury.githubprofile.data.models.FollowerModel;
import com.hedyhidoury.githubprofile.data.models.RepositoryModel;
import com.hedyhidoury.githubprofile.data.remote.reponses.UserSearchResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * All WS of an authenticated user
 */
public interface GithubSessionApi {

    @GET("users/{username}/repos")
    Observable<List<RepositoryModel>> getRepos(@Path("username") String username);

    @GET("users/{username}/followers")
    Observable<List<FollowerModel>> getFollowers(@Path("username") String username);


}
