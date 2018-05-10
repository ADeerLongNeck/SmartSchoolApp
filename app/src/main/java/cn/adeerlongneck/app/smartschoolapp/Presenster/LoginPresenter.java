package cn.adeerlongneck.app.smartschoolapp.Presenster;

import android.util.Log;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import cn.adeerlongneck.app.smartschoolapp.Model.userModel;
import cn.adeerlongneck.app.smartschoolapp.Utility.HttpUtil;
import cn.adeerlongneck.app.smartschoolapp.View.LoginView;

/**
 * Created by 长脖鹿 on 2017/11/16.
 * FindUser--查询服务器用户是否注册
 * stuInfo--用户数据
 */

public class LoginPresenter extends BasePresenter{
    LoginView loginView;
    Map<String,String>prama1;
    Map<String,String>prama2;
    public LoginPresenter(LoginView view){
        loginView=view;
    }
    public void login(final String account, String password){
        prama2= new HashMap<String,String>();
        prama2.put("stuid",account);
        prama2.put("password",password);
        HttpUtil httpUtil=new HttpUtil(new HttpUtil.HttpResponse() {
            @Override
            public void onSuccess(Object object) {
                String res = object.toString();
                if (res.equals("1")) {
                    //TODO 判断是否为教师，

                   getUserInfo(account);


                }
                if (res.equals("0")) {
                    loginView.hideDialog();
                    loginView.loginFail("请核对账号密码");
                }
                else
                {
                    loginView.hideDialog();

                }
            }
            @Override
            public void onFail(String error) {
                loginView.loginFail("登陆失败，"+"错误码:"+error);
                loginView.hideDialog();
            }
        });
        httpUtil.sendPostHttp("http://www.adeerlongneck.cn/smartschool/login.php",prama2);
    }

    public void getUserInfo(final String stuid) {
        Map<String,String> map =new HashMap<>();
        map.put("stuid",stuid);
        HttpUtil httpUtil =new HttpUtil(new HttpUtil.HttpResponse() {
            @Override
            public void onSuccess(Object object) {
                Log.d("11","111111111111111---------------------------------------------"+object.toString());
                Log.d("111","111111111111111-------");
               getCodeFromJson(object.toString());

            }

            @Override
            public void onFail(String error) {
                Log.d("22", "22222222222----------------------------------------------------------------" + stuid + error);
            }
        });
        httpUtil.sendPostHttp("http://www.adeerlongneck.cn:8000/Getuserinfo", map);
}

public String getCodeFromJson(String string){
    String res=null;

    Gson gson=new Gson();
    userModel userinfo=gson.fromJson(string, userModel.class);
    String t=userinfo.getIs_teacher();
    Log.d("00000000000000",userinfo.getIs_teacher());

if(t.equals("1")){

    loginView.hideDialog();
    loginView.loginSuccess(1,userinfo.getRealname(),userinfo.getStuid());

}if(t.equals("0")){
        loginView.hideDialog();
        loginView.loginSuccess(0,userinfo.getRealname(), userinfo.getStuid());

    }
else{
    loginView.hideDialog();
}

    return  res;
}

}
