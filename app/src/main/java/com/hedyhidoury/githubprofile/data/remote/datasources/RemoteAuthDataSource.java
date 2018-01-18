package com.hedyhidoury.githubprofile.data.remote.datasources;


import com.hedyhidoury.githubprofile.data.models.UserModel;
import com.hedyhidoury.githubprofile.data.remote.api.GithubAuthApi;
import com.hedyhidoury.githubprofile.data.remote.reponses.UserSearchResponse;
import com.hedyhidoury.githubprofile.data.repositories.AuthenticationRepository;
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
 * Created by Hedy HidouRy on 1/8/2018.
 */


public class RemoteAuthDataSource implements AuthenticationRepository {

    private final GithubAuthApi githubAuthApi;

    @Inject
    public RemoteAuthDataSource(@RetrofitProvider(RetrofitType.AUTH) GithubAuthApi githubAuthApi){
        this.githubAuthApi = githubAuthApi;
    }

    @Override
    public Observable<List<UserModel>> getUsers(String searchQuery) {
        final PublishSubject<List<UserModel>> listPublishSubject = PublishSubject.create();
        githubAuthApi.searchUser(searchQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserSearchResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // Nothing to add
                    }

                    @Override
                    public void onNext(UserSearchResponse userSearchResponse) {
                        listPublishSubject.onNext(userSearchResponse.getItems());
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
    public Observable<UserModel> getUser(String userLoginName) {
        return githubAuthApi.getUser(userLoginName);
    }

    @Override
    public void insertUser(UserModel userModel) {
        // Not used my friend
    }
}
