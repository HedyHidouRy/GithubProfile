package com.hedyhidoury.githubprofile.di.modules;

import com.google.gson.Gson;
import com.hedyhidoury.githubprofile.data.remote.datasources.RemoteAuthDataSource;
import com.hedyhidoury.githubprofile.data.remote.api.GithubAuthApi;
import com.hedyhidoury.githubprofile.data.repositories.AuthenticationRepository;
import com.hedyhidoury.githubprofile.di.qualifiers.DataProvider;
import com.hedyhidoury.githubprofile.di.qualifiers.DataType;
import com.hedyhidoury.githubprofile.di.qualifiers.RetrofitProvider;
import com.hedyhidoury.githubprofile.di.qualifiers.RetrofitType;
import com.hedyhidoury.githubprofile.di.scopes.AuthScope;
import com.hedyhidoury.githubprofile.ui.authentication.contracts.IdentifyUserContract;
import com.hedyhidoury.githubprofile.ui.authentication.fragments.IdentifyUserFragment;
import com.hedyhidoury.githubprofile.ui.authentication.fragments.SearchUsersFragment;
import com.hedyhidoury.githubprofile.ui.authentication.contracts.AuthContract;
import com.hedyhidoury.githubprofile.ui.authentication.contracts.SearchUsersContract;
import com.hedyhidoury.githubprofile.ui.authentication.presenters.AuthPresenter;
import com.hedyhidoury.githubprofile.ui.authentication.presenters.IdentifyUserPresenter;
import com.hedyhidoury.githubprofile.ui.authentication.presenters.SearchUsersPresenter;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.hedyhidoury.githubprofile.di.modules.NetModule.BASE_URL;

/**
 * Created by Hedy HidouRy on 1/8/2018.
 */

@Module
public abstract class AuthModule {

    @ContributesAndroidInjector
    abstract SearchUsersFragment searchUsersFragment();

    @ContributesAndroidInjector
    abstract IdentifyUserFragment identifyUserFragment();

    @Binds
    public abstract AuthContract.Presenter provideAuthContract(AuthPresenter authPresenter);

    @Binds
    public abstract SearchUsersContract.Presenter provideSearchUsersPresenter(SearchUsersPresenter searchUsersPresenter);

    @Binds
    public abstract IdentifyUserContract.Presenter provideUserIdentification(IdentifyUserPresenter identifyUserPresenter);


    @Provides
    @AuthScope
    @RetrofitProvider(RetrofitType.AUTH)
    static Retrofit provideRetrofit(@Named(BASE_URL) String baseUrl, Gson gson, OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @AuthScope
    @RetrofitProvider(RetrofitType.AUTH)
    static GithubAuthApi provideGithubAuthApi(@RetrofitProvider(RetrofitType.AUTH) Retrofit retrofit){
        return retrofit.create(GithubAuthApi.class);
    }

    @Provides
    @AuthScope
    @DataProvider(DataType.Remote)
    static AuthenticationRepository provideAuthenticationReposiory(@RetrofitProvider(RetrofitType.AUTH) GithubAuthApi githubAuthApi) {
        return new RemoteAuthDataSource(githubAuthApi);
    }

}
