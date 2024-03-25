package com.zhangxiaofeng.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zhangxiaofeng.R;
import com.zhangxiaofeng.base.Base;
import com.zhangxiaofeng.databinding.ActivityChooseRoleBinding;
import com.zhangxiaofeng.myapp.MyApplication;

public class ChooseRole extends Base<ActivityChooseRoleBinding> {

    @Override
    protected ActivityChooseRoleBinding getBind() {
        return ActivityChooseRoleBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {

        bd.but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.STATE = true;
                startActivity(new Intent(c, LoginActivity.class));
            }
        });

        bd.but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.STATE = false;
                startActivity(new Intent(c, LoginActivity.class));
            }
            //Botton ListView RecycleView Sqlite
            //纯本文   json格式  消息格式：谁发的+发给谁 +内容
            //网络请求 retrofit框架
            //点9 对话框
            // 历史消息 入数据库
        });
    }
}