package com.github.mrtan.myfrendlydemo.interaction;

import android.database.Cursor;

import com.github.mrtan.myfrendlydemo.data.remote.Api;
import com.squareup.sqlbrite.BriteDatabase;

import javax.inject.Inject;

import dagger.Lazy;

/**
 * 任一 reactive任务的基类
 * 例如从数据库或网络存取数据
 */
public abstract class Interaction {

    @Inject
    Lazy<BriteDatabase> mDb;
    @Inject
    Api mApi;

    public boolean recordExists (String tableName,
                                 String dbField, String fieldValue) {
        String query = "SELECT 1 FROM "+ tableName + " WHERE " +dbField + "=?";
        Cursor cursor = mDb.get().query(query, fieldValue);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }


}
