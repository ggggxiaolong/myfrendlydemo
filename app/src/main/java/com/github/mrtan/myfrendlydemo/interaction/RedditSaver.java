package com.github.mrtan.myfrendlydemo.interaction;

import com.github.mrtan.myfrendlydemo.data.model.Children;
import com.github.mrtan.myfrendlydemo.data.model.Image;
import com.github.mrtan.myfrendlydemo.data.model.Post;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import timber.log.Timber;

public class RedditSaver extends Interaction {

    @Inject
    public RedditSaver() {
    }

    public Observable<List<Post>> save(List<Children> childrens) {
        return Observable
                .from(childrens)
                .map(Children::data)
                .doOnNext(this::insertPost)
                .toList();
    }

    private void insertPost(Post post) {
        if (!post.nestedThumbnail().isPresent()) {
            return;
        }
        Image image = post.nestedThumbnail().get();

        mDb.get().insert(Post.TABLE_NAME, new Post.Marshal()
                .title(post.title())
                .height(image.height())
                .width(image.width())
                .url(image.url())
                .asContentValues());
    }
}
