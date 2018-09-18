package cn.adeerlongneck.app.smartschoolapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;

import java.sql.Time;

import cn.adeerlongneck.app.smartschoolapp.Presenster.CreateSignPresenter;
import cn.adeerlongneck.app.smartschoolapp.R;
import cn.adeerlongneck.app.smartschoolapp.Utility.LocationUtil;
import cn.adeerlongneck.app.smartschoolapp.View.CreateSignView;

public class CreateSignActivity extends AppCompatActivity implements CreateSignView{
NumberPicker numberPicker;
CreateSignPresenter createSignPresenter;
Button bt_post;
String git;
    String courseid;
    String time="300";
    String jing;
    String wei;
    MapView mMapView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_sign);
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String []{android.Manifest.permission.ACCESS_COARSE_LOCATION},1);
        }

        LocationUtil();

    ini();
        mMapView.onCreate(savedInstanceState);
map();



    }
    public void ini(){
        Intent intent = getIntent();
        //从Intent当中根据key取得value
        if (intent != null) {
            courseid = intent.getStringExtra("courseid");
        }
        createSignPresenter =new CreateSignPresenter(this);
        mMapView = (MapView) findViewById(R.id.map);
        numberPicker=(NumberPicker)findViewById(R.id.numberPicker);
        bt_post=(Button)findViewById(R.id.post);
        bt_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            createSignPresenter.postdata(courseid,time,jing,wei);
                Log.d("ddd","-----------"+jing+"----"+wei+"----"+time+"----"+courseid);


            }
        });
        numberPirckerOpinion();

    }
    public void map(){
        AMap aMap = null;
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
//aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.moveCamera(CameraUpdateFactory.zoomTo(17));


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    public void numberPirckerOpinion(){
         final String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10","20","30"};
        //设置需要显示的内容数组
        numberPicker.setDisplayedValues(numbers);
        //设置最大最小值
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(numbers.length);
        //设置默认的位置
        numberPicker.setValue(5);
        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //得到选择结果
                Log.d("2",numbers[picker.getValue()-1] );
             //   time=numbers[picker.getValue()-1];

            }
        });
    }

    @Override
    public void bind(String jing, String wei) {
        this.jing=jing;
        this.wei=wei;
    }

    @Override
    public void success(String random) {
        Intent intent=new Intent(this,HistoryInfoActivity.class);
        intent.putExtra("random",random);
        startActivity(intent);
    }

    @Override
    public void fail() {

    }


    public AMapLocationClient mLocationClient = null;
    public AMapLocationClientOption mLocationOption = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
//可在其中解析amapLocation获取相应内容。
                    Log.d("ddd",amapLocation.getAddress()+"纬度"+amapLocation.getLatitude()+"经度"+amapLocation.getLongitude());
                    jing= String.valueOf(amapLocation.getLongitude());
                    wei=String.valueOf(amapLocation.getLatitude());
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
            }
        }
    };
    public void LocationUtil() {


        mLocationClient = new AMapLocationClient(this);
//设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        mLocationOption = new AMapLocationClientOption();
        option();

        mLocationClient.setLocationOption(mLocationOption);
//启动定位
        location();
    }

    public void location(){
        mLocationClient.startLocation();
    }
    public void option() {
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setOnceLocation(true);
//获取最近3s内精度最高的一次定位结果：
//设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
    }

}
