package com.hedyhidoury.githubprofile.ui.authentication.presenters;

import com.hedyhidoury.githubprofile.base.BasePresenterImpl;
import com.hedyhidoury.githubprofile.ui.authentication.contracts.AuthContract;

import javax.inject.Inject;

/**
 * Created by Hedy HidouRy on 1/8/2018.
 */

public class AuthPresenter extends BasePresenterImpl<AuthContract.View> implements AuthContract.Presenter{

    @Inject
    public AuthPresenter(){}
}
