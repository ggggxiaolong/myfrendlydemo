package com.github.mrtan.myfrendlydemo.ui.utils;

import android.graphics.Typeface;

import java.util.HashMap;
import java.util.Map;

/**
 * 我们需要将我们使用的字体缓存起来，利于组织和展示
 */
public final class TypefaceCache {
    private static Map<String, Typeface> mFontCache = new HashMap<>(10);
    private TypefaceCache(){}

    public static Typeface get(String name) {
        return mFontCache.get(name);
    }

    public static void put(String name, Typeface typeface) {
        mFontCache.put(name, typeface);
    }
}
