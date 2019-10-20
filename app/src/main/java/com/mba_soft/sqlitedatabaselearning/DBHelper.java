package com.mba_soft.sqlitedatabaselearning;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    final String LOG_TAG = "SQLDbL";

    DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

        Log.d(LOG_TAG, "App: DBHelper constructor");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String tableName = "main_table";
        String cNameName = "name";
        String cNameEmail = "email";
        String cNameAddition = "addition";

        String query = "create table " + tableName +
                "(id integer primary key autoincrement," +
                cNameName + " text," +
                cNameEmail + " text," +
                cNameAddition + " text" +
                ");";

        Log.d(LOG_TAG, "App: DBHelper-onCreate");

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(LOG_TAG, "App: DBHelper-onUpgrade");
    }
}
