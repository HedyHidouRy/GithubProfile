package com.hedyhidoury.githubprofile.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hedyhidoury.githubprofile.base.BaseActivity;
import com.hedyhidoury.githubprofile.base.BasePresenter;
import com.hedyhidoury.githubprofile.managers.SessionManager;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Created by Hedy HidouRy on 16/01/2018.
 */

public abstract class BaseUserActivity<T extends BasePresenter> extends BaseActivity<T> {

    @Inject
    SessionManager sessionManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!sessionManager.isUserLoggedIn()) {
            finishView();
        }
    }

    @Override
    protected void androidInject() {
        AndroidInjection.inject(this);
    }


    protected void logoutUser() {

        sessionManager.logoutUser();

        finishView();
    }
}
