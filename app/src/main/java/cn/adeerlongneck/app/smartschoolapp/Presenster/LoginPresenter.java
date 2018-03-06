package cn.adeerlongneck.app.smartschoolapp.Presenster;

import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

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

    public void isRegister(String account, final int i){
        prama1= new HashMap<String,String>();
        prama1.put("stuid",account);

        HttpUtil httpUtil=new HttpUtil(new HttpUtil.HttpResponse() {
            @Override
            public void onSuccess(Object object) {
                String res=object.toString();

                if(res.equals("1")){
                    Log.d("1111111111",res);
                    loginView.hideDialog();
                    loginView.isRegister(i);
                }
                if(res.equals("0")){
                    loginView.hideDialog();
                    loginView.notRegister();
                }

            }

            @Override
            public void onFail(String error) {
                loginView.hideDialog();
                loginView.loginFail("请检查网络连接");
            }
        });
    httpUtil.sendPostHttp("http://192.168.6.46/isRegister2.php",prama1);



    }
    public void Login(String account,String password){

        prama2= new HashMap<String,String>();
        prama2.put("stuid",account);
        prama2.put("password",password);

        HttpUtil httpUtil=new HttpUtil(new HttpUtil.HttpResponse() {
            @Override
            public void onSuccess(Object object) {
                String res = object.toString();

                if (res.equals("1")) {
                    loginView.hideDialog();
                    loginView.loginSuccess();

                }
                if (res.equals("0")) {
                    loginView.hideDialog();
                    loginView.loginFail("请核对账号密码");
                }
                else
                {
                    loginView.hideDialog();
                    loginView.loginFail("登陆失败，请检查账号密码");
                }


            }

            @Override
            public void onFail(String error) {

            }
        });
        httpUtil.sendPostHttp("http://192.168.6.46/login.php",prama2);

    }







}
