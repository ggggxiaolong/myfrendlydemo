package com.github.mrtan.myfrendlydemo.ui.base;

/**
 * app中的任何一个presenter必须实现这个接口或者实现BasePresenter
 *
 */
public interface Presenter<V extends MvpView> {
    void attachView(V mvpView);

    void detachView();
}
