package com.hedyhidoury.githubprofile.ui.home.presenters;

import com.hedyhidoury.githubprofile.base.BasePresenterImpl;
import com.hedyhidoury.githubprofile.data.models.FollowerModel;
import com.hedyhidoury.githubprofile.data.models.RepositoryModel;
import com.hedyhidoury.githubprofile.data.repositories.GithubRepository;
import com.hedyhidoury.githubprofile.di.qualifiers.DataProvider;
import com.hedyhidoury.githubprofile.di.qualifiers.DataType;
import com.hedyhidoury.githubprofile.managers.SessionManager;
import com.hedyhidoury.githubprofile.ui.home.contracts.FollowersContract;
import com.hedyhidoury.githubprofile.utils.CallbackWrapper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Hedy HidouRy on 18/01/2018.
 */

public class FollowersPresenter extends BasePresenterImpl<FollowersContract.View> implements FollowersContract.Presenter {

    private final SessionManager sessionManager;
    private final GithubRepository githubRepository;

    @Inject
    FollowersPresenter(SessionManager sessionManager, @DataProvider(DataType.Remote)GithubRepository githubRepository){
        this.sessionManager = sessionManager;
        this.githubRepository = githubRepository;
    }

    @Override
    public void onViewAdded(FollowersContract.View view) {
        super.onViewAdded(view);
        getFollowersList();
    }

    public void getFollowersList() {
        githubRepository.getFollowers(sessionManager.getUserModel().getLogin())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new CallbackWrapper<List<FollowerModel>>(getView()) {
                    @Override
                    protected void onSuccess(List<FollowerModel> followerModels) {
                        getView().displayFollowers(followerModels);
                    }
                });
    }
}
