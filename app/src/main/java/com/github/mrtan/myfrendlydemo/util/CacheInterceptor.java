package com.github.mrtan.myfrendlydemo.util;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CacheInterceptor implements Interceptor{

    private final NetworkStatus mNetworkStatus;

    @Inject
    public CacheInterceptor(NetworkStatus networkStatus) {
        mNetworkStatus = networkStatus;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originRequest = chain.request();
        Request.Builder request = originRequest.newBuilder();
        if (originRequest.header("fresh") != null) {
            request.cacheControl(CacheControl.FORCE_NETWORK);
        }
        Response response = chain.proceed(request.build());
        return response.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                .header("Cache-Control", cacheControl())
                .build();
    }

    private String cacheControl() {
        String cacheHeaderValue;
        if (mNetworkStatus.isOnGoodConnection()) {
            cacheHeaderValue = "public, max-age=2419200";
        } else {
            cacheHeaderValue = "public, only-if-cached, max-stale=2419200";
        }
        return cacheHeaderValue;
    }
}
