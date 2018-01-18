package com.hedyhidoury.githubprofile.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Hedy HidouRy on 18/01/2018.
 */

public class FollowerModel {

    @SerializedName("login")
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
