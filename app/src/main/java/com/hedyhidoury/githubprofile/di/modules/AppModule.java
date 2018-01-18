package com.hedyhidoury.githubprofile.di.modules;

import android.app.Application;

import com.hedyhidoury.githubprofile.data.local.datasources.UserLocalDataSource;
import com.hedyhidoury.githubprofile.data.local.daos.UserDao;
import com.hedyhidoury.githubprofile.data.repositories.UserManagerRepository;
import com.hedyhidoury.githubprofile.di.qualifiers.DataProvider;
import com.hedyhidoury.githubprofile.di.qualifiers.DataType;
import com.hedyhidoury.githubprofile.utils.SharedPreferencesHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Hedy HidouRy on 1/7/2018.
 */

@Module
public abstract class AppModule {

    @Singleton
    @Provides
    public static SharedPreferencesHelper provideSharedPrefs(Application application){
        return new SharedPreferencesHelper(application);
    }


    @Singleton
    @DataProvider(DataType.Local)
    @Provides
    public static UserManagerRepository provideUserManagerRepo(UserDao userDao){
        return new UserLocalDataSource(userDao);
    }

}
