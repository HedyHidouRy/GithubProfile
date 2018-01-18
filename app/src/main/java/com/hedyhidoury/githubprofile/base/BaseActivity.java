package com.hedyhidoury.githubprofile.base;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.hedyhidoury.githubprofile.ui.authentication.AuthActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;

/**
 * Created by Hedy HidouRy on 1/7/2018.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView
        , HasFragmentInjector
        , BaseFragment.BaseFragmentInteractor {

    @Inject
    DispatchingAndroidInjector<Fragment> injector;

    private T presenter;

    private Unbinder unbinder;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        androidInject();
        super.onCreate(savedInstanceState);
        if(getLayoutId() != 0){
            setContentView(getLayoutId());
            unbinder = ButterKnife.bind(this);
        }
        presenter.onViewAdded(this);
    }

    protected void androidInject() {
        AndroidInjection.inject(this);
    }

    protected abstract int getLayoutId();

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    protected T getPresenter(){
        return presenter;
    }

    @Inject
    public void setPresenter(T presenter){
        this.presenter = presenter;
    }

    @Override
    protected void onDestroy() {
        presenter.onViewRemoved();
        if(unbinder != null){
            unbinder.unbind();
        }
        super.onDestroy();
    }

    @Override
    public void finishView() {
        startActivity(new Intent(this, AuthActivity.class));
        finish();
    }

    @Override
    public AndroidInjector<Fragment> fragmentInjector() {
        return injector;
    }

    @Override
    public void openFragment(Fragment fragment, int container, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(container, fragment);
        if(addToBackStack) fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
