package com.zhangxiaofeng.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.zhangxiaofeng.R;
import com.zhangxiaofeng.adapter.Adapter;
import com.zhangxiaofeng.adapter.ViewHolder;
import com.zhangxiaofeng.base.Base;
import com.zhangxiaofeng.bean.GetData;
import com.zhangxiaofeng.databinding.ActivityMesBinding;

import java.util.ArrayList;
import java.util.List;

public class MesActivity extends Base<ActivityMesBinding> {

    List<GetData.DataDTO> list = new ArrayList<>();
    private int img;

    @Override
    protected ActivityMesBinding getBind() {
        return ActivityMesBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        img = getIntent().getIntExtra("img", R.drawable.patient_e);
        bd.imageView4.setImageResource(img);
        bd.textView12.setText(getIntent().getStringExtra("name"));
        bd.textView13.setText(getIntent().getStringExtra("age"));
        bd.textView14.setText(getIntent().getStringExtra("text"));
        list.add(new GetData.DataDTO("2", "", getIntent().getStringExtra("text")));
        fun(list);
        //医生
        bd.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = bd.editTextTextPassword.getText().toString();
                if (s.equals("")) {
                    Toast.makeText(c, "不能为空哟~", Toast.LENGTH_SHORT).show();
                } else {
                    list.add(new GetData.DataDTO("", "", s));
                    fun(list);
                    bd.editTextTextPassword.setText("");

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            list.add(new GetData.DataDTO("2", "", "好的，谢谢医生！"));
                            fun(list);
                        }
                    }, 1000);
                }
            }
        });


    }

    public void fun(List<GetData.DataDTO> data) {
        bd.rv.setLayoutManager(new LinearLayoutManager(c));
        bd.rv.setAdapter(new Adapter<GetData.DataDTO>(data, R.layout.item_message) {
            @Override
            protected void setBind(ViewHolder holder, GetData.DataDTO data, int position) {
                if (data.sender.equals("2")) {
                    holder.item.findViewById(R.id.img2).setVisibility(View.GONE);
                    holder.item.findViewById(R.id.text2).setVisibility(View.GONE);
                    holder.setImag(img, R.id.img1);
                    holder.setText(data.message, R.id.text1);
                } else {
                    holder.item.findViewById(R.id.img1).setVisibility(View.GONE);
                    holder.item.findViewById(R.id.text1).setVisibility(View.GONE);
                    holder.setImag(R.drawable.doctor_, R.id.img2);
                    holder.setText(data.message, R.id.text2);
                }
            }
        });
    }
}