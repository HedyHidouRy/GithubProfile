package com.hedyhidoury.githubprofile.di.components;

import com.hedyhidoury.githubprofile.di.scopes.AuthScope;

import dagger.BindsInstance;
import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by Hedy HidouRy on 1/7/2018.
 */

@AuthScope
@Subcomponent(modules = {
        AndroidInjectionModule.class
})
public interface AuthComponent extends AndroidInjector<DaggerApplication> {

    @Subcomponent.Builder
    interface Builder {
        AuthComponent build();
    }
}
