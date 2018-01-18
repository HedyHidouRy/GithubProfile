package com.hedyhidoury.githubprofile.ui.authentication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.widget.TextView;

import com.hedyhidoury.githubprofile.R;
import com.hedyhidoury.githubprofile.base.BaseActivity;
import com.hedyhidoury.githubprofile.ui.authentication.fragments.IdentifyUserFragment;
import com.hedyhidoury.githubprofile.ui.authentication.fragments.SearchUsersFragment;
import com.hedyhidoury.githubprofile.ui.authentication.contracts.AuthContract;

import butterknife.BindView;
import butterknife.OnClick;

public class AuthActivity extends BaseActivity<AuthContract.Presenter> implements AuthContract.View
    , SearchUsersFragment.SearchUsersFragmentInteractor {

    private static final int SEARCH_FRAGMENT = 1;
    private static final int IDENTIFICATION_FRAGMENT = 2;
    private static final int AUTH_CONTAINER = R.id.auth_container;
    // Views
    @BindView(R.id.switch_text)
    TextView textSwitcher;

    private int selectedFragment = SEARCH_FRAGMENT;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, AuthActivity.class);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openFragment(SearchUsersFragment.newInstance(), AUTH_CONTAINER, false);
    }

    @OnClick(R.id.switch_text)
    public void onSwitchRequested(){
        if(selectedFragment == IDENTIFICATION_FRAGMENT){
            textSwitcher.setText(R.string.identification);
            openFragment(SearchUsersFragment.newInstance(), AUTH_CONTAINER, false);
            selectedFragment = SEARCH_FRAGMENT;
        }else{
            textSwitcher.setText(R.string.search);
            openFragment(IdentifyUserFragment.newInstance(), AUTH_CONTAINER, false);
            selectedFragment = IDENTIFICATION_FRAGMENT;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

}
