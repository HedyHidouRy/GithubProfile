package com.hedyhidoury.githubprofile.ui.home;

import com.hedyhidoury.githubprofile.ui.home.contracts.FollowersContract;
import com.hedyhidoury.githubprofile.ui.home.contracts.RepositoriesContract;
import com.hedyhidoury.githubprofile.ui.home.fragments.FollowersFragment;
import com.hedyhidoury.githubprofile.ui.home.fragments.RepositoriesFragment;
import com.hedyhidoury.githubprofile.ui.home.presenters.FollowersPresenter;
import com.hedyhidoury.githubprofile.ui.home.presenters.RepositoriesPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Hedy HidouRy on 16/01/2018.
 */

@Module
public abstract class HomeModule {

    @ContributesAndroidInjector
    abstract RepositoriesFragment repositoriesFragment();

    @Binds
    abstract RepositoriesContract.Presenter provideReposContract(RepositoriesPresenter repositoriesPresenter);

    @ContributesAndroidInjector
    abstract FollowersFragment followersFragment();

    @Binds
    abstract FollowersContract.Presenter provideFollowersPresenter(FollowersPresenter followersPresenter);


}
