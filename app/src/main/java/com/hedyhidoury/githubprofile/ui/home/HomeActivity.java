package com.hedyhidoury.githubprofile.ui.home;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.hedyhidoury.githubprofile.R;
import com.hedyhidoury.githubprofile.base.BaseUserActivity;
import com.hedyhidoury.githubprofile.ui.home.contracts.HomeContract;
import com.hedyhidoury.githubprofile.ui.home.fragments.FollowersFragment;
import com.hedyhidoury.githubprofile.ui.home.fragments.RepositoriesFragment;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Hedy HidouRy on 16/01/2018.
 */

public class HomeActivity extends BaseUserActivity<HomeContract.Presenter> implements HomeContract.View{

    private static final int HOME_CONTAINER = R.id.home_container;
    // Injections
    @Inject
    RepositoriesFragment repositoriesFragment;
    @Inject
    FollowersFragment followersFragment;

    // Views
    @BindView(R.id.bottom_view)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    public static Intent getCallingIntent(Context context) {
        return new Intent(context, HomeActivity.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationListener);
        openHomeFragment(R.id.action_folders);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int itemId = item.getItemId();
            if(bottomNavigationView.getSelectedItemId() == itemId){
                // If selected same tab
                return true;
            }
            return openHomeFragment(itemId);
        }
    };

    private boolean openHomeFragment(int itemId) {
        Fragment fragment = null;
        switch (itemId){
            case R.id.action_folders:
                fragment = repositoriesFragment;
                break;
            case R.id.action_gists:
                fragment = followersFragment;
                break;
            default:
                break;
        }
        openFragment(fragment, HOME_CONTAINER, false);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            logoutUser();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

}
