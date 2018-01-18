package com.hedyhidoury.githubprofile.ui.authentication.presenters;

import com.hedyhidoury.githubprofile.base.BasePresenterImpl;
import com.hedyhidoury.githubprofile.data.models.UserModel;
import com.hedyhidoury.githubprofile.data.repositories.AuthenticationRepository;
import com.hedyhidoury.githubprofile.di.qualifiers.DataProvider;
import com.hedyhidoury.githubprofile.di.qualifiers.DataType;
import com.hedyhidoury.githubprofile.ui.authentication.contracts.IdentifyUserContract;
import com.hedyhidoury.githubprofile.utils.CallbackWrapper;
import com.hedyhidoury.githubprofile.managers.SessionManager;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Hedy HidouRy on 15/01/2018.
 */

public class IdentifyUserPresenter extends BasePresenterImpl<IdentifyUserContract.NetworkingView> implements IdentifyUserContract.Presenter {


    private final AuthenticationRepository authenticationRemoteReposiory;
    private final SessionManager sessionManager;

    @Inject
    public IdentifyUserPresenter(@DataProvider(DataType.Remote) AuthenticationRepository authenticationRemoteReposiory
            , SessionManager sessionManager){
        this.authenticationRemoteReposiory = authenticationRemoteReposiory;
        this.sessionManager = sessionManager;
    }

    @Override
    public void identifyUser(String userName) {
        compositeDisposable.add(authenticationRemoteReposiory.getUser(userName)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new CallbackWrapper<UserModel>(getView()) {
            @Override
            protected void onSuccess(UserModel userModel) {
                prepareForIdentifiedUser(userModel);
            }
        }));
    }

    /**
     * After succeeding getting user, now prepare for getting it
     * @param userModel
     */
    private void prepareForIdentifiedUser(UserModel userModel){
        sessionManager.storeUser(userModel);
        sessionManager.createUserSession(userModel);
        getView().goHomeView();
    }
}
