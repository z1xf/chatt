package com.zhangxiaofeng.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    public View item;
    public ViewHolder(View itemView) {
        super(itemView);
        item = itemView;
    }

    public void setText(String text, int id){
        ((TextView)item.findViewById(id)).setText(text);
    }


    public void setImag(int img, int id){
        ((ImageView)item.findViewById(id)).setImageResource(img);
    }


}
