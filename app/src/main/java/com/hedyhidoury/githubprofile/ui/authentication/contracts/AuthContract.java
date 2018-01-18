package com.hedyhidoury.githubprofile.ui.authentication.contracts;

import com.hedyhidoury.githubprofile.base.BasePresenter;
import com.hedyhidoury.githubprofile.base.BaseNetworkingView;
import com.hedyhidoury.githubprofile.base.BaseView;

/**
 * Created by Hedy HidouRy on 1/8/2018.
 */

public interface AuthContract {

    interface Presenter extends BasePresenter<AuthContract.View>{

    }

    interface View extends BaseView {
    }
}
