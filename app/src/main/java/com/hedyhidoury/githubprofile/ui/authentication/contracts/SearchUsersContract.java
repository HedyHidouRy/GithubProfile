package com.hedyhidoury.githubprofile.ui.authentication.contracts;

import com.hedyhidoury.githubprofile.base.BaseNetworkingView;
import com.hedyhidoury.githubprofile.base.BasePresenter;
import com.hedyhidoury.githubprofile.data.models.UserModel;

import java.util.List;

/**
 * Created by Hedy HidouRy on 1/7/2018.
 */

public interface SearchUsersContract {

    interface NetworkingView extends BaseNetworkingView {
        void displayUsers(List<UserModel> userModelList);
    }

    interface Presenter extends BasePresenter<NetworkingView>{
        void onSearchUser(String searchedUser);
    }
}
