package com.zhangxiaofeng.util;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;

import com.zhangxiaofeng.Database.UserDao;
import com.zhangxiaofeng.Database.UserDao2;
import com.zhangxiaofeng.myapp.MyApplication;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Util {

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getTime() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return currentDate.format(formatter);
    }

    /***
     * 弹窗
     * @param context
     * @param message
     */
    public static void setDialog(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        new UserDao2(context).detele(MyApplication.ID);
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public static void setDialog3(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        new UserDao(context).detele(MyApplication.ID);
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }



    /***
     * 弹窗
     * @param fragmentActivity
     * @param message
     */
    public static void setDialog2(FragmentActivity fragmentActivity , String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(fragmentActivity);
        builder.setMessage(message)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        fragmentActivity.finishAffinity();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void setDialog4(Context context , String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //context.fileList();//
                        System.exit(0);
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
