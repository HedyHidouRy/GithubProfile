package com.hedyhidoury.githubprofile.base;

/**
 * Created by Hedy HidouRy on 1/7/2018.
 */

public interface BasePresenter<T extends BaseView> {

    void onViewAdded(T view);

    void onViewRemoved();
}
