package com.hedyhidoury.githubprofile.ui.authentication.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.hedyhidoury.githubprofile.R;
import com.hedyhidoury.githubprofile.base.BaseFragment;
import com.hedyhidoury.githubprofile.ui.authentication.contracts.IdentifyUserContract;
import com.hedyhidoury.githubprofile.utils.Navigator;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;

/**
 * Created by Hedy HidouRy on 15/01/2018.
 */

public class IdentifyUserFragment extends BaseFragment<IdentifyUserContract.Presenter> implements IdentifyUserContract.NetworkingView {


    @BindView(R.id.name_field)
    EditText fieldName;

    @Inject
    Navigator navigator;

    public static IdentifyUserFragment newInstance() {

        Bundle args = new Bundle();

        IdentifyUserFragment fragment = new IdentifyUserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @OnClick(R.id.submit_identification)
    public void onSubmittingName(){
        if(fieldName.getText().toString().isEmpty()){
            fieldName.setError(getString(R.string.error_msg_type_something));
        }else{
            getPresenter().identifyUser(fieldName.getText().toString());
        }
    }

    @Override
    public void goHomeView() {
        navigator.navigateToHomeView(getActivity());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_identify_user;
    }


    @Override
    public void onNetworkError() {
        Toast.makeText(getActivity(), R.string.error_msg_verify_internet, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTimeout() {
        Toast.makeText(getActivity(), R.string.error_msg_timeout, Toast.LENGTH_SHORT).show();
    }
}
