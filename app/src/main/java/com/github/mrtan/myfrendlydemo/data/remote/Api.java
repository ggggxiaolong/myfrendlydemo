package com.github.mrtan.myfrendlydemo.data.remote;

import com.github.mrtan.myfrendlydemo.data.model.RedditData;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

public interface Api {

    @GET("r/aww/new/.json")
    Observable<RedditData> aww(@Query("limit") String limit, @Header("fresh") String fresh);
}
