package com.hedyhidoury.githubprofile.base;

/**
 * Created by Hedy HidouRy on 1/7/2018.
 */

/**
 * Dealing the most with a remote server, so it's good to specify error in here
 */
public interface BaseNetworkingView extends BaseView {

    void onNetworkError();

    void onTimeout();
}
