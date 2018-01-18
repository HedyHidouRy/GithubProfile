package com.hedyhidoury.githubprofile.ui.authentication.contracts;

import com.hedyhidoury.githubprofile.base.BaseNetworkingView;
import com.hedyhidoury.githubprofile.base.BasePresenter;

/**
 * Created by Hedy HidouRy on 15/01/2018.
 */

public interface IdentifyUserContract {

    interface Presenter extends BasePresenter<NetworkingView>{
        void identifyUser(String userName);
    }

    interface NetworkingView extends BaseNetworkingView {
        void goHomeView();
    }
}
