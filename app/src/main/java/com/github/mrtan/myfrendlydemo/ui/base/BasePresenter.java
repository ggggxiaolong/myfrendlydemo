package com.github.mrtan.myfrendlydemo.ui.base;

import android.support.annotation.CheckResult;

/**
 * 基类实现了Presenter接口并提供的基本的实现。
 */
public class BasePresenter<T extends MvpView> implements Presenter<T> {

    private T mMvpView;

    @Override
    public void attachView(T mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mMvpView = null;
    }

    public T getMvpView() {
        return mMvpView;
    }

    @CheckResult
    public boolean isAttached() {
        return mMvpView != null;
    }

    public void checkViewAttached() {
        if (!isAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please save Presneter.attacheView(MvpView) before" +
                    "requesting data to the Presenter");
        }
    }
}
