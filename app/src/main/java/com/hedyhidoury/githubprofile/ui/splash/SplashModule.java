package com.hedyhidoury.githubprofile.ui.splash;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Hedy HidouRy on 18/01/2018.
 */

@Module
public abstract class SplashModule {


    @Binds
    public abstract SplashContract.Presenter provideSplashPresenter(SplashPresenter splashPresenter);
}
