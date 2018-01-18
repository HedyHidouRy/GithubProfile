package com.hedyhidoury.githubprofile.ui.home.contracts;

import com.hedyhidoury.githubprofile.base.BasePresenter;
import com.hedyhidoury.githubprofile.base.BaseView;

/**
 * Created by Hedy HidouRy on 16/01/2018.
 */

public interface HomeContract {

    interface View extends BaseView{}

    interface Presenter extends BasePresenter<HomeContract.View>{

    }
}
