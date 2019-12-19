package com.appler.yhy.activity_;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.appler.yhy.R;

public class ManageActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton ibBack;
    private RelativeLayout rlHead;
    private LinearLayout ll_sll;
    private LinearLayout ll_cqh;
    private LinearLayout ll_syy;
    private LinearLayout ll_yhy;
    private LinearLayout ll_both;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        ibBack = findViewById(R.id.ib_personel_back);
        rlHead = findViewById(R.id.rl_personel);
        ll_sll = findViewById(R.id.ll_sll);
        ll_cqh = findViewById(R.id.ll_cqh);
        ll_syy = findViewById(R.id.ll_syy);
        ll_yhy = findViewById(R.id.ll_yhy);
        ll_both = findViewById(R.id.ll_both);

        ibBack.setOnClickListener(this);
        rlHead.setOnClickListener(this);
        ll_sll.setOnClickListener(this);
        ll_cqh.setOnClickListener(this);
        ll_syy.setOnClickListener(this);
        ll_yhy.setOnClickListener(this);
        ll_both.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_personel_back:
                finish();
                break;
            case R.id.rl_personel:
                Intent intent = new Intent(ManageActivity.this, PersonelActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_sll:
                break;
            case R.id.ll_cqh:
                break;
            case R.id.ll_syy:
                Intent intent1 = new Intent(ManageActivity.this, SYYImageActivity.class);
                startActivity(intent1);
                break;
            case R.id.ll_yhy:
                Intent intentYhy = new Intent(ManageActivity.this, AllImageActivity.class);
                startActivity(intentYhy);
                break;
            case R.id.ll_both:
                Intent intentBoth = new Intent(ManageActivity.this, BothActivity.class);
                startActivity(intentBoth);
                break;

        }
    }
}
