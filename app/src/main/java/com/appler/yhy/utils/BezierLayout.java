package com.appler.yhy.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.appler.yhy.R;

import java.util.Random;

/**
 * Created by appler on 2018/5/15.
 */

public class BezierLayout extends RelativeLayout {
    Drawable[] mDrawables=new Drawable[]{getResources().getDrawable(R.drawable.ic_heart1),
            getResources().getDrawable(R.drawable.ic_ball),getResources().getDrawable(R.drawable.ic_flower),getResources().getDrawable(R.drawable.ic_heart2),
            getResources().getDrawable(R.mipmap.ic_launcher_round),getResources().getDrawable(R.drawable.ic_smile)};
    private LayoutParams mParams;

    private int mDrawWidth;
    private int mDrawHeight;
    private int mHeight;
    private int mWidth;
    private Random mRandom=new Random();
    public BezierLayout(Context context) {
        this(context,null);
    }

    public BezierLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BezierLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        mDrawWidth=mDrawables[2].getIntrinsicWidth();
        mDrawHeight=mDrawables[2].getIntrinsicHeight();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //对按下事件监听，获取点击的位置
        if(event.getAction()== MotionEvent.ACTION_DOWN){
            PointF point=new PointF();
            point.set(event.getX(),event.getY());
            //每点击一次添加一个礼物
            addPresent(point);
            return true;
        }
        return super.onTouchEvent(event);

    }

    private void addPresent(PointF point) {
        mParams=new LayoutParams(mDrawWidth,mDrawHeight);
        //设置左边距为落下点-图片宽度的一半（这是为了让图片的中心为落下手指位置）
        mParams.leftMargin=(int)(point.x-mDrawWidth/2);
        //设置上边距为落下点-图片高度的一半（这是为了让图片的中心为落下手指位置）
        mParams.topMargin=(int)(point.y-mDrawHeight/2);
        ImageView imageView=new ImageView(getContext());
        //随机设置图片
        imageView.setImageDrawable(mDrawables[mRandom.nextInt(6)]);
        //设置动画
        AnimatorSet animatorset=getObjectAnimator(imageView,point);
        animatorset.setTarget(imageView);
        addView(imageView,mParams);
        animatorset.start();
    }
    public AnimatorSet getObjectAnimator(final ImageView imageview, PointF point) {
        //动画：放大+透明度+贝塞尔曲线
        //放大+透明度
        ObjectAnimator scalexanimator=ObjectAnimator.ofFloat(imageview,"scaleX",0.2f,0.5f);
        ObjectAnimator scaleyanimator=ObjectAnimator.ofFloat(imageview,"scaleY",0.2f,0.5f);
        ObjectAnimator alphaanimator=ObjectAnimator.ofFloat(imageview,"alpha",1.0f,0f);
        AnimatorSet set=new AnimatorSet();
        set.playTogether(scalexanimator,scaleyanimator,alphaanimator);
        set.setDuration(1000);
        //贝塞尔曲线
        ValueAnimator bezierAnimator=getBezierAnimator(imageview,point);
        //将放大+透明度+贝塞尔曲线三种动画添加在一起
        AnimatorSet sets=new AnimatorSet();
        sets.playTogether(set,bezierAnimator);
        //设置动画的对象与时间
        sets.setTarget(imageview);
        sets.setDuration(3000);
        return sets;
    }

    public ValueAnimator getBezierAnimator(final ImageView imageview,PointF point) {
        //贝塞尔曲线需要四个点
        //起点
        PointF point0=new PointF();
        point0.x=point.x-mDrawWidth/2;
        point0.y=point.y-mDrawHeight/2;
        //第一个中间点
        final PointF point1=getTooglePoint(1,point);
        //d第二个中间点
        PointF point2=getTooglePoint(2,point);
        //最后一个点
        PointF point3=new PointF(mRandom.nextInt(mWidth),0);
        BerzierValutor valutor=new BerzierValutor(point1,point2);
        ValueAnimator valueAnimator=ValueAnimator.ofObject(valutor,point0,point3);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF point= (PointF) animation.getAnimatedValue();
                imageview.setX(point.x);
                imageview.setY(point.y);
                imageview.setAlpha(1-animation.getAnimatedFraction());
            }
        });
        return valueAnimator;
    }

    private PointF getTooglePoint(int i,PointF point) {
        PointF point1=new PointF();
        point1.x=mRandom.nextInt(mWidth);
        int reactHeight=(int)(point.y/2);

        if(i==1){
            //第一个中间点，一半以下的随机位置
            point1.y=reactHeight+mRandom.nextInt(reactHeight);
        }else {
            //第二个中间点，一半以上的随机位置
            point1.y=mRandom.nextInt(reactHeight);
        }
        return point1;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth=getMeasuredWidth();
        mHeight=getMeasuredHeight();
    }
    class  BerzierValutor implements TypeEvaluator<PointF> {
        private PointF mPoint1;
        private PointF mpoint2;

        public BerzierValutor(PointF mPoint1, PointF mpoint2) {
            this.mPoint1 = mPoint1;
            this.mpoint2 = mpoint2;
        }

        @Override
        public PointF evaluate(float t, PointF startValue, PointF endValue) {
            PointF pointf=new PointF();
            //根据贝塞尔公式设置三次方贝塞尔曲线
            pointf.x=startValue.x*(1-t)*(1-t)*(1-t)+3*mPoint1.x*t*(1-t)*(1-t)+3*mpoint2.x*t*t*(1-t)+endValue.x*t*t*t;
            pointf.y=startValue.y*(1-t)*(1-t)*(1-t)+3*mPoint1.y*t*(1-t)*(1-t)+3*mpoint2.y*t*t*(1-t)+endValue.y*t*t*t;
            return pointf;
        }
    }
}
