package com.github.mrtan.myfrendlydemo.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.mrtan.myfrendlydemo.injection.ActivityComponentFactory;
import com.github.mrtan.myfrendlydemo.injection.component.ActivityComponent;
import com.github.mrtan.myfrendlydemo.injection.component.ApplicationComponent;
import com.github.mrtan.myfrendlydemo.util.BundleService;

public abstract class BaseActivity extends AppCompatActivity{

    private BundleService mBundleService;
    protected ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
    }

    public ActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = ActivityComponentFactory.create(this);
        }
        return mActivityComponent;
    }

    public BundleService getBundleService() {
        return mBundleService;
    }

    @LayoutRes
    protected abstract int getLayout();
}
