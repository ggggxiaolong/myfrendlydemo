package com.github.mrtan.myfrendlydemo.injection.module;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.github.mrtan.myfrendlydemo.injection.ScopeActivity;
import com.github.mrtan.myfrendlydemo.ui.base.BaseActivity;
import com.github.mrtan.myfrendlydemo.util.BundleService;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module
public class BundleModule {

    @Provides
    @NonNull
    public Bundle provideBundle(Activity context) {
        return context.getIntent().getExtras() == null ? new Bundle():context.getIntent().getExtras();
    }

    @Provides
    @NonNull
    public Intent provideIntent(Activity context) {
        return context.getIntent() == null ? new Intent():context.getIntent();
    }

    @Provides
    @ScopeActivity
    public BundleService provideBundleService (Activity context) {
        return ((BaseActivity) context).getBundleService();
    }
}
