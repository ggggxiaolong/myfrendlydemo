package com.github.mrtan.myfrendlydemo.injection.component;

import android.app.Activity;

import com.github.mrtan.myfrendlydemo.injection.ScopeActivity;
import com.github.mrtan.myfrendlydemo.injection.module.ActivityModule;
import com.github.mrtan.myfrendlydemo.injection.module.BundleModule;
import com.github.mrtan.myfrendlydemo.ui.reddit.PostViewHolder;
import com.github.mrtan.myfrendlydemo.ui.reddit.RedditRecyclerView;
import com.github.mrtan.myfrendlydemo.ui.reddit.RedditView;

import dagger.Subcomponent;

@Subcomponent(modules = {ActivityModule.class, BundleModule.class})
@ScopeActivity
public interface ActivityComponent {

    void inject(Activity activity);

    void inject(RedditView redditView);

    void inject(RedditRecyclerView redditRecyclerView);

    void inject(PostViewHolder postViewHolder);
}
