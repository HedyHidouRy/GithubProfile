package com.hedyhidoury.githubprofile.di.components;

import com.hedyhidoury.githubprofile.data.models.UserModel;
import com.hedyhidoury.githubprofile.di.modules.UserBindingModule;
import com.hedyhidoury.githubprofile.di.scopes.UserScope;
import com.hedyhidoury.githubprofile.managers.SessionManager;

import dagger.BindsInstance;
import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by Hedy HidouRy on 16/01/2018.
 */
@UserScope
@Subcomponent(modules = {
        UserBindingModule.class,
        AndroidInjectionModule.class
})
public interface UserComponent extends AndroidInjector<DaggerApplication> {

    void inject(SessionManager sessionManager);

    @Subcomponent.Builder
    interface Builder{
        UserComponent build();
        @BindsInstance
        Builder withUser(UserModel user);
    }
}
