package com.github.mrtan.myfrendlydemo.injection.module;

import android.app.Activity;
import android.content.Context;

import com.github.mrtan.myfrendlydemo.injection.ScopeActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @ScopeActivity
    Context providesContext() {
        return mActivity;
    }
}
