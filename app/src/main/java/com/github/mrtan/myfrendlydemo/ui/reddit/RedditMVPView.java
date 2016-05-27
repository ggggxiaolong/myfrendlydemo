package com.github.mrtan.myfrendlydemo.ui.reddit;

import com.github.mrtan.myfrendlydemo.data.model.Post;
import com.github.mrtan.myfrendlydemo.ui.base.MvpView;

import java.util.List;

/**
 * Created by mrtan on 5/27/16.
 */
public interface RedditMVPView extends MvpView {

    void showError();

    void showPosts(List<Post> posts);
}
