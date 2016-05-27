package com.github.mrtan.myfrendlydemo.util;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class BundleService {
    private final Bundle mData;
    private Bundle mSaveState;

    public BundleService(Bundle saveState, Bundle intentExtras) {
        mData = new Bundle();
        mSaveState = saveState;
        if (mSaveState != null) {
            mData.putAll(mSaveState);
        }
        if (intentExtras != null) {
            mData.putAll(intentExtras);
        }
    }

    @Nullable
    public Object get(String key) {
        return mData.get(key);
    }

    public boolean contains(String key) {
        return mData.containsKey(key);
    }

    public Bundle getAll() {
        return mData;
    }
}
