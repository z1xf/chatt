package com.zhangxiaofeng.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zhangxiaofeng.Database.UserDao;
import com.zhangxiaofeng.Database.UserDao2;
import com.zhangxiaofeng.Databean.UserData;
import com.zhangxiaofeng.databinding.ActivitySignupBinding;
import com.zhangxiaofeng.myapp.MyApplication;


public class SignupActivity extends AppCompatActivity {
    ActivitySignupBinding binding;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.singupName.getText().toString();
                String number = binding.singupNum.getText().toString();
                if (name.equals("") || number.equals(""))
                    Toast.makeText(SignupActivity.this, "请填写完整信息！", Toast.LENGTH_SHORT).show();
                else {

                    if (MyApplication.STATE){
                        UserDao userDao = new UserDao(context);
                        UserData userInfo = new UserData(userDao.getMaxId() + 1, name, number);
                        int res = userDao.add(userInfo);
                        if (res == 200) {
                            Toast.makeText(context, "注册成功！",
                                    Toast.LENGTH_SHORT).show();
                            binding.singupName.setText("");
                            binding.singupNum.setText("");
                        } else {
                            Toast.makeText(context, "该用户已被注册！", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        UserDao2 userDao2 = new UserDao2(context);
                        UserData userInfo = new UserData(userDao2.getMaxId() + 1, name, number);
                        int res = userDao2.add(userInfo);
                        if (res == 200) {
                            Toast.makeText(context, "注册成功！",
                                    Toast.LENGTH_SHORT).show();
                            binding.singupName.setText("");
                            binding.singupNum.setText("");
                        } else {
                            Toast.makeText(context, "该用户已被注册！", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        binding.loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}