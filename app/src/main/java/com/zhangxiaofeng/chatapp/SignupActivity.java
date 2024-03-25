package com.zhangxiaofeng.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

//import com.zhangxiaofeng.chatapp.databinding.ActivitySignupBinding;
import com.zhangxiaofeng.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity {
    ActivitySignupBinding binding;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new DatabaseHelper(this);
        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.singupName.getText().toString();
                String number = binding.singupNum.getText().toString();
                if (name.equals("")||number.equals(""))
                    Toast.makeText(SignupActivity.this,"请填写完整信息！",Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkUserName = databaseHelper.checkName(name);
                    if (checkUserName == false){
                        Boolean insert = databaseHelper.insertData(name,number);
                        if (insert == true){
                            Toast.makeText(SignupActivity.this,"注册成功，请登录！",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(SignupActivity.this,"注册失败，请重试！",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(SignupActivity.this,"信息错误！",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        binding.loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}