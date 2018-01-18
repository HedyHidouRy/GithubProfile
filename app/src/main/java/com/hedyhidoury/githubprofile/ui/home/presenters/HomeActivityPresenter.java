package com.hedyhidoury.githubprofile.ui.home.presenters;

import com.hedyhidoury.githubprofile.base.BasePresenterImpl;
import com.hedyhidoury.githubprofile.ui.home.contracts.HomeContract;

import javax.inject.Inject;

/**
 * Created by Hedy HidouRy on 17/01/2018.
 */

public class HomeActivityPresenter extends BasePresenterImpl<HomeContract.View> implements HomeContract.Presenter{

    @Inject
    public HomeActivityPresenter(){}
}
