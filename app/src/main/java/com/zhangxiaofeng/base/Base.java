package com.zhangxiaofeng.base;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

public abstract class Base<T extends ViewBinding>extends AppCompatActivity {

    public T bd;
    public Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = getBind();
        setContentView(bd.getRoot());
        c = this;
        initView();

    }


    protected abstract T getBind();
    protected abstract void initView();


}