package com.zhangxiaofeng.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.zhangxiaofeng.R;
import com.zhangxiaofeng.activity.MesActivity;
import com.zhangxiaofeng.adapter.Adapter;
import com.zhangxiaofeng.adapter.ViewHolder;
import com.zhangxiaofeng.bean.GetData;
import com.zhangxiaofeng.bean.ItemData;
import com.zhangxiaofeng.bean.ItemData2;
import com.zhangxiaofeng.databinding.Fragment1Binding;
import com.zhangxiaofeng.http.Http;
import com.zhangxiaofeng.myapp.MyApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fragment1 extends Fragment {

    private Fragment1Binding bd;
    List<GetData.DataDTO> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        bd = Fragment1Binding.inflate(inflater, container, false);

        if (MyApplication.STATE) {
            bd.editTextTextPassword.setVisibility(View.GONE);
            bd.button.setVisibility(View.GONE);
            bd.rv.setLayoutManager(new LinearLayoutManager(requireActivity()));
            List<ItemData2> itemData = Arrays.asList(
                    new ItemData2(R.drawable.patient_e, "小丽", "18岁 女", "301病房5号床"),
                    new ItemData2(R.drawable.patient_d, "老张", "64岁 男", "402病房3号床"),
                    new ItemData2(R.drawable.patient_c, "小王", "26岁 男", "206病房1号床"),
                    new ItemData2(R.drawable.patient_b, "小美", "28岁 女", "208病房6号床"),
                    new ItemData2(R.drawable.patient_a, "老刘", "72岁 男", "411病房2号床")
            );
            bd.rv.setAdapter(new Adapter<ItemData2>(itemData, R.layout.item_home2) {
                @Override
                protected void setBind(ViewHolder holder, ItemData2 data, int position) {
                    holder.setImag(data.img, R.id.imageView);
                    holder.setText(data.text, R.id.textView3);
                    holder.setText(data.title, R.id.textView2);
                    holder.setText(data.rests, R.id.textView4);
                    holder.item.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //跳转到对话界面
                            startActivity(new Intent(
                                            requireActivity(), MesActivity.class
                                    )
                                            .putExtra("img", data.img)
                                            .putExtra("name", data.title)
                                            .putExtra("age", data.rests)
                                            .putExtra("text", data.text)
                            );
                        }
                    });
                }
            });
        } else {
            //病人问医生
            bd.rv.setVisibility(View.VISIBLE);
            bd.editTextTextPassword.setVisibility(View.VISIBLE);
            bd.button.setVisibility(View.VISIBLE);
            bd.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String s = bd.editTextTextPassword.getText().toString();
                    if (s.equals("")) {
                        Toast.makeText(requireActivity(), "不能为空哟~", Toast.LENGTH_SHORT).show();
                    } else {
                        list.add(new GetData.DataDTO("", "", s));
                        fun(list);
                        bd.editTextTextPassword.setText("");

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                list.add(new GetData.DataDTO("2", "", "收到请求，请耐心等待！"));
                                fun(list);
                            }
                        }, 1000);
                    }
                }
            });

        }


        return bd.getRoot();
    }


    public void fun(List<GetData.DataDTO> data) {
        bd.rv.setLayoutManager(new LinearLayoutManager(requireActivity()));
        bd.rv.setAdapter(new Adapter<GetData.DataDTO>(data, R.layout.item_message) {
            @Override
            protected void setBind(ViewHolder holder, GetData.DataDTO data, int position) {
                if (data.sender.equals("2")) {
                    holder.item.findViewById(R.id.img2).setVisibility(View.GONE);
                    holder.item.findViewById(R.id.text2).setVisibility(View.GONE);
                    holder.setImag(R.drawable.doctor_, R.id.img1);
                    holder.setText(data.message, R.id.text1);
                } else {
                    holder.item.findViewById(R.id.img1).setVisibility(View.GONE);
                    holder.item.findViewById(R.id.text1).setVisibility(View.GONE);
                    holder.setImag(R.drawable.patient_e, R.id.img2);
                    holder.setText(data.message, R.id.text2);
                }
            }
        });
    }
}