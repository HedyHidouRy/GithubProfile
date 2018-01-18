package com.hedyhidoury.githubprofile.di.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hedyhidoury.githubprofile.data.remote.api.HeaderInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import static com.hedyhidoury.githubprofile.data.remote.Config.API_HOST;

/**
 * Created by Hedy HidouRy on 1/7/2018.
 */

@Module
public abstract class NetModule {

    public static final String BASE_URL = "base_url";


    @Provides
    @Named(BASE_URL)
    protected static String provideBaseURL(){
        return API_HOST;
    }

    @Binds
    @Singleton
    abstract HeaderInterceptor provideHeaderInterceptor(HeaderInterceptor headerInterceptor);

    @Provides
    @Singleton
    static HttpLoggingInterceptor provideHttpLoggingInterceptor(){
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC);
    }

    @Provides
    @Singleton
    static OkHttpClient provideOkhttpClient(HttpLoggingInterceptor httpLoggingInterceptor){
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS);

        return builder.build();
    }

    @Provides
    @Singleton
    static Gson provideGson(){
        return new GsonBuilder().create();
    }

}
