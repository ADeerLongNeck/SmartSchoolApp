package cn.adeerlongneck.app.smartschoolapp.Activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import java.util.HashMap;
import java.util.Map;

import cn.adeerlongneck.app.smartschoolapp.MyApplication;
import cn.adeerlongneck.app.smartschoolapp.R;
import cn.adeerlongneck.app.smartschoolapp.Utility.HttpUtil;

public class StudentStartSignActivity extends AppCompatActivity {
    String wei = null;
    String jing = null;
    String stuid = null;
    String random = null;
    Button bt_sign;
    EditText ed_random;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_start_sign);
        bt_sign = (Button) findViewById(R.id.bt_sign);
        ed_random = (EditText) findViewById(R.id.ed_random);
        random = ed_random.getText().toString();
        MyApplication application = (MyApplication) this.getApplicationContext();
        stuid = application.getStuid();
        LocationUtil();
        showDialog();
        bt_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_random.getText().toString().isEmpty()) {
                    Toast.makeText(StudentStartSignActivity.this, "请输入签到码", Toast.LENGTH_SHORT).show();
                } else
                    startSign();
            }
        });
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
                    Log.d("ddd", amapLocation.getAddress() + "纬度" + amapLocation.getLatitude() + "经度" + amapLocation.getLongitude());
                    jing = String.valueOf(amapLocation.getLongitude());
                    wei = String.valueOf(amapLocation.getLatitude());
                    hideDialog();
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

    public void location() {
        mLocationClient.startLocation();
    }

    public void option() {
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setOnceLocation(true);
//获取最近3s内精度最高的一次定位结果：
//设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
    }

    public void startSign() {

        random = ed_random.getText().toString();

        Map<String, String> map = new HashMap<>();
        map.put("stuid", stuid);
        map.put("random", random);
        map.put("lo", wei);
        map.put("la", jing);
        HttpUtil httpUtil = new HttpUtil(new HttpUtil.HttpResponse() {
            @Override
            public void onSuccess(Object object) {
                String res = object.toString();
                if (res.equals("1")) {
                    Toast.makeText(StudentStartSignActivity.this, "签到成功~", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(StudentStartSignActivity.this, "签到失败~"+ object.toString(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFail(String error) {
                Log.d("dd", "--------" + error);
                Toast.makeText(StudentStartSignActivity.this, "签到失败,请检查网络"+ stuid+random+jing+wei, Toast.LENGTH_SHORT).show();
            }
        });
        httpUtil.sendPostHttp("http://www.adeerlongneck.cn:8000/Sign", map);
    }


    public void showDialog() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(this, "", "正在定位", true, false);

        } else if (progressDialog.isShowing()) {
            progressDialog.setTitle("");
            progressDialog.setMessage("正在定位");
        }
        progressDialog.show();
    }


    public void hideDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }


    }

}