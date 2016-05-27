package com.github.mrtan.myfrendlydemo.ui.reddit;

import com.github.mrtan.myfrendlydemo.interaction.RedditReader;
import com.github.mrtan.myfrendlydemo.ui.base.BasePresenter;
import com.github.mrtan.myfrendlydemo.util.FriendlyScheduler;

import javax.inject.Inject;

import rx.Subscription;

public class RedditPresenter extends BasePresenter<RedditMVPView> {

    private final RedditReader mRedditReader;
    private Subscription mSubscription;

    @Inject
    public RedditPresenter(RedditReader redditReader) {
        mRedditReader = redditReader;
    }

    @Override
    public void attachView(RedditMVPView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }

    public void loadPosts() {
        checkViewAttached();
        mSubscription = mRedditReader
                .readPosts()
                .compose(FriendlyScheduler.schedule())
                .subscribe(posts -> getMvpView().showPosts(posts),
                        throwable -> getMvpView().showError());
    }
}
