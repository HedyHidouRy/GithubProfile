package com.hedyhidoury.githubprofile.ui.splash;

import com.hedyhidoury.githubprofile.base.BasePresenter;
import com.hedyhidoury.githubprofile.base.BaseView;

/**
 * Created by Hedy HidouRy on 18/01/2018.
 */

public interface SplashContract {

    interface Presenter extends BasePresenter<SplashContract.View>{

    }

    interface View extends BaseView{
        void redirectHome();
        void redirectLogin();
    }
}
