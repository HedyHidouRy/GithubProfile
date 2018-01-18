package com.hedyhidoury.githubprofile.ui.splash;


import com.hedyhidoury.githubprofile.base.BaseActivity;
import com.hedyhidoury.githubprofile.utils.Navigator;

import javax.inject.Inject;

/**
 * Created by Hedy HidouRy on 16/01/2018.
 */

public class SplashScreen extends BaseActivity<SplashContract.Presenter> implements SplashContract.View {

    @Inject
    Navigator navigator;


    @Override
    protected int getLayoutId() {
        // No display exists in Splash Screen
        return 0;
    }

    @Override
    public void redirectHome() {
        navigator.navigateToHomeView(this);
    }

    @Override
    public void redirectLogin() {
        navigator.navigateToLoginView(this);
    }
}
