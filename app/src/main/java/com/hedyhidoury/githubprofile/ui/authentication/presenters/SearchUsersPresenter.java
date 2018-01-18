package com.hedyhidoury.githubprofile.ui.authentication.presenters;

import com.hedyhidoury.githubprofile.base.BasePresenterImpl;
import com.hedyhidoury.githubprofile.data.models.UserModel;
import com.hedyhidoury.githubprofile.data.repositories.AuthenticationRepository;
import com.hedyhidoury.githubprofile.di.qualifiers.DataProvider;
import com.hedyhidoury.githubprofile.di.qualifiers.DataType;
import com.hedyhidoury.githubprofile.di.scopes.AuthScope;
import com.hedyhidoury.githubprofile.ui.authentication.contracts.SearchUsersContract;
import com.hedyhidoury.githubprofile.utils.CallbackWrapper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Hedy HidouRy on 1/7/2018.
 */
@AuthScope
public class SearchUsersPresenter extends BasePresenterImpl<SearchUsersContract.NetworkingView> implements SearchUsersContract.Presenter{

    private final AuthenticationRepository authenticationReposiory;

    @Inject
    public SearchUsersPresenter(@DataProvider(DataType.Remote) AuthenticationRepository authenticationRepository){
        this.authenticationReposiory = authenticationRepository;
    }

    @Override
    public void onSearchUser(String searchedUser) {
        compositeDisposable.add(authenticationReposiory.getUsers(searchedUser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new CallbackWrapper<List<UserModel>>(getView()) {

                    @Override
                    protected void onSuccess(List<UserModel> userModels) {
                        getView().displayUsers(userModels);
                    }
                }
        ));

    }
}
