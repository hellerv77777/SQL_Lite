package com.sebastian.sql_lite.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseSqLite extends SQLiteOpenHelper {

    public DataBaseSqLite(@Nullable Context context, @Nullable SQLiteDatabase.CursorFactory factory) {
        super(context, UtilsDb.DB_NAME, factory, UtilsDb.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(UtilsDb.CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       onCreate(db);
    }
}
