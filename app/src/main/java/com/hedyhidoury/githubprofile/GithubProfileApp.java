package com.hedyhidoury.githubprofile;

import android.app.Activity;
import android.app.Application;

import com.hedyhidoury.githubprofile.di.components.DaggerAppComponent;
import com.hedyhidoury.githubprofile.managers.SessionManager;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by Hedy HidouRy on 1/7/2018.
 */

public class GithubProfileApp extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> androidInjector;
    @Inject
    SessionManager sessionManager;

    @Override
    public void onCreate() {
        super.onCreate();
        callDaggerInjector();
    }

    private void callDaggerInjector() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return sessionManager.activityInjector();
    }
}
