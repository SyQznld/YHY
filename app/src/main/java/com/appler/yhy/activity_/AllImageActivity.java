package com.appler.yhy.activity_;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.appler.yhy.R;
import com.appler.yhy.adapter.MainAllImageAdapter;
import com.appler.yhy.bean.YHYAllImage;

import java.util.ArrayList;
import java.util.List;

public class AllImageActivity extends AppCompatActivity {

    private List<YHYAllImage> yhyAllImageList = new ArrayList<>();
    private MainAllImageAdapter allImageAdapter;
    private GridView gridView;
    private ImageButton ibBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_image);

        gridView = findViewById(R.id.gridView);
        ibBack = findViewById(R.id.yhy_back);

        yhyAllImageList.add(new YHYAllImage("你", R.mipmap.y3));
        yhyAllImageList.add(new YHYAllImage("真", R.mipmap.y2));
        yhyAllImageList.add(new YHYAllImage("的", R.mipmap.y1));
        yhyAllImageList.add(new YHYAllImage("真", R.mipmap.y14));
        yhyAllImageList.add(new YHYAllImage("的", R.mipmap.y5));
        yhyAllImageList.add(new YHYAllImage("真", R.mipmap.y6));
        yhyAllImageList.add(new YHYAllImage("的", R.mipmap.y7));
        yhyAllImageList.add(new YHYAllImage("真", R.mipmap.y8));
        yhyAllImageList.add(new YHYAllImage("的", R.mipmap.y9));
        yhyAllImageList.add(new YHYAllImage("很", R.mipmap.y10));
        yhyAllImageList.add(new YHYAllImage("美", R.mipmap.y12));
        yhyAllImageList.add(new YHYAllImage("啊", R.mipmap.y11));

        allImageAdapter = new MainAllImageAdapter(this, yhyAllImageList);
        gridView.setAdapter(allImageAdapter);

        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    toast("你怎么比糖还甜啊");
                }else if(position == 1){
                    toast("撒敷敷的你");
                }else if(position == 2){
                    toast("想亲你想么么哒");
                }else if(position == 3){
                    toast("哈哈 是不是可不要脸");
                }else if(position == 4){
                    toast("浓浓土味情话");
                }else if(position == 5){
                    toast("猪，你的鼻子有俩孔");
                }else if(position == 6){
                    toast("你肯定有打火机");
                }else if(position == 7){
                    toast("夏天到了，阳光都不及你耀眼");
                }else if(position == 8){
                    toast("hello啊");
                }else if(position == 9){
                    toast("我能爱酸甜味的你吗");
                }else if(position == 10){
                    toast("信球闺女");
                }else if(position == 11){
                    toast("撒浪嘿 康桑密哒 比亚内");
                }
            }
        });

    }

    private void toast(String toast) {
        Toast.makeText(AllImageActivity.this, toast, Toast.LENGTH_SHORT).show();
    }
}
