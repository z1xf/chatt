package com.zhangxiaofeng.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhangxiaofeng.R;
import com.zhangxiaofeng.activity.NurseWork;
import com.zhangxiaofeng.activity.WordActivity;
import com.zhangxiaofeng.activity.nurse_setting;
import com.zhangxiaofeng.adapter.Adapter;
import com.zhangxiaofeng.adapter.ViewHolder;
import com.zhangxiaofeng.bean.ItemData;
import com.zhangxiaofeng.databinding.Fragment2Binding;
import com.zhangxiaofeng.myapp.MyApplication;
import com.zhangxiaofeng.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Fragment2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Fragment2Binding bd = Fragment2Binding.inflate(inflater, container, false);

        if (MyApplication.STATE) {
            bd.rv.setLayoutManager(new LinearLayoutManager(requireActivity()));
            ArrayList<ItemData> itemData = new ArrayList<>();
            itemData.add(new ItemData(R.drawable.workreports, "工作记录"));
            itemData.add(new ItemData(R.drawable.settings, "设置"));
            bd.rv.setAdapter(new Adapter<ItemData>(itemData, R.layout.item_home) {
                @Override
                protected void setBind(ViewHolder holder, ItemData data, int position) {
                    holder.setImag(data.img, R.id.iimage);
                    holder.setText(data.text, R.id.itext);
                    holder.item.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (position == 0) {
                                startActivity(new Intent(requireActivity(), NurseWork.class));
                            } else {
                                startActivity(new Intent(requireActivity(), nurse_setting.class));
                            }
                        }
                    });
                }
            });
        } else {
            bd.imageView2.setVisibility(View.GONE);
            bd.textView6.setVisibility(View.GONE);
            bd.textView7.setVisibility(View.GONE);
            bd.textView8.setVisibility(View.GONE);
            bd.textView9.setVisibility(View.GONE);
            bd.textView10.setVisibility(View.GONE);
            bd.rv.setLayoutManager(new LinearLayoutManager(requireActivity()));
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
                                FragmentActivity fragmentActivity = requireActivity();
                                Util.setDialog2(fragmentActivity, "确定退出吗？");
                            } else {
                                Util.setDialog(requireActivity(), "确定注销吗？");
                            }
                        }
                    });
                }
            });
        }
        return bd.getRoot();
    }
}

