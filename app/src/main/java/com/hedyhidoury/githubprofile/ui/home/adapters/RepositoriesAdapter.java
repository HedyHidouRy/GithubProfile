package com.hedyhidoury.githubprofile.ui.home.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hedyhidoury.githubprofile.R;
import com.hedyhidoury.githubprofile.data.models.RepositoryModel;
import com.hedyhidoury.githubprofile.ui.authentication.adapters.UsersAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hedy HidouRy on 18/01/2018.
 */

public class RepositoriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<RepositoryModel> repos = new ArrayList<>();

    public RepositoriesAdapter(Context context){
        this.context = context;
    }

    /**
     * Update list of existing repos
     * @param repositoryModels
     */
    public void updateRepos(List<RepositoryModel> repositoryModels){
        this.repos = repositoryModels;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View view = LayoutInflater.from(context).inflate(R.layout.item_folder, parent, false);
        viewHolder = new ReposViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RepositoryModel repositoryModel = repos.get(position);
        ReposViewHolder reposViewHolder = (ReposViewHolder) holder;
        reposViewHolder.repoDescription.setText(repositoryModel.getDescription());
        reposViewHolder.repoLanguage.setText(repositoryModel.getLanguage());
        reposViewHolder.repoName.setText(repositoryModel.getName());
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    public class ReposViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.repo_name)
        TextView repoName;
        @BindView(R.id.repo_language)
        TextView repoLanguage;
        @BindView(R.id.repo_description)
        TextView repoDescription;

        public ReposViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
