package com.hedyhidoury.githubprofile.managers;

import android.app.Activity;

import com.hedyhidoury.githubprofile.data.local.daos.UserDao;
import com.hedyhidoury.githubprofile.data.models.UserModel;
import com.hedyhidoury.githubprofile.data.repositories.UserManagerRepository;
import com.hedyhidoury.githubprofile.di.components.UserComponent;
import com.hedyhidoury.githubprofile.di.qualifiers.DataProvider;
import com.hedyhidoury.githubprofile.di.qualifiers.DataType;
import com.hedyhidoury.githubprofile.utils.SharedPreferencesHelper;

import org.reactivestreams.Subscription;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by Hedy HidouRy on 16/01/2018.
 */
@Singleton
public class SessionManager implements HasActivityInjector {

    private final SharedPreferencesHelper sharedPreferencesHelper;
    private final UserComponent.Builder userBuilder;
    private final UserManagerRepository userManagerRepository;
    // Injection
    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;
    // Vars
    private UserComponent userComponent;
    private UserModel userModel;

    @Inject
    public SessionManager(SharedPreferencesHelper sharedPreferencesHelper
            , UserComponent.Builder builder
            , @DataProvider(DataType.Local) UserManagerRepository userManagerRepository) {
        this.sharedPreferencesHelper = sharedPreferencesHelper;
        this.userBuilder = builder;
        this.userManagerRepository = userManagerRepository;
    }

    /**
     * Check wether user is already authenticated or not
     */
    public Observable<Boolean> checkUserAuthentication() {
        ReplaySubject<Boolean> replaySubject = ReplaySubject.create();
        if (sharedPreferencesHelper.userIdeExist()) {
            userManagerRepository.fetchUser(sharedPreferencesHelper.getUserId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableSubscriber<UserModel>() {
                        @Override
                        public void onNext(UserModel user) {
                            createUserSession(userModel);
                            replaySubject.onNext(true);
                        }

                        @Override
                        public void onError(Throwable t) {
                            replaySubject.onError(t);
                        }

                        @Override
                        public void onComplete() {
                            // Nothing to implement
                        }
                    });
        } else {
            replaySubject.onNext(false);
            replaySubject.onComplete();
        }

        return replaySubject;
    }


    /**
     * Create new session for an authenticated user
     *
     * @param userModel
     */
    public void createUserSession(UserModel userModel) {
        this.userModel = userModel;
        userComponent = userBuilder
                .withUser(userModel)
                .build();

        userComponent.inject(this);
    }

    public void storeUser(UserModel userModel) {
        sharedPreferencesHelper.storeUserId(userModel.getId());
        userManagerRepository.addUser(userModel);
    }

    public boolean isUserLoggedIn() {
        return userComponent != null;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }

    public void logoutUser() {
        sharedPreferencesHelper.removeUserId();
        userManagerRepository.removeUser(userModel);
    }
}