package com.scujcc.moviepost;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.scujcc.MySQLiteHelper;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private Button testButton, delButton, upgradeButton, addButton, queryButton;
    private MySQLiteHelper helper;
    private SQLiteDatabase mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main222);

        testButton = findViewById(R.id.sqltest);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helper = new MySQLiteHelper(MainActivity.this, "usersdb.db", null,1);
                mydb = helper.getWritableDatabase();
              
            }
        });

        delButton = findViewById(R.id.delButton);
        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mydb.isOpen()) {
                    //mydb.close();
                }
                File db = new File(mydb.getPath());
                SQLiteDatabase.deleteDatabase(db222);
                Log.i("Test", "数据库已删除");
            }
        });

        upgradeButton = findViewById(R.id.upgradeButton);
        upgradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helper = new MySQLiteHelper(MainActivity.this, "usersdb.db", null,2);
                mydb = helper.getWritableDatabase();
                Log.d("Test", "数据库路径："+mydb.getPath());
            }
        });

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mydb == null) {
                    Log.w("Test", "数据库为空，是不是忘记打开了？");
                    return;
                }
                mydb.insert("users", null, userData());
                Log.d("Test", "完成新增用户数据的操作");
            }
        });

        queryButton = findViewById(R.id.queryAllButton);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mydb == null) {
                    Log.w("Test", "数据库为空，是不是忘记打开了？");
                    return;
                }
                Cursor c = mydb.query("users", null, null,null,null,null,null,null);
                c.moveToFirst();
                do {
                    String userid = c.getString(c.getColumnIndex("userid"));
                    Log.i("Test", "用户id为："+userid);
                } while (c.moveToNext());
            }
        });

    }

    private ContentValues userData() {
        ContentValues cv = new ContentValues();
        cv.put("userid", "JC20061234");
        cv.put("password", "123456");
        cv.put("age", 18);
        return cv;
    }
}
