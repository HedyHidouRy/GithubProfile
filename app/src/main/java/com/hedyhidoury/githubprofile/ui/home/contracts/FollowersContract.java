package com.hedyhidoury.githubprofile.ui.home.contracts;

import com.hedyhidoury.githubprofile.base.BaseNetworkingView;
import com.hedyhidoury.githubprofile.base.BasePresenter;
import com.hedyhidoury.githubprofile.data.models.FollowerModel;

import java.util.List;

/**
 * Created by Hedy HidouRy on 18/01/2018.
 */

public interface FollowersContract {
    interface View extends BaseNetworkingView{
        void displayFollowers(List<FollowerModel> followerModelList);
    }

    interface Presenter extends BasePresenter<FollowersContract.View>{

    }
}
