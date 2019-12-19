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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.appler.yhy.activity_.SecondActivity;
import com.appler.yhy.utils.ThumbsUpLayout;
import com.luolc.emojirain.EmojiRainLayout;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private ImageButton ibBack;
    private LinearLayout ll1;
    private LinearLayout ll2;
    private LinearLayout ll3;
    private LinearLayout ll4;
    private LinearLayout ll5;

    private LinearLayout ll6;
    private LinearLayout ll7;
    private LinearLayout ll8;
    private LinearLayout ll9;
    private LinearLayout ll10;

    private CircleImageView ibThumb;
    private ThumbsUpLayout thumbsUpLayout;

    private EmojiRainLayout emojiRainLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();


        emojiRainLayout.addEmoji(R.drawable.ic_heart1);//emoji_1_3
        emojiRainLayout.addEmoji(R.drawable.ic_ball);//emoji_2_3
        emojiRainLayout.addEmoji(R.drawable.ic_flower);//emoji_3_3
        emojiRainLayout.addEmoji(R.drawable.ic_heart2);//emoji_4_3
        emojiRainLayout.addEmoji(R.drawable.ic_smile);//emoji_5_3

        // 每一波掉落的emoji个数，默认6个
        emojiRainLayout.setPer(10);
        // 掉落动画持续的总时长，默认8000ms
        emojiRainLayout.setDuration(7200);
        // 每个emoji掉落时长的平均值，默认2400ms
        emojiRainLayout.setDropDuration(2400);
        // 掉落频率，即每两拨的时间间隔，默认500ms
        emojiRainLayout.setDropFrequency(500);
        emojiRainLayout.startDropping();


        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                emojiRainLayout.stopDropping();
            }
        });


        ibThumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thumbsUpLayout.addThumbs();
            }
        });


        ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                initAlertDialog("输入爱的昵称", "信球", ll2, ll1);
            }
        });
        ll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                initAlertDialog("介绍时候的尊称", "大爷", ll3, ll2);
            }
        });

        ll3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                initAlertDialog("测试你的求生欲", "猕猴桃", ll4, ll3);
            }
        });
        ll4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                initAlertDialog("魔性逗笑神曲", "She", ll5, ll4);
            }
        });
        ll5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                initAlertDialog("社会人标配", "小猪佩奇", ll6, ll5);
            }
        });


        ll6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                initAlertDialog("吃的最为重要", "火锅", ll7, ll6);
            }
        });

        ll7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                initAlertDialog("恼火被看错的年纪", "四十多", ll8, ll7);
            }
        });
        ll8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                initAlertDialog("你最花痴的男人", "李敏镐", ll9, ll8);
            }
        });

        ll9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                initAlertDialog("欲美欲仙的饰品", "头纱", ll10, ll9);
            }
        });

        ll10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                AlertDialog dialog = null;
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.question, null);
                final EditText etSix = view.findViewById(R.id.et_six);
                final TextView tv_baidu = view.findViewById(R.id.tv_baidu);
                TextView tv_sure = view.findViewById(R.id.tv_sure);
                builder.setView(view);
                builder.setTitle("一道难题");

                tv_sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if ("six".equals(etSix.getText().toString())) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                                    startActivity(intent);
                                }
                            }, 1500);
                        } else {
                            Toast.makeText(MainActivity.this, "错错错 答案不对", Toast.LENGTH_SHORT).show();
                            tv_baidu.setVisibility(View.VISIBLE);
                        }
                    }
                });

                tv_baidu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "你百度啊 怕是缺心眼儿？", Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                AlertDialog dialog = null;
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.sin_answer, null);
                                builder.setView(view);
                                builder.setTitle("ANSWER");
                                builder.setMessage("还是给你说答案吧，憋不住。");
                                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(MainActivity.this, "是不是又学到了一高大上的套路。", Toast.LENGTH_SHORT).show();
                                        dialogInterface.dismiss();
                                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                                        startActivity(intent);

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
                        }, 1500);
                    }
                });
                dialog = builder.create();
                dialog.show();
//                dialog.setCancelable(false);
//                dialog.setCanceledOnTouchOutside(false);
            }
        });


    }

    private void initAlertDialog(String title, final String answer, final LinearLayout ll, final LinearLayout llPre) {
        AlertDialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.edit_layout, null);
        final EditText etContent = view.findViewById(R.id.et_content);
        builder.setView(view);
        builder.setTitle(title);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (answer.trim().equals(etContent.getText().toString().trim())) {
                    ll.setVisibility(View.VISIBLE);
                    llPre.setEnabled(false);
                } else {
                    Toast.makeText(MainActivity.this, "不对，好好想想", Toast.LENGTH_SHORT).show();
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

    private void initView() {
        ibBack = findViewById(R.id.main_cie_head);
        ll1 = findViewById(R.id.ll1);
        ll2 = findViewById(R.id.ll2);
        ll3 = findViewById(R.id.ll3);
        ll4 = findViewById(R.id.ll4);
        ll5 = findViewById(R.id.ll5);

        ll6 = findViewById(R.id.ll6);
        ll7 = findViewById(R.id.ll7);
        ll8 = findViewById(R.id.ll8);
        ll9 = findViewById(R.id.ll9);
        ll10 = findViewById(R.id.ll10);

        ibThumb = findViewById(R.id.cir_thumb);
        thumbsUpLayout = findViewById(R.id.thumbsUpLayout);

        emojiRainLayout = findViewById(R.id.emojiRainLayout);

    }
}
