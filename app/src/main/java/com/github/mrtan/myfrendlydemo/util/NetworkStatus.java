package com.github.mrtan.myfrendlydemo.util;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.subjects.BehaviorSubject;

@Singleton
public class NetworkStatus extends BroadcastReceiver {

    private final ConnectivityManager mConnectivityManager;
    private final WifiManager mWifiManager;
    private final BehaviorSubject<Boolean> mOnchangeSubject = BehaviorSubject.create();

    @Inject
    public NetworkStatus(Application context) {
        mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        mWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    }

    public Observable<Boolean> onChange() {
        return mOnchangeSubject.distinctUntilChanged();
    }

    public Observable<Void> OnConnect() {
        return onChange().filter(connected -> connected).map(connected -> null);
    }

    public Observable<Void> onDisconnect() {
        return onChange().filter(connected -> !connected).map(connected -> null);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean isInternetConnected = isInternetConnected();
        mOnchangeSubject.onNext(isInternetConnected);
    }

    public boolean isOnGoodWIFIConnection() {
        NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
        return networkInfo != null && isGoodWIFIConnection(networkInfo);
    }

    public boolean isOnGoodConnection(){
        NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
        return networkInfo != null && isOnGoodConnection(networkInfo);
    }

    public String getNetworksStatus() {
        NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
        return networkInfo == null ? "None" : getNetworksStatus(networkInfo);
    }

    private String getNetworksStatus(@NonNull NetworkInfo info) {
        return info.getType() == ConnectivityManager.TYPE_MOBILE ? info.getSubtypeName() : info.getTypeName();
    }

    private boolean isInternetConnected(){
        return isOnGoodConnection();
    }

    private boolean isOnGoodConnection(@NonNull NetworkInfo info) {
        return info.isConnected() && (isGoodWIFIConnection(info)) || isGoodCellularConnection(info);
    }

    private boolean isGoodWIFIConnection(@NonNull NetworkInfo info){
        return (info.getType() == ConnectivityManager.TYPE_WIFI)
                && (WifiManager.calculateSignalLevel(mWifiManager.getConnectionInfo().getRssi(), 4) >  0);
    }

    private boolean isGoodCellularConnection(@NonNull NetworkInfo info) {
        int networkType = info.getSubtype();
        return networkType != TelephonyManager.NETWORK_TYPE_GPRS
                && networkType != TelephonyManager.NETWORK_TYPE_EDGE
                && networkType != TelephonyManager.NETWORK_TYPE_CDMA
                && networkType != TelephonyManager.NETWORK_TYPE_1xRTT
                && networkType != TelephonyManager.NETWORK_TYPE_IDEN;
    }
}
