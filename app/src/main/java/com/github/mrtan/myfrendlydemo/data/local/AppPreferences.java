package com.github.mrtan.myfrendlydemo.data.local;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppPreferences {
    public static final String AUTH_TOKEN = "token";
    public static final String LOGIN_REQUEST = "login";
    private final SharedPreferences mSharedPreferences;

    @Inject
    public AppPreferences(Application context){
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @SuppressLint("CommitPreEdits")
    public void applyPreference(String key, boolean value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getPreference(String key, boolean defaultValue){
        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    @SuppressLint("CommitPreEdits")
    public void applyPreference(String key, String value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getPreference(String key, String defaultValue){
        return mSharedPreferences.getString(key, defaultValue);
    }

    public void registerPreferenceChangeListener(OnSharedPreferenceChangeListener listener){
        mSharedPreferences.registerOnSharedPreferenceChangeListener(listener);
    }

    public void unregisterPreferenceChangeListener(OnSharedPreferenceChangeListener listener){
        mSharedPreferences.unregisterOnSharedPreferenceChangeListener(listener);
    }

}
