package com.github.mrtan.myfrendlydemo.injection;

import android.app.Activity;

import com.github.mrtan.myfrendlydemo.DemoApplication;
import com.github.mrtan.myfrendlydemo.injection.component.ActivityComponent;
import com.github.mrtan.myfrendlydemo.injection.module.ActivityModule;

public class ActivityComponentFactory {
    private ActivityComponentFactory() {
    }

    public static ActivityComponent create(Activity activity) {
        return DemoApplication
                .getComponent(activity)
                .getApplicationComponent()
                .plus(new ActivityModule(activity));
    }
}
