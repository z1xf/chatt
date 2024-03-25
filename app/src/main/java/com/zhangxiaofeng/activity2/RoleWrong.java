package com.zhangxiaofeng.activity2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.zhangxiaofeng.R;
import com.zhangxiaofeng.base.Base;
import com.zhangxiaofeng.databinding.ActivityRoleWrongBinding;

public class RoleWrong extends Base<ActivityRoleWrongBinding> {


    @Override
    protected ActivityRoleWrongBinding getBind() {
        return ActivityRoleWrongBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        bd.relogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}