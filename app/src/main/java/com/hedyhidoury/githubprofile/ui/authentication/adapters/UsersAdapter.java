package com.hedyhidoury.githubprofile.ui.authentication.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hedyhidoury.githubprofile.R;
import com.hedyhidoury.githubprofile.data.models.UserModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hedy HidouRy on 11/01/2018.
 */

public class UsersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<UserModel> userList = new ArrayList<>();
    private Context context;

    public UsersAdapter(Context context){
        this.context = context;
    }

    /**
     * Update Adapter user list
     * @param userModels new User Collection List
     */
    public void updateUsersList(List<UserModel> userModels){
        this.userList = userModels;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View view = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false);
        viewHolder = new UserViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        UserViewHolder userViewHolder = (UserViewHolder)holder;
        UserModel user = userList.get(position);

        userViewHolder.userName.setText(user.getLogin());
        userViewHolder.userRate.setText(Double.toString(user.getScore()));

        Picasso.with(context)
                .load(user.getAvatarUrl())
                .placeholder(R.drawable.ic_man)
                .into(userViewHolder.userImage);

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.user_image)
        ImageView userImage;
        @BindView(R.id.user_name)
        TextView userName;
        @BindView(R.id.user_rate)
        TextView userRate;

        public UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
