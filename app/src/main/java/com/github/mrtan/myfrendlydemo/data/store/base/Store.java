package com.github.mrtan.myfrendlydemo.data.store.base;

import android.support.annotation.NonNull;

import rx.Observable;

public interface Store<T, V> {

    Observable<T> get(@NonNull V request);

    Observable<T> fresh(@NonNull V request);
}
