package com.hedyhidoury.githubprofile.data.remote.datasources;

import android.content.Loader;

import com.hedyhidoury.githubprofile.data.models.FollowerModel;
import com.hedyhidoury.githubprofile.data.models.RepositoryModel;
import com.hedyhidoury.githubprofile.data.remote.api.GithubSessionApi;
import com.hedyhidoury.githubprofile.data.remote.reponses.UserSearchResponse;
import com.hedyhidoury.githubprofile.data.repositories.GithubRepository;
import com.hedyhidoury.githubprofile.di.qualifiers.RetrofitProvider;
import com.hedyhidoury.githubprofile.di.qualifiers.RetrofitType;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Hedy HidouRy on 16/01/2018.
 */

public class RemoteSessionDataSource implements GithubRepository {

    private final GithubSessionApi githubSessionApi;

    @Inject
    public RemoteSessionDataSource(@RetrofitProvider(RetrofitType.HOME) GithubSessionApi githubSessionApi){
        this.githubSessionApi = githubSessionApi;
    }

    @Override
    public Observable<List<RepositoryModel>> getRepositories(String username) {
        final PublishSubject<List<RepositoryModel>> listPublishSubject = PublishSubject.create();
        githubSessionApi.getRepos(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<RepositoryModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // Nothing to add
                    }

                    @Override
                    public void onNext(List<RepositoryModel> repositoryModelList) {
                        listPublishSubject.onNext(repositoryModelList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listPublishSubject.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        listPublishSubject.onComplete();
                    }
                });
        return listPublishSubject;
    }

    @Override
    public Observable<List<FollowerModel>> getFollowers(String username) {
        final PublishSubject<List<FollowerModel>> listPublishSubject = PublishSubject.create();
        githubSessionApi.getFollowers(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<FollowerModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // Nothing to add
                    }

                    @Override
                    public void onNext(List<FollowerModel> followerModels) {
                        listPublishSubject.onNext(followerModels);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listPublishSubject.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        listPublishSubject.onComplete();
                    }
                });
        return listPublishSubject;    }
}
