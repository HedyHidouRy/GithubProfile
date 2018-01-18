package com.hedyhidoury.githubprofile.utils;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Hedy HidouRy on 15/01/2018.
 */

@Singleton
public class SharedPreferencesHelper {

    private static final String USER_ID_KEY = "user_id";
    private SharedPreferences sharedPreferences;


    @Inject
    public SharedPreferencesHelper(Context context){
        this.sharedPreferences = context.getSharedPreferences(context.getPackageName() + "_preferences", Context.MODE_PRIVATE);
    }

    /**
     * Store given userId in shared preferences
     * @param userId user id to be stored
     */
    public void storeUserId(long userId){
        sharedPreferences.edit().putLong(USER_ID_KEY, userId).apply();
    }

    /**
     * get stored userId in shared preferences
     */
    public long getUserId(){
        return sharedPreferences.getLong(USER_ID_KEY, -1);
    }

    /**
     * check user Id exist or not
     */
    public boolean userIdeExist(){
        return sharedPreferences.contains(USER_ID_KEY);
    }

    /**
     * get stored userId in shared preferences
     */
    public void removeUserId(){
         sharedPreferences.edit().remove(USER_ID_KEY).apply();
    }

    /**
     * Clear all stored values
     */
    public void clearAll(){
         sharedPreferences.edit().clear();
    }


}
