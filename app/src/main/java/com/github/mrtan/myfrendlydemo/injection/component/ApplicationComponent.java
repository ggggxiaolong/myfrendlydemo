package com.github.mrtan.myfrendlydemo.injection.component;

import android.support.annotation.NonNull;

import com.github.mrtan.myfrendlydemo.DemoApplication;
import com.github.mrtan.myfrendlydemo.injection.module.ActivityModule;
import com.github.mrtan.myfrendlydemo.injection.module.ApplicationModule;
import com.github.mrtan.myfrendlydemo.injection.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {

    ActivityComponent plus(@NonNull ActivityModule activityModule);

    void inject(@NonNull DemoApplication boxBeeApplication);
}
