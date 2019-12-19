package com.appler.yhy.activity_;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.appler.yhy.R;
import com.appler.yhy.utils.BezierLayout;

public class PersonelActivity extends AppCompatActivity {

    private ImageButton ib_pdetail_back;
    private BezierLayout bezierLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personel);

        ib_pdetail_back = findViewById(R.id.ib_pdetail_back);
        bezierLayout = findViewById(R.id.bl_bezierLayout);
        ib_pdetail_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
