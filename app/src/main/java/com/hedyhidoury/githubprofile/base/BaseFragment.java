package com.hedyhidoury.githubprofile.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.DaggerFragment;

/**
 * Created by Hedy HidouRy on 1/7/2018.
 */

public abstract class BaseFragment<T extends BasePresenter> extends DaggerFragment implements BaseView {

    @Inject
    T presenter;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(),container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onViewAdded(this);
    }

    public T getPresenter() {
        return presenter;
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        presenter.onViewRemoved();
        super.onDestroy();
    }

    protected abstract int getLayoutId();

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishView() {
        // Not for now
    }

    public interface BaseFragmentInteractor{
        void openFragment(Fragment fragment, int container, boolean addToBackStack);
    }
}
