package com.github.mrtan.myfrendlydemo.ui.reddit;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;

import com.github.mrtan.myfrendlydemo.R;
import com.github.mrtan.myfrendlydemo.data.model.Post;
import com.github.mrtan.myfrendlydemo.ui.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

//TODO
public class RedditView extends CoordinatorLayout implements RedditMVPView{

    @Inject
    RedditPresenter mRedditPresenter;
    @BindView(R.id.postRecyclerView)
    RedditRecyclerView mRedditRecyclerView;

    private Unbinder bind;

    public RedditView(Context context) {
        this(context, null);
    }

    public RedditView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RedditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ((BaseActivity)context)
                .getActivityComponent()
                .inject(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mRedditPresenter.attachView(this);
        bind = ButterKnife.bind(this);
        mRedditPresenter.loadPosts();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mRedditPresenter.detachView();
        bind.unbind();
    }

    @Override
    public void showError() {

    }

    @Override
    public void showPosts(List<Post> posts) {
        mRedditRecyclerView.mAdapter.setPosts(posts);
    }
}
