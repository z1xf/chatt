package com.zhangxiaofeng.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class Adapter<T> extends RecyclerView.Adapter<ViewHolder> {

    List<T> list;

    int layout;

    public Adapter(List<T> list, int layout) {
        this.list = list;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(layout, parent, false));
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        setBind(holder, list.get(position), position);

    }

    protected abstract void setBind(ViewHolder holder, T data, int position);


    @Override
    public int getItemCount() {
        return list.size();
    }
}
