package com.github.mrtan.myfrendlydemo.data.store.base;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import rx.Observable;

/**
 * RxStore 用来从不同数据源获取数据
 * @param <Response> 转换之后的数据类型
 *                  <p>
 *                  get = 从缓存获取数据，如果获取不到从网络抓取并缓存
 *                  fresh = 跳过内存和本地缓存
 */
public abstract class BaseStore<Response, Request> implements Store<Response, Request> {
    public static final String SUCCESS_RESPONSE = "SUCCESS";
    private final RxCache<Request, Response> cache;

    public BaseStore() {
        cache = RxCache.create();
    }

    @Override
    public Observable<Response> get(@NonNull Request request) {
        return cache.get(request, getNetWorkResponse(request));
    }

    @Override
    public Observable<Response> fresh(@NonNull Request request) {
        return fetch(request, "fresh and clean")
                .doOnNext(data -> cache.update(request, data));
    }

    protected Observable<Response> getNetWorkResponse(@NonNull Request request){
        return fetch(request, null);
    }

    protected abstract Observable<Response> fetch(Request request, @Nullable String forceNetwork);

    public void clearMemory() {
        cache.clearMemory();
    }

    public void clearMemory(@NonNull Request request){
        cache.clearMemory(request);
    }
}
