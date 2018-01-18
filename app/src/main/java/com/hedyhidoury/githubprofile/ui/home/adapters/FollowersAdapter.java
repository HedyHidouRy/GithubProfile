package com.hedyhidoury.githubprofile.ui.home.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hedyhidoury.githubprofile.R;
import com.hedyhidoury.githubprofile.data.models.FollowerModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hedy HidouRy on 18/01/2018.
 */

public class FollowersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private List<FollowerModel> followerModels = new ArrayList<>();

    public FollowersAdapter(Context context){
        this.context = context;
    }

    public void updateFollowers(List<FollowerModel> list){
        this.followerModels = list;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View view = LayoutInflater.from(context).inflate(R.layout.item_follower, parent, false);
        viewHolder = new FollowerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FollowerModel followerModel = followerModels.get(position);
        FollowerViewHolder reposViewHolder = (FollowerViewHolder) holder;

        reposViewHolder.followerName.setText(followerModel.getLogin());
    }

    @Override
    public int getItemCount() {
        return followerModels.size();
    }

    public class FollowerViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.folower_name)
        TextView followerName;

        public FollowerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
