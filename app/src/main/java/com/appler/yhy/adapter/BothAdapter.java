package com.appler.yhy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appler.yhy.R;
import com.appler.yhy.bean.YHYAllImage;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by appler on 2018/5/10.
 */

public class BothAdapter extends RecyclerView.Adapter<BothAdapter.MyHolder> {
    private List<YHYAllImage> data;
    private Context context;


    public BothAdapter(List<YHYAllImage> data, Context context) {
        this.data = data;
        this.context = context;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.both,parent,false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
//        holder.img.setImageResource(data.get(position).getDrawableId());
        Glide.with(context).load(data.get(position).getDrawableId()).into(holder.img);
        holder.tv.setText(data.get(position).getDescribe());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView tv;

        public MyHolder(View view) {
            super(view);
            img = view.findViewById(R.id.imageview);
            tv = view.findViewById(R.id.textView);
        }
    }
}
