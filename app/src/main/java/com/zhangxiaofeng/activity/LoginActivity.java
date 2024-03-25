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
import com.zhangxiaofeng.MainActivity;
import com.zhangxiaofeng.activity2.RoleWrong;
import com.zhangxiaofeng.databinding.ActivityLoginBinding;
import com.zhangxiaofeng.myapp.MyApplication;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    private Boolean state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Context context = this;
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.loginName.getText().toString();
                String number = binding.loginNum.getText().toString();
                state = false;
                if (name.equals("") || number.equals(""))
                    Toast.makeText(LoginActivity.this, "请完善个人信息！", Toast.LENGTH_SHORT).show();
                else {
                    if (MyApplication.STATE) {
                        UserDao userDao = new UserDao(context);
                        for (UserData a : userDao.getAll()) {
                            if (a.getUsername().equals(name)) {
                                if (a.getPassword().equals(number)) {
                                    state = true;
                                    break;
                                }
                            }
                            state = false;
                        }
                        if (!state) {
                            Toast.makeText(context, "请检查账号密码状态", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(context, RoleWrong.class));
                        } else {
                            MyApplication.Username = name;
                            startActivity(new Intent(context, MainActivity.class));
                            Toast.makeText(context, "登录成功！", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        UserDao2 userDao2 = new UserDao2(context);
                        for (UserData a : userDao2.getAll()) {
                            if (a.getUsername().equals(name)) {
                                if (a.getPassword().equals(number)) {
                                    state = true;
                                    MyApplication.ID = a.get_id();
                                    break;
                                }
                            }
                            state = false;
                        }
                        if (!state) {
                            Toast.makeText(context, "请检查账号密码状态", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(context, RoleWrong.class));
                        } else {
                            MyApplication.Username = name;
                            startActivity(new Intent(context, MainActivity.class));
                            Toast.makeText(context, "登录成功！", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
        binding.signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}