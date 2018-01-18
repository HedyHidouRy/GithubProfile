package com.hedyhidoury.githubprofile.data.remote.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Hedy HidouRy on 1/7/2018.
 */

public class HeaderInterceptor implements Interceptor {
    private static final String ACCEPT_HEADER = "Accept";
    private static final String JSON_TYPE = "application/json";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        request = request.newBuilder().addHeader(ACCEPT_HEADER, JSON_TYPE).build();
        return chain.proceed(request);
    }
}
