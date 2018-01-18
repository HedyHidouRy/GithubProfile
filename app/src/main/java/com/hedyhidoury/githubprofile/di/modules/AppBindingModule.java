package com.hedyhidoury.githubprofile.di.modules;

import com.hedyhidoury.githubprofile.di.components.UserComponent;
import com.hedyhidoury.githubprofile.di.scopes.AuthScope;
import com.hedyhidoury.githubprofile.ui.authentication.AuthActivity;
import com.hedyhidoury.githubprofile.ui.splash.SplashModule;
import com.hedyhidoury.githubprofile.ui.splash.SplashScreen;

import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Hedy HidouRy on 1/7/2018.
 */

@Module(subcomponents = {
        UserComponent.class
})
public abstract class AppBindingModule {

    @AuthScope
    @ContributesAndroidInjector(modules = {
            AuthModule.class,
            AndroidInjectionModule.class
    })
    abstract AuthActivity authActivity();

    @ContributesAndroidInjector(modules = {
            SplashModule.class,
            AndroidInjectionModule.class})
    abstract SplashScreen splashScreen();

}
