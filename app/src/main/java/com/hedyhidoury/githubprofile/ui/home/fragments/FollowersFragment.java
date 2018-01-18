package com.hedyhidoury.githubprofile.ui.home.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hedyhidoury.githubprofile.R;
import com.hedyhidoury.githubprofile.base.BaseFragment;
import com.hedyhidoury.githubprofile.data.models.FollowerModel;
import com.hedyhidoury.githubprofile.ui.home.adapters.FollowersAdapter;
import com.hedyhidoury.githubprofile.ui.home.adapters.RepositoriesAdapter;
import com.hedyhidoury.githubprofile.ui.home.contracts.FollowersContract;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Hedy HidouRy on 18/01/2018.
 */

public class FollowersFragment extends BaseFragment<FollowersContract.Presenter> implements FollowersContract.View {


    @BindView(R.id.followers_recycler)
    RecyclerView followersRecycler;
    // Vars
    private FollowersAdapter followersAdapter;

    @Inject
    public FollowersFragment(){
        // Empty for injection
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpViews();
    }

    /**
     * Set up all view ( {@link RecyclerView} ... )
     */
    private void setUpViews() {
        followersAdapter = new FollowersAdapter(getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        followersRecycler.setLayoutManager(layoutManager);
        followersRecycler.setAdapter(followersAdapter);
    }

    @Override
    public void onNetworkError() {
        showMessage(getString(R.string.error_msg_verify_internet));
    }

    @Override
    public void onTimeout() {
        showMessage(getString(R.string.error_msg_verify_internet));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_followers;
    }

    @Override
    public void displayFollowers(List<FollowerModel> followerModelList) {
        followersAdapter.updateFollowers(followerModelList);
    }
}
