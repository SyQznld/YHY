package com.appler.yhy.activity_;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.appler.yhy.R;
import com.appler.yhy.adapter.SYImageAdapter;
import com.appler.yhy.bean.YHYAllImage;

import java.util.ArrayList;
import java.util.List;

public class SYYImageActivity extends AppCompatActivity {

    private ImageButton ibBack;
    private GridView syy_gridView;

    private List<YHYAllImage> yhyAllImageList = new ArrayList<>();
    private SYImageAdapter allImageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syyimage);

        ibBack = findViewById(R.id.syy_back);
        syy_gridView = findViewById(R.id.syy_gridView);


        initBean();

        allImageAdapter = new SYImageAdapter(this, yhyAllImageList);
        syy_gridView.setAdapter(allImageAdapter);

        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        syy_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    toast("失神岁月一去无返，偷来的时光总是太短");
                } else if (position == 1) {
                    toast("你是九月夏天 滚烫的浪");
                } else if (position == 2) {
                    toast("我想成为你的鹿。");
                } else if (position == 3) {
                    toast("我多想拥抱你 可惜时光之里 山南水北 可惜你我中间人来人往");
                } else if (position == 4) {
                    toast("明天过后");
                } else if (position == 5) {
                    toast("I want to give you a little case.");
                } else if (position == 6) {
                    toast("先说爱的人先不爱，后动心的不死心。");
                } else if (position == 7) {
                    toast("遗憾与年轻总是一起 你的呢");
                } else if (position == 8) {
                    toast("Wonderful You.❤");
                } else if (position == 9) {
                    toast("听说悬崖有棵长生树");
                } else if (position == 10) {
                    toast("轻轻缓缓细细慢慢");
                } else if (position == 11) {
                    toast("我养的猫长大了茶树也结了茶  喜羊羊的结局被灰太狼吃掉啦  欢喜的当初变成了结尾的冤家  你还在远方寻那朵不凋零的花");
                }
            }
        });

    }

    private void initBean() {
        yhyAllImageList.add(new YHYAllImage("我", R.mipmap.syy3));
        yhyAllImageList.add(new YHYAllImage("是", R.mipmap.syy2));
        yhyAllImageList.add(new YHYAllImage("不", R.mipmap.syy1));
        yhyAllImageList.add(new YHYAllImage("是", R.mipmap.syy4));
        yhyAllImageList.add(new YHYAllImage("也", R.mipmap.syy8));
        yhyAllImageList.add(new YHYAllImage("是", R.mipmap.syy6));
        yhyAllImageList.add(new YHYAllImage("可", R.mipmap.syy7));
        yhyAllImageList.add(new YHYAllImage("美", R.mipmap.syy11));
        yhyAllImageList.add(new YHYAllImage("的", R.mipmap.syy9));
        yhyAllImageList.add(new YHYAllImage("小", R.mipmap.syy10));
        yhyAllImageList.add(new YHYAllImage("可", R.mipmap.syy5));
        yhyAllImageList.add(new YHYAllImage("爱", R.mipmap.syy12));
    }

    private void toast(String toast) {
        Toast.makeText(SYYImageActivity.this, toast, Toast.LENGTH_SHORT).show();
    }
}