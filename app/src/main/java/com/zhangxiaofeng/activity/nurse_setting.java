package com.zhangxiaofeng.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zhangxiaofeng.R;
import com.zhangxiaofeng.adapter.Adapter;
import com.zhangxiaofeng.adapter.ViewHolder;
import com.zhangxiaofeng.base.Base;
import com.zhangxiaofeng.bean.ItemData;
import com.zhangxiaofeng.chatapp.LoginActivity;
import com.zhangxiaofeng.chatapp.SignupActivity;
import com.zhangxiaofeng.databinding.ActivityNurseSettingBinding;
import com.zhangxiaofeng.myapp.MyApplication;
import com.zhangxiaofeng.util.Util;

import java.util.ArrayList;

public class nurse_setting extends Base<ActivityNurseSettingBinding> {
    Context context;

    @Override
    protected ActivityNurseSettingBinding getBind() {
        return ActivityNurseSettingBinding.inflate(getLayoutInflater());
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = getBind();
        setContentView(bd.getRoot());
        c = this;
        initView();


    }

    @Override
    protected void initView() {
        bd.rv.setLayoutManager(new LinearLayoutManager(c));
        ArrayList<ItemData> itemData = new ArrayList<>();
        itemData.add(new ItemData(R.drawable.quit_patien, "退出登录"));
        itemData.add(new ItemData(R.drawable.logoff_patien, "注销账号"));
        bd.rv.setAdapter(new Adapter<ItemData>(itemData, R.layout.item_home) {
            @Override
            protected void setBind(ViewHolder holder, ItemData data, int position) {
                holder.setImag(data.img, R.id.iimage);
                holder.setText(data.text, R.id.itext);
                holder.item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (position == 0) {

                            //FragmentActivity Context;
                            Util.setDialog4(context, "确定退出吗？");
                        } else {
                            Util.setDialog3(c, "确定注销吗？");
                        }
                    }
                });
            }
        });

    }
}