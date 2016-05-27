package com.github.mrtan.myfrendlydemo;

import android.app.Application;
import android.content.Context;

import com.github.mrtan.myfrendlydemo.injection.component.ApplicationComponent;
import com.github.mrtan.myfrendlydemo.injection.component.DaggerApplicationComponent;
import com.github.mrtan.myfrendlydemo.injection.module.ApplicationModule;

import timber.log.Timber;

public class DemoApplication extends Application {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static DemoApplication getComponent(Context context) {
        return (DemoApplication) context.getApplicationContext();
    }

    public ApplicationComponent getApplicationComponent() {
        if (mApplicationComponent == null){
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

}
