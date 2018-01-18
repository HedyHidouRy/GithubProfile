package com.hedyhidoury.githubprofile.utils;


import com.hedyhidoury.githubprofile.base.BaseNetworkingView;

import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * Created by Hedy HidouRy on 15/01/2018.
 */

public abstract class CallbackWrapper<T> extends DisposableObserver<T> {


    private BaseNetworkingView view;

    public CallbackWrapper(BaseNetworkingView view){
        this.view = view;
    }

    protected abstract void onSuccess(T t);

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onComplete() {
        // Nothing to add
    }

    @Override
    public void onError(Throwable e) {
        if(e instanceof HttpException){
            ResponseBody responseBody = ((HttpException)e).response().errorBody();
            view.showMessage(getErrorMessage(responseBody));
        }else if(e instanceof SocketTimeoutException){
            view.onTimeout();
        }else if(e instanceof IOException){
            view.onNetworkError();
        }else{
            view.showMessage(e.getMessage());
        }
    }

    private String getErrorMessage(ResponseBody responseBody){
        try{
            JSONObject jsonObject = new JSONObject(responseBody.string());
            return jsonObject.getString("message");
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
