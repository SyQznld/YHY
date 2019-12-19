package com.appler.yhy.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by appler on 2018/5/7.
 */

public class YHYAllImage implements Parcelable{
    private String describe;
    private int drawableId;

    public YHYAllImage() {
    }

    public YHYAllImage(String describe, int drawableId) {
        this.describe = describe;
        this.drawableId = drawableId;
    }

    protected YHYAllImage(Parcel in) {
        describe = in.readString();
        drawableId = in.readInt();
    }

    public static final Creator<YHYAllImage> CREATOR = new Creator<YHYAllImage>() {
        @Override
        public YHYAllImage createFromParcel(Parcel in) {
            return new YHYAllImage(in);
        }

        @Override
        public YHYAllImage[] newArray(int size) {
            return new YHYAllImage[size];
        }
    };

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    @Override
    public String toString() {
        return "YHYAllImage{" +
                "describe='" + describe + '\'' +
                ", drawableId=" + drawableId +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(describe);
        dest.writeInt(drawableId);
    }
}
