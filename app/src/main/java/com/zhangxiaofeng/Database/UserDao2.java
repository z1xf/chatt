package com.zhangxiaofeng.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zhangxiaofeng.Databean.UserData;

import java.util.ArrayList;
import java.util.List;

public class UserDao2 {
    private DBOpenHelper helper;// 创建 DBOpenHelper 对象
    private SQLiteDatabase db;// 创建 SQLiteDatabase 对象

    public UserDao2(Context context) {
        helper = new DBOpenHelper(context);// 初始化 DBOpenHelper 对象
    }


    /**
     * 增加信息
     *
     * @param userInfo
     */
    public int add(UserData userInfo) {
        db = helper.getWritableDatabase();// 初始化 SQLiteDatabase 对象
        //防止重复用户名
        for (UserData a : getAll()) {
            if (a.getUsername().equals(userInfo.getUsername())) {
                return 400;
            }
        }
        db.execSQL("insert into user2 (_id,username,password) values (?,?,?)",
                new Object[]{
                        userInfo.get_id(),
                        userInfo.getUsername(),
                        userInfo.getPassword(),});// 执行添加便签信息操作
        return 200;
    }

    /**
     * 获取全部信息
     *
     * @return
     */
    public List<UserData> getAll() {
        List<UserData> list = new ArrayList<>(); // 创建集合对象
        db = helper.getWritableDatabase(); // 初始化 SQLiteDatabase 对象
        Cursor cursor = db.rawQuery("SELECT * FROM user2", null); // 查询全部数据

        while (cursor.moveToNext()) { // 遍历查询结果
            // 从游标中读取每行数据的字段值
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String username = cursor.getString(cursor.getColumnIndex("username"));
            String password = cursor.getString(cursor.getColumnIndex("password"));

            // 创建 UserInfo 对象，并将字段值设置到对象中
            UserData userInfo = new UserData(
                    id,
                    username,
                    password
            );

            // 将 UserInfo 对象添加到集合中
            list.add(userInfo);
        }

        cursor.close(); // 关闭游标
        return list; // 返回集合
    }

    /**
     * 获取最大编号
     *
     * @return
     */
    public int getMaxId() {
        db = helper.getWritableDatabase();// 初始化 SQLiteDatabase 对象
        Cursor cursor = db.rawQuery("select max(_id) from user2", null);// 获取便签信息表中的最大编号
        while (cursor.moveToLast()) {// 访问 Cursor 中的最后一条数据
            return cursor.getInt(0);// 获取访问到的数据，即最大编号
        }
        return 0;// 如果没有数据，则返回 0
    }


    /**
     * 查找信息
     *
     * @param id
     * @return
     */
    public UserData find(int id) {
        db = helper.getWritableDatabase();// 初始化 SQLiteDatabase 对象
        Cursor cursor = db.rawQuery(
                "select _id,flag from user2 where _id = ?",
                new String[]{String.valueOf(id)});// 根据编号查找便签信息，并存储到 Cursor 类中

        if (cursor.moveToNext())// 遍历查找到的便签信息
        {

        }
        return null;// 如果没有信息，则返回 null
    }

    /**
     * 更新信息
     *
     * @param tb_flag
     */
    public void update(UserData tb_flag) {
        db = helper.getWritableDatabase();// 初始化 SQLiteDatabase 对象
        db.execSQL("update user2 set username = ?, password = ? where _id = ?",
                new Object[]{tb_flag.getUsername(), tb_flag.getPassword(), tb_flag.get_id()});// 执行修改便签信息操作
    }

    /**
     * 刪除信息
     *
     * @param ids
     */
    public void detele(Integer... ids) {
        if (ids.length > 0)// 判断是否存在要删除的 id
        {
            StringBuffer sb = new StringBuffer();// 创建 StringBuffer 对象
            for (int i = 0; i < ids.length; i++)// 遍历要删除的 id 集合
            {
                sb.append('?').append(',');// 将删除条件添加到 StringBuffer 对象中
            }
            sb.deleteCharAt(sb.length() - 1);// 去掉最后一个“,“字符
            db = helper.getWritableDatabase();// 创建 SQLiteDatabase 对象
            // 执行删除便签信息操作
            db.execSQL("delete from tb_flag where _id in (" + sb + ")",
                    (Object[]) ids);
        }
    }

    /**
     * 获取总记录数
     *
     * @return
     */
    public long getCount() {
        db = helper.getWritableDatabase();// 初始化 SQLiteDatabase 对象
        Cursor cursor = db.rawQuery("select count(_id) from user2", null);// 获取便签信息的记录数
        if (cursor.moveToNext())// 判断 Cursor 中是否有数据
        {
            return cursor.getLong(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回 0
    }

}

