package com.hedyhidoury.githubprofile.base;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Hedy HidouRy on 1/7/2018.
 */

public class BasePresenterImpl<T extends BaseView> implements BasePresenter<T> {

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    private T view;

    public T getView() {
        return view;
    }

    @Override
    public void onViewAdded(T view) {
        this.view = view;
    }

    @Override
    public void onViewRemoved() {
        compositeDisposable.clear();
        view = null;
    }
}
