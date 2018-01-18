package com.hedyhidoury.githubprofile.di.components;

import android.app.Application;

import com.hedyhidoury.githubprofile.GithubProfileApp;
import com.hedyhidoury.githubprofile.di.modules.AppModule;
import com.hedyhidoury.githubprofile.di.modules.AppBindingModule;
import com.hedyhidoury.githubprofile.di.modules.NetModule;
import com.hedyhidoury.githubprofile.di.modules.RoomModule;
import com.hedyhidoury.githubprofile.managers.SessionManager;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;


/**
 * Created by Hedy HidouRy on 1/7/2018.
 */
@Singleton
@Component(modules = {
        AppModule.class,
        NetModule.class,
        AppBindingModule.class,
        RoomModule.class,
        AndroidInjectionModule.class
})
public interface AppComponent extends AndroidInjector<DaggerApplication>{

    UserComponent.Builder userBuilder();

    @Component.Builder
    interface Builder {
        AppComponent build();
        @BindsInstance
        Builder application(Application application);
    }

    void inject(GithubProfileApp app);
}
