package com.github.mrtan.myfrendlydemo.data.local;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.github.mrtan.myfrendlydemo.data.model.Post;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Database extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    @Inject
    public Database(Application context) {
        super(context, "db", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Post.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
