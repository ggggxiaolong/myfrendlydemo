package com.github.mrtan.myfrendlydemo.data.store;

import android.app.Application;
import android.support.annotation.Nullable;

import com.github.mrtan.myfrendlydemo.data.model.RedditData;
import com.github.mrtan.myfrendlydemo.data.remote.Api;
import com.github.mrtan.myfrendlydemo.data.store.base.BaseStore;
import com.squareup.sqlbrite.BriteDatabase;

import javax.inject.Inject;

import dagger.Lazy;
import rx.Observable;

public class RedditStore extends BaseStore<RedditData, String> {

    @Inject
    Lazy<BriteDatabase> db;
    @Inject
    Lazy<Api> api;

    @Inject
    Application application;

    @Inject
    public RedditStore(){}

    @Override
    protected Observable<RedditData> fetch(String forceNetwork, @Nullable String limit) {
        return api.get().aww("50", forceNetwork);
    }
}
