package com.github.mrtan.myfrendlydemo.ui.reddit;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mrtan.myfrendlydemo.R;
import com.github.mrtan.myfrendlydemo.data.model.Post;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {

    private List<Post> mPosts = new ArrayList<>();

    @Inject
    public PostAdapter() {
        Timber.i("PostAdapter init");
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Timber.i("onCreateViewHolder was call");
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.article_item, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        Timber.i("onBindViewHolder was call");
        holder.onBind(mPosts.get(position));
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public void setPosts(List<Post> articlesToAdd) {
        mPosts.clear();
        mPosts.addAll(articlesToAdd);
        notifyDataSetChanged();
    }
}
