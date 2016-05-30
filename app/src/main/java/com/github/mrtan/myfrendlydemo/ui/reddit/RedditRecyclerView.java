package com.github.mrtan.myfrendlydemo.ui.reddit;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.github.mrtan.myfrendlydemo.ui.base.BaseActivity;

import javax.inject.Inject;

import timber.log.Timber;


public class RedditRecyclerView extends RecyclerView {

    @Inject
    PostAdapter mAdapter;

    public RedditRecyclerView(Context context) {
        this(context, null);
    }

    public RedditRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RedditRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        ((BaseActivity)context).getActivityComponent().inject(this);
        Timber.i("RedditRecyclerView init");
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setOrientation();
        setAdapter(mAdapter);
    }

    private void setOrientation() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        setLayoutManager(layoutManager);
    }
}
