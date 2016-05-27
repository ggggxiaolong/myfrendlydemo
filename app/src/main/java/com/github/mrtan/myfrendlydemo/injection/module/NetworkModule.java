package com.github.mrtan.myfrendlydemo.injection.module;

import android.app.Application;
import android.support.annotation.NonNull;

import com.github.mrtan.myfrendlydemo.data.local.AppPreferences;
import com.github.mrtan.myfrendlydemo.injection.ClientCache;
import com.github.mrtan.myfrendlydemo.util.CacheInterceptor;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;

@Module
public class NetworkModule {

    @Provides
    @NonNull
    @Singleton
    public OkHttpClient provideClient(@ClientCache File cacheDir, CacheInterceptor interceptor,
                                      @NonNull AppPreferences appPreferences){
        final OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        Cache cache = new Cache(cacheDir, 20*1024*1024);
        okHttpBuilder.cache(cache);
        okHttpBuilder.interceptors().add(interceptor);
        okHttpBuilder.addInterceptor(getAuthInterceptor(appPreferences));
        okHttpBuilder.networkInterceptors().add(interceptor);
        return okHttpBuilder.build();
    }

    @NonNull
    private Interceptor getAuthInterceptor(@NonNull AppPreferences appPreferences) {
        return chain -> {
            Request.Builder newRequest = chain.request().newBuilder();
            String token = appPreferences.getPreference(AppPreferences.AUTH_TOKEN, null);
            if (token != null) {
                newRequest
                        .addHeader("X-USER-TOKEN", token);
            }
            return chain.proceed(newRequest.build());
        };
    }

    @Provides
    @Singleton
    @ClientCache
    File provideCacheFile(Application context) {
        return new File(context.getCacheDir(), "cache_file");
    }
}
