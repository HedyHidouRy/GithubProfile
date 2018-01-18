package com.hedyhidoury.githubprofile.data.repositories;

import com.hedyhidoury.githubprofile.data.models.FollowerModel;
import com.hedyhidoury.githubprofile.data.models.RepositoryModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Hedy HidouRy on 1/7/2018.
 */

public interface GithubRepository {

    /**
     * Get All List of repos
     * @param username repos owner
     * @return Collection of {@link RepositoryModel}
     */
    Observable<List<RepositoryModel>> getRepositories(String username);

    /**
     * Get All List of repos
     * @param username repos owner
     * @return Collection of {@link RepositoryModel}
     */
    Observable<List<FollowerModel>> getFollowers(String username);


}
