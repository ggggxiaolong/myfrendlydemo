package com.github.mrtan.myfrendlydemo.data.store.base;

import android.support.annotation.NonNull;

import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * 用于处理原始和转换之后数据的订阅逻辑的基类
 * @param <Response> 转化之后的数据
 */
public abstract class StreamStore<Response, Request> extends BaseStore<Response, Request> {
    private final BehaviorSubject<Response> subject;

    public StreamStore() {
        super();
        subject = BehaviorSubject.create();
    }

    /**
     * 暴露给客户端来订阅无尽的数据流
     * 任何订阅这个流的客户端将会收到所有{@Link Response}类型的数据更新
     */
    public Observable<Response> stream() {
        return subject.asObservable();
    }

    @Override
    protected Observable<Response> getNetWorkResponse(@NonNull Request request) {
        return super.getNetWorkResponse(request).doOnNext(this::notifySubscribers);
    }

    /**
     * 通知订阅者数据改变
     * @param data
     */
    protected void notifySubscribers(Response data) {
        subject.onNext(data);
    }
}
