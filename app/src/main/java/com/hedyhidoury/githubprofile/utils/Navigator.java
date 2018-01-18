package com.hedyhidoury.githubprofile.utils;

import android.content.Context;
import android.content.Intent;

import com.hedyhidoury.githubprofile.ui.authentication.AuthActivity;
import com.hedyhidoury.githubprofile.ui.home.HomeActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Hedy HidouRy on 15/01/2018.
 */

@Singleton
public class Navigator {

    @Inject
    public Navigator(){
        // Empty
    }

    /**
     * Goes to the user home screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    public void navigateToHomeView(Context context) {
        if (context != null) {
            Intent intentToLaunch = HomeActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    /**
     * Goes to the login screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    public void navigateToLoginView(Context context) {
        if (context != null) {
            Intent intentToLaunch = AuthActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }
    
}
