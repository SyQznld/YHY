package com.appler.yhy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.appler.yhy.R;
import com.appler.yhy.bean.YHYAllImage;
import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by appler on 2018/5/7.
 */

public class MainAllImageAdapter extends BaseAdapter {

    private Context context;
    private List<YHYAllImage> list;
    private LayoutInflater layoutInflater;

    public MainAllImageAdapter(Context context, List<YHYAllImage> list) {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.image_layout, parent, false);
            viewHolder.cir_image = convertView.findViewById(R.id.cir_image);
            viewHolder.tv_describe = convertView.findViewById(R.id.tv_describe);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        YHYAllImage allImage = (YHYAllImage) getItem(position);
        viewHolder.tv_describe.setText(allImage.getDescribe());
        Glide.with(context).load(allImage.getDrawableId()).into(viewHolder.cir_image);
        return convertView;
    }

    class ViewHolder {
        CircleImageView cir_image;
        TextView tv_describe;
    }
}
