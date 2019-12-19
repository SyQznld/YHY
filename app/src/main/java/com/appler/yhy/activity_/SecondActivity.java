package com.appler.yhy.activity_;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.appler.yhy.MainActivity;
import com.appler.yhy.R;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.ldoblem.giftcardlib.GiftCardView;

import de.hdodenhof.circleimageview.CircleImageView;

public class SecondActivity extends AppCompatActivity {

    private CircleImageView circleImageView;
    private ImageButton ibTitleQuestion;
    private ImageButton ibMainQuestion;
    private ImageButton ibLocation;
    private TextView tv_address;

    private MapView mapView;
    private BaiduMap baiduMap;
    private LocationClient locationClient;
    private BDLocationListener bdLocationListener = new MyLocationListener();
    private LatLng latLng;
    private boolean isFirstLoc = true;
    private float lastX;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_second);

        initViewAndClick();

        initMapBasic();
        location();

    }

    private void initViewAndClick() {
        mapView = findViewById(R.id.mv_mapview);
        ibLocation = findViewById(R.id.ib_main_location);
        circleImageView = findViewById(R.id.cie_head);
        ibMainQuestion = findViewById(R.id.ib_main_question);
        ibTitleQuestion = findViewById(R.id.ib_title_question);
        tv_address = findViewById(R.id.tv_address);





        ibMainQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ibTitleQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, ManageActivity.class);
                startActivity(intent);
            }
        });

        ibLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location();
            }
        });
    }


    private void location() {


        // 设置自定义图标  定位到当前位置
        MyLocationConfiguration.LocationMode mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory.fromResource(R.mipmap.navi_map_gps_locked);
        MyLocationConfiguration config = new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker);
        baiduMap.setMyLocationConfigeration(config);


        locationClient = new LocationClient(getApplicationContext());
        initLocation();
        locationClient.registerLocationListener(bdLocationListener);
        locationClient.start();
        locationClient.requestLocation();

        //把定位点再次显现出来
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(latLng);
        baiduMap.animateMapStatus(mapStatusUpdate);
    }


    private void initMapBasic() {
        baiduMap = mapView.getMap();
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        baiduMap.setMyLocationEnabled(true);

        //隐藏百度地图logo
        View child = mapView.getChildAt(1);
        if (child != null && (child instanceof ImageView || child instanceof ZoomControls)) {
            child.setVisibility(View.INVISIBLE);
        }
        //显示地图上比例尺
        mapView.showScaleControl(true);

        //指南针
        UiSettings uiSettings = baiduMap.getUiSettings();
        uiSettings.setCompassEnabled(false);
        uiSettings.setRotateGesturesEnabled(false);
    }

    //实现BDLocationListener接口,BDLocationListener为结果监听接口，异步获取定位结果
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            latLng = new LatLng(location.getLatitude(), location.getLongitude());
            // 构造定位数据
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(lastX).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            // 设置定位数据
            baiduMap.setMyLocationData(locData);
            // 当不需要定位图层时关闭定位图层
            //mBaiduMap.setMyLocationEnabled(false);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

                if (location.getLocType() == BDLocation.TypeGpsLocation) {
                    // GPS定位结果
                    tv_address.setText(location.getAddrStr());
                    Toast.makeText(SecondActivity.this, location.getAddrStr(), Toast.LENGTH_SHORT).show();
                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
                    // 网络定位结果
                    tv_address.setText(location.getAddrStr());
                    Toast.makeText(SecondActivity.this, location.getAddrStr(), Toast.LENGTH_SHORT).show();
                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {
                    // 离线定位结果
                    tv_address.setText(location.getAddrStr());
                    Toast.makeText(SecondActivity.this, location.getAddrStr(), Toast.LENGTH_SHORT).show();

                } else if (location.getLocType() == BDLocation.TypeServerError) {
                    Toast.makeText(SecondActivity.this, "服务器错误，请检查", Toast.LENGTH_SHORT).show();
                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                    Toast.makeText(SecondActivity.this, "网络错误，请检查", Toast.LENGTH_SHORT).show();
                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                    Toast.makeText(SecondActivity.this, "手机模式错误，请检查是否飞行", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);
        option.setOpenGps(true); // 打开gps


        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        locationClient.setLocOption(option);
    }

}
