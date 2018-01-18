package com.hedyhidoury.githubprofile.ui.splash;

import com.hedyhidoury.githubprofile.base.BasePresenterImpl;
import com.hedyhidoury.githubprofile.managers.SessionManager;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Hedy HidouRy on 18/01/2018.
 */

public class SplashPresenter extends BasePresenterImpl<SplashContract.View> implements SplashContract.Presenter {

    private final SessionManager sessionManager;

    @Inject
    public SplashPresenter(SessionManager sessionManager){
        this.sessionManager = sessionManager;
        checkAuthentication();
    }

    private void checkAuthentication() {
        sessionManager.checkUserAuthentication()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Boolean>() {
                    @Override
                    public void onNext(Boolean loggedIn) {
                        if(loggedIn){
                            getView().redirectHome();
                        }else{
                            getView().redirectLogin();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        // Nothing to implement
                    }

                    @Override
                    public void onComplete() {
                        // Nothing to implement
                    }
                });
    }

}
