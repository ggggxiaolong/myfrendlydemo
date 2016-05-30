package com.github.mrtan.myfrendlydemo.injection.module;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.github.mrtan.myfrendlydemo.BuildConfig;
import com.github.mrtan.myfrendlydemo.data.local.Database;
import com.github.mrtan.myfrendlydemo.data.model.GsonAdaptersModel;
import com.github.mrtan.myfrendlydemo.data.remote.Api;
import com.github.mrtan.myfrendlydemo.util.DateDeSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.otto.Bus;
import com.squareup.picasso.Picasso;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import java.util.Date;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;
import timber.log.Timber;

@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides //for Picasso
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    Bus provideBus() {
        return new Bus();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapterFactory(new GsonAdaptersModel())
                .registerTypeAdapter(Date.class, new DateDeSerializer())
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create();
    }

    @Provides
    @Singleton
    public Api provideTMSApi(@NonNull OkHttpClient okHttpClient,
                             @NonNull Gson gson) {
        return new Retrofit.Builder()
                .baseUrl("http://reddit.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .validateEagerly(BuildConfig.DEBUG)
                .build()
                .create(Api.class);
    }

    @Provides
    @Singleton
    @NonNull
    public Picasso providePicasso(@NonNull Application boxBeeApplication,
                                  @NonNull OkHttpClient okHttpClient) {
        return new Picasso.Builder(boxBeeApplication)
                .downloader(new OkHttp3Downloader(okHttpClient))
                .listener((picasso,uri,exception) ->
                    Timber.e(exception,"there is something wrong with Picasso at URi: %s" , uri)
                )
                .build();
    }

    @Provides
    @NonNull
    @Singleton
    public SqlBrite provideSqlBrite() {
        return SqlBrite.create(message -> Timber.tag("Database").v(message));
    }

    @Provides
    @Singleton
    @NonNull
    public BriteDatabase provideDatabase(SqlBrite sqlBrite, Database helper) {
        return sqlBrite.wrapDatabaseHelper(helper, Schedulers.io());
    }
}
