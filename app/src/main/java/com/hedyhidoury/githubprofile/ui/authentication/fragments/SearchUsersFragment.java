package com.hedyhidoury.githubprofile.ui.authentication.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hedyhidoury.githubprofile.R;
import com.hedyhidoury.githubprofile.base.BaseFragment;
import com.hedyhidoury.githubprofile.data.models.UserModel;
import com.hedyhidoury.githubprofile.ui.authentication.adapters.UsersAdapter;
import com.hedyhidoury.githubprofile.ui.authentication.contracts.SearchUsersContract;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;

/**
 * Created by Hedy HidouRy on 1/7/2018.
 */

public class SearchUsersFragment extends BaseFragment<SearchUsersContract.Presenter> implements
        SearchUsersContract.NetworkingView {

    @BindView(R.id.users_recycler)
    RecyclerView usersRecycler;
    @BindView(R.id.search_field)
    EditText searchField;

    // Vars
    UsersAdapter usersAdapter;

    private SearchUsersFragmentInteractor listener;

    public static SearchUsersFragment newInstance() {
        Bundle args = new Bundle();
        SearchUsersFragment fragment = new SearchUsersFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search_users;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpViews();
        configureEditListener();
    }

    /**
     * Set up views, including recycler view
     */
    private void setUpViews() {
        usersAdapter = new UsersAdapter(getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        usersRecycler.setLayoutManager(layoutManager);
        usersRecycler.setAdapter(usersAdapter);
    }

    /**
     * Make Search Text listen on texts, and on each collapse of time, display something
     */
    private void configureEditListener() {
        RxTextView.textChanges(searchField)
                .filter(charSequence -> charSequence.length() > 2)
                .debounce(300, TimeUnit.MILLISECONDS)
                .map(charSequence -> charSequence.toString())
                .subscribe(s -> getPresenter().onSearchUser(s));
    }

    @Override
    public void displayUsers(List<UserModel> userModelList) {
        usersAdapter.updateUsersList(userModelList);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof SearchUsersFragmentInteractor){
            listener = (SearchUsersFragmentInteractor) context;
        } else{
            throw new RuntimeException(context.toString() + " must implement SearchUsersFragmentInteractor");
        }
    }

    @Override
    public void onNetworkError() {
        Toast.makeText(getActivity(), R.string.error_msg_verify_internet, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTimeout() {
        Toast.makeText(getActivity(), R.string.error_msg_timeout, Toast.LENGTH_SHORT).show();
    }

    public interface SearchUsersFragmentInteractor extends BaseFragment.BaseFragmentInteractor{

    }
}
