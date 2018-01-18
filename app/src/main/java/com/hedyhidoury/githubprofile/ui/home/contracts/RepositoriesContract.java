package com.hedyhidoury.githubprofile.ui.home.contracts;

import com.hedyhidoury.githubprofile.base.BaseNetworkingView;
import com.hedyhidoury.githubprofile.base.BasePresenter;
import com.hedyhidoury.githubprofile.data.models.RepositoryModel;

import java.util.List;

/**
 * Created by Hedy HidouRy on 16/01/2018.
 */

public interface RepositoriesContract {

    interface View extends BaseNetworkingView{
        void displayReposList(List<RepositoryModel> repositoryModels);
    }

    interface Presenter extends BasePresenter<RepositoriesContract.View>{
    }
}
