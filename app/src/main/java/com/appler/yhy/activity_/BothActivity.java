package com.appler.yhy.activity_;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageButton;

import com.appler.yhy.R;
import com.appler.yhy.adapter.BothAdapter;
import com.appler.yhy.bean.YHYAllImage;

import java.util.ArrayList;
import java.util.List;

public class BothActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<YHYAllImage> data;
    private ImageButton both_back;
        private int[] imgs = {
           R.mipmap.b20, R.mipmap.b13,R.mipmap.b1,R.mipmap.b15,R.mipmap.b16,
    R.mipmap.b18, R.mipmap.b19,R.mipmap.b11, R.mipmap.b2,R.mipmap.b7,R.mipmap.b4,R.mipmap.b8,R.mipmap.b6,
                R.mipmap.b3, R.mipmap.b17,R.mipmap.b5,R.mipmap.b10,R.mipmap.b14,R.mipmap.b4,R.mipmap.b21,R.mipmap.b22,R.mipmap.b23,
                R.mipmap.b24,R.mipmap.b25,R.mipmap.b26,R.mipmap.b27,
                R.mipmap.b30,R.mipmap.b31,R.mipmap.b32,R.mipmap.b33,
                R.mipmap.b34,R.mipmap.app,R.mipmap.b35,R.mipmap.b36,
                R.mipmap.b37,R.mipmap.b38,R.mipmap.b39,R.mipmap.b40,
                R.mipmap.b41,R.mipmap.b42,R.mipmap.b43,R.mipmap.b44,
                R.mipmap.b45,R.mipmap.b46,R.mipmap.b47,R.mipmap.b48,
                R.mipmap.b49,R.mipmap.b50,R.mipmap.b51};

//    private String[] titles = {
//            "春雪", "夏雨", "秋菊", "冬梅", "玫瑰", "晓月", "如花", "燕雀"
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_both);

        recyclerView = findViewById(R.id.recyclerView);


        initData();
        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        both_back = findViewById(R.id.both_back);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        BothAdapter adpter = new BothAdapter(data, BothActivity.this);
        recyclerView.setAdapter(adpter);

        both_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    private void initData() {
        data = new ArrayList<YHYAllImage>();
        for (int i = 0; i < imgs.length; i++) {
            YHYAllImage product = new YHYAllImage("", imgs[i]);
            data.add(product);
        }
    }
}