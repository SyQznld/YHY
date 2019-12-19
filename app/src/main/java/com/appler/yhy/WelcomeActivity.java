package com.appler.yhy;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.appler.yhy.activity_.SecondActivity;

import de.hdodenhof.circleimageview.CircleImageView;
import scut.carson_ho.kawaii_loadingview.Kawaii_LoadingView;

public class WelcomeActivity extends AppCompatActivity {

    private TextView tvLoad;
    private RelativeLayout rlLoad;
    private Kawaii_LoadingView loadingView;
    private RelativeLayout rlHead;
    private CircleImageView head;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        rlLoad = findViewById(R.id.rl_load);
        tvLoad = findViewById(R.id.tv_load);
        loadingView = findViewById(R.id.kawaii_loadingView);
        rlHead = findViewById(R.id.rl_head);
        head = findViewById(R.id.head);


        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog dialog = null;
                AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);
                View view = LayoutInflater.from(WelcomeActivity.this).inflate(R.layout.login, null);
                final EditText etAccount = view.findViewById(R.id.et_username);
                final EditText etPwd = view.findViewById(R.id.et_pwd);
                builder.setView(view);
                builder.setTitle("进入于可爱的专属APP");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String acc = etAccount.getText().toString();
                        String pwd = etPwd.getText().toString();

                        if ("yhy".equals(acc) && "69853".equals(pwd)) {
                            rlHead.setVisibility(View.GONE);
                            rlLoad.setVisibility(View.VISIBLE);
                            loadingView.startMoving();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(WelcomeActivity.this, SecondActivity.class);
                                    startActivity(intent);
                                    loadingView.stopMoving();
                                    rlLoad.setVisibility(View.GONE);
                                }
                            }, 5000);

                        } else {
                            Toast.makeText(WelcomeActivity.this, "输入正确的账号密码才能登录，信球~", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog = builder.create();
                dialog.show();
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);

            }
        });
    }
}
