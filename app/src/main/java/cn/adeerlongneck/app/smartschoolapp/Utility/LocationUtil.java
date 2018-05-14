package cn.adeerlongneck.app.smartschoolapp.Utility;

import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import cn.adeerlongneck.app.smartschoolapp.Activity.CreateSignActivity;

/**
 * Created by 长脖鹿 on 2018/5/14.
 */

public class LocationUtil {
    Context context;

    public String getJingdu() {
        return jingdu;
    }

    public void setJingdu(String jingdu) {
        this.jingdu = jingdu;
    }

    public String getWeidu() {
        return weidu;
    }

    public void setWeidu(String weidu) {
        this.weidu = weidu;
    }

    String jingdu;
    String weidu;
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
                    jingdu= String.valueOf(amapLocation.getLongitude());
                    weidu=String.valueOf(amapLocation.getLatitude());
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
            }
        }
    };
        public LocationUtil(Context c) {
            this.context = c;

            mLocationClient = new AMapLocationClient(context);
//设置定位回调监听
            mLocationClient.setLocationListener(mLocationListener);
            mLocationOption = new AMapLocationClientOption();
            option();
            mLocationClient.setLocationOption(mLocationOption);
//启动定位

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





