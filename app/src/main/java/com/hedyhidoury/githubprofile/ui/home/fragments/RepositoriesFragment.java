package com.hedyhidoury.githubprofile.ui.home.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hedyhidoury.githubprofile.R;
import com.hedyhidoury.githubprofile.base.BaseFragment;
import com.hedyhidoury.githubprofile.data.models.RepositoryModel;
import com.hedyhidoury.githubprofile.ui.home.adapters.RepositoriesAdapter;
import com.hedyhidoury.githubprofile.ui.home.contracts.RepositoriesContract;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Hedy HidouRy on 16/01/2018.
 */

public class RepositoriesFragment extends BaseFragment<RepositoriesContract.Presenter> implements RepositoriesContract.View {

    @BindView(R.id.repos_recycler)
    RecyclerView reposRecycler;

    // Vars
    private RepositoriesAdapter repositoriesAdapter;

    @Inject
    public RepositoriesFragment(){
        // Required for injection
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_repositories;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpViews();
    }

    /**
     * Set up and get ready for components
     */
    private void setUpViews() {
        repositoriesAdapter = new RepositoriesAdapter(getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        reposRecycler.setLayoutManager(layoutManager);
        reposRecycler.setAdapter(repositoriesAdapter);
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
    public void displayReposList(List<RepositoryModel> repositoryModels) {
        repositoriesAdapter.updateRepos(repositoryModels);
    }
}
