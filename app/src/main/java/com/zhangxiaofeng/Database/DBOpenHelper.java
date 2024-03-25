package com.zhangxiaofeng.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;// 定义数据库版本号
    private static final String DBNAME = "My_Database.db";// 定义数据库名

    public DBOpenHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) // 创建数据库
    {
        String user = "CREATE TABLE IF NOT EXISTS user ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + "username VARCHAR NOT NULL,"
                + "password VARCHAR NOT NULL,"
                + "name VARCHAR"
                + ");";
        db.execSQL(user);// 表一创建普通用户表

        String user2 = "CREATE TABLE IF NOT EXISTS user2 ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + "username VARCHAR NOT NULL,"
                + "password VARCHAR NOT NULL,"
                + "name VARCHAR"
                + ");";
        db.execSQL(user2);// 表一创建医生用户表

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) // 覆写基类的 onUpgrade 方法，以便数据库版本更新
    {
    }
}
