package com.hedyhidoury.githubprofile.di.modules;

import com.google.gson.Gson;
import com.hedyhidoury.githubprofile.data.remote.api.GithubSessionApi;
import com.hedyhidoury.githubprofile.data.remote.datasources.RemoteSessionDataSource;
import com.hedyhidoury.githubprofile.data.repositories.GithubRepository;
import com.hedyhidoury.githubprofile.di.qualifiers.DataProvider;
import com.hedyhidoury.githubprofile.di.qualifiers.DataType;
import com.hedyhidoury.githubprofile.di.qualifiers.RetrofitProvider;
import com.hedyhidoury.githubprofile.di.qualifiers.RetrofitType;
import com.hedyhidoury.githubprofile.di.scopes.UserScope;
import com.hedyhidoury.githubprofile.ui.home.HomeActivity;
import com.hedyhidoury.githubprofile.ui.home.HomeModule;
import com.hedyhidoury.githubprofile.ui.home.contracts.HomeContract;
import com.hedyhidoury.githubprofile.ui.home.presenters.HomeActivityPresenter;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.hedyhidoury.githubprofile.di.modules.NetModule.BASE_URL;

/**
 * Created by Hedy HidouRy on 16/01/2018.
 */

/**
 * Contains all elements which user will be a part of it
 */
@Module
public abstract class UserBindingModule {

    @ContributesAndroidInjector(modules = {AndroidInjectionModule.class, HomeModule.class})
    public abstract HomeActivity homeActivity();

    @Binds
    public abstract HomeContract.Presenter provideHomeContract(HomeActivityPresenter homeActivityPresenter);

    @Provides
    @UserScope
    @RetrofitProvider(RetrofitType.HOME)
    static Retrofit provideRetrofit(@Named(BASE_URL) String baseUrl, Gson gson, OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @UserScope
    @RetrofitProvider(RetrofitType.HOME)
    static GithubSessionApi provideGithubSessionApi(@RetrofitProvider(RetrofitType.HOME) Retrofit retrofit){
        return retrofit.create(GithubSessionApi.class);
    }

    @Provides
    @UserScope
    @DataProvider(DataType.Remote)
    static GithubRepository provideSessionReposiory(@RetrofitProvider(RetrofitType.HOME) GithubSessionApi githubSessionApi) {
        return new RemoteSessionDataSource(githubSessionApi);
    }

}
