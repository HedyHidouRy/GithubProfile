package com.hedyhidoury.githubprofile.data.remote.reponses;

import com.google.gson.annotations.SerializedName;
import com.hedyhidoury.githubprofile.data.models.UserModel;

import java.util.List;

/**
 * Created by Hedy HidouRy on 1/7/2018.
 */

public class UserSearchResponse {
    @SerializedName("total_count")
    private int totalCount;
    @SerializedName("incomplete_results")
    private boolean incompleteResults;
    @SerializedName("items")
    private List<UserModel> items;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public List<UserModel> getItems() {
        return items;
    }

    public void setItems(List<UserModel> items) {
        this.items = items;
    }
}
