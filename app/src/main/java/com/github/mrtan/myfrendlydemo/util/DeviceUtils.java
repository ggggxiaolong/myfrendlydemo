package com.github.mrtan.myfrendlydemo.util;

import android.app.Application;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import javax.inject.Inject;
import javax.inject.Singleton;

import static android.content.Context.WINDOW_SERVICE;

@Singleton
public final class DeviceUtils {

    private WindowManager mWindowManager;

    @Inject
    public DeviceUtils(Application context) {
        mWindowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
    }

    public int getScreenWidth() {
        return getScreenSize().x;
    }

    public int getScreenHeight() {
        return getScreenSize().y;
    }

    public Point getScreenSize() {
        Point point = new Point();
        mWindowManager.getDefaultDisplay().getSize(point);
        return point;
    }
}
