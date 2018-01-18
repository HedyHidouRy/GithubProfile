package com.hedyhidoury.githubprofile.ui.home.presenters;

import com.hedyhidoury.githubprofile.base.BasePresenterImpl;
import com.hedyhidoury.githubprofile.data.models.RepositoryModel;
import com.hedyhidoury.githubprofile.data.models.UserModel;
import com.hedyhidoury.githubprofile.data.repositories.GithubRepository;
import com.hedyhidoury.githubprofile.di.qualifiers.DataProvider;
import com.hedyhidoury.githubprofile.di.qualifiers.DataType;
import com.hedyhidoury.githubprofile.managers.SessionManager;
import com.hedyhidoury.githubprofile.ui.home.contracts.RepositoriesContract;
import com.hedyhidoury.githubprofile.ui.home.fragments.RepositoriesFragment;
import com.hedyhidoury.githubprofile.utils.CallbackWrapper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Hedy HidouRy on 16/01/2018.
 */

public class RepositoriesPresenter extends BasePresenterImpl<RepositoriesContract.View> implements RepositoriesContract.Presenter {


    private final SessionManager sessionManager;
    private final GithubRepository githubRepository;

    @Inject
    RepositoriesPresenter(SessionManager sessionManager, @DataProvider(DataType.Remote)GithubRepository githubRepository){
        this.sessionManager = sessionManager;
        this.githubRepository = githubRepository;
    }

    @Override
    public void onViewAdded(RepositoriesContract.View view) {
        super.onViewAdded(view);
        getRepositories();
    }

    private void getRepositories() {
        githubRepository.getRepositories(sessionManager.getUserModel().getLogin())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new CallbackWrapper<List<RepositoryModel>>(getView()) {
                    @Override
                    protected void onSuccess(List<RepositoryModel> repositoryModels) {
                        getView().displayReposList(repositoryModels);
                    }
                });
    }
}
