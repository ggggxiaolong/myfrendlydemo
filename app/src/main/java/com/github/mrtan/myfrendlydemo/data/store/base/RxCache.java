package com.github.mrtan.myfrendlydemo.data.store.base;

import android.support.annotation.NonNull;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import rx.Observable;

public final class RxCache<Request, Response> {
    final Cache<Request, Observable<Response>> memory;

    private RxCache() {
        memory = CacheBuilder.newBuilder()
                //设置最大个数
                .maximumSize(getCacheSize())
                //设置过期时间
                .expireAfterAccess(getCacheTTL(), TimeUnit.MILLISECONDS)
                .build();
    }

    public static <Request, Response> RxCache<Request, Response> create() {
        return new RxCache<>();
    }

    /**
     * @return 从缓存读取数据
     */
    Observable<Response> get(@NonNull final Request request, Observable<Response> network) {
        try {
            return memory.get(request, network::cache);
        } catch (ExecutionException e) {
            //no-op
        }
        return Observable.empty();
    }

    void update(@NonNull final Request request, final Response data) {
        memory.put(request, Observable.just(data));
    }

    void clearMemory(){
        memory.invalidateAll();
    }

    void clearMemory(@NonNull Request request){
        memory.invalidate(request);
    }

    /**
     * 默认生存时间，可以被重写
     * @return
     */
    long getCacheTTL(){
        return TimeUnit.HOURS.toMillis(24);
    }

    /**
     * 默认缓存是1，也可以重写
     * @return
     */
    int getCacheSize(){
        return 30;
    }
}
