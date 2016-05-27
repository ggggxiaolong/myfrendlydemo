package com.github.mrtan.myfrendlydemo.interaction;

import com.github.mrtan.myfrendlydemo.data.model.Post;
import com.github.mrtan.myfrendlydemo.data.store.RedditStore;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.schedulers.Schedulers;

public class RedditReader extends Interaction {
    public static final String LIMIT = "50";

    private final RedditStore mStore;
    private final RedditSaver mSaver;

    @Inject
    public RedditReader(RedditStore store, RedditSaver saver) {
        mStore = store;
        mSaver = saver;
    }

    public Observable<List<Post>> readPosts() {
        if (!recordExists(Post.TABLE_NAME, Post._ID, "1")){
            return seedDB().flatMap(posts -> getPostsFromDB());
        }
        return getPostsFromDB();
    }

    private Observable<List<Post>> getPostsFromDB() {
        return mDb.get().createQuery(Post.TABLE_NAME, Post.SELECT_ALL)
                .mapToList(Post.MAPPER::map)
                .first();
    }

    private Observable<List<Post>> seedDB() {
        return mStore.get(LIMIT)
                .subscribeOn(Schedulers.io())
                .map( redditData -> redditData.data().children())
                .flatMap(mSaver::save);
    }
}
