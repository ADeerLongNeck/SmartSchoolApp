package cn.adeerlongneck.app.smartschoolapp.Presenster;

import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.adeerlongneck.app.smartschoolapp.Activity.RegisterActivity;
import cn.adeerlongneck.app.smartschoolapp.Model.userModel;
import cn.adeerlongneck.app.smartschoolapp.Utility.HttpUtil;
import cn.adeerlongneck.app.smartschoolapp.View.RegisterActivityView;
import cn.bmob.newsmssdk.BmobSMS;
import cn.bmob.newsmssdk.exception.BmobException;
import cn.bmob.newsmssdk.listener.RequestSMSCodeListener;

/**
 * Created by 长脖鹿 on 2017/12/16.
 */

public class RegisterPresenter {
    Map<String,String> prama;
    int ERRORCODE=4;
RegisterActivityView registerActivityView;
 public RegisterPresenter(RegisterActivityView registerActivityView){
this.registerActivityView=registerActivityView;

 }

    public int isRegister(String stuid, final String phonenumber){
        prama = new HashMap<String, String>();
        prama.put("stuid",stuid);
        prama.put("phonenumber",phonenumber);

        HttpUtil httpUtil =new HttpUtil(new HttpUtil.HttpResponse() {
            @Override
            public void onSuccess(Object object) {
                //取得服务器返回的数据
                String respone=object.toString();
                if(respone.equals("1")){
                    //学号已经被注册了

                    Log.d("2222222222222","1111111111");

                    ERRORCODE=1;
                }
                if(respone.equals("2")){
                    //手机号号已经被注册了

                    Log.d("2222222222222","2222222222222");
                    ERRORCODE=2;
                }
                if(respone.equals("3")){
                    //都被注册了

                    ERRORCODE=3;
                    Log.d("2222222222222","33333333333333");
                }
                if(respone.equals("0")){
                    registerActivityView.sendYZM();
                    //没有注册，可以进行下一步
                    ERRORCODE=0;
                    Log.d("2222222222222","000000000000000000000");
                }

            }
            @Override
            public void onFail(String error) {
                //请求失败
                Log.d("44444444444444444444",error);
            }
        });
        httpUtil.sendPostHttp("http://172.16.219.102/isRegister.php",prama);
        return ERRORCODE;
    }

    public void getYZM(String stuid,String phonenumber){
        isRegister(stuid, phonenumber);

    }





}
