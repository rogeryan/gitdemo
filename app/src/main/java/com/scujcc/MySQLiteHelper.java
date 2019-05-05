package com.scujcc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
    final static String CREATE_TABLE_USER="create table users(id integer primary key autoincrement, userid text, password text)";

    public MySQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
        Log.i("Test", "Users表创建完成！");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL("alter table users1 add column age int");
            Log.d("Test", "数据升级完成，当前数据版本号：" + newVersion);
        }

    }
}
