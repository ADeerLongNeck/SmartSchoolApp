package cn.adeerlongneck.app.smartschoolapp.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



import cn.adeerlongneck.app.smartschoolapp.Model.userModel;
import cn.adeerlongneck.app.smartschoolapp.Presenster.RegisterPresenter;
import cn.adeerlongneck.app.smartschoolapp.R;
import cn.adeerlongneck.app.smartschoolapp.Utility.HttpUtil;
import cn.adeerlongneck.app.smartschoolapp.Utility.MyCountDownTimer;
import cn.adeerlongneck.app.smartschoolapp.View.RegisterActivityView;
import cn.bmob.newsmssdk.BmobSMS;
import cn.bmob.newsmssdk.exception.BmobException;
import cn.bmob.newsmssdk.listener.RequestSMSCodeListener;
import cn.bmob.newsmssdk.listener.VerifySMSCodeListener;

public class RegisterActivity extends AppCompatActivity implements RegisterActivityView {
    EditText ed_stuid;
    EditText ed_password;
    EditText ed_realname;
    EditText ed_phonenumber;
    Button bt_startface;
    Button bt_yanzhengma;
    EditText ed_yzm;
    Context context;
    ProgressDialog progressDialog;
    RegisterPresenter registerPresenter;
    int ERRORCODE=0;
    String YZM;

    private void verYZM(String phonenumber,String sms){
        BmobSMS.verifySmsCode(this,phonenumber, sms, new VerifySMSCodeListener() {
            @Override
            public void done(BmobException ex) {
                // TODO Auto-generated method stub
                if(ex==null){//短信验证码已验证成功
                    Log.i("bmob", "验证通过");
                    Toast.makeText(RegisterActivity.this,"验证成功",Toast.LENGTH_SHORT).show();
                }else{
                    Log.i("bmob", "验证失败：code ="+ex.getErrorCode()+",msg = "+ex.getLocalizedMessage());
                    Toast.makeText(RegisterActivity.this,"验证失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerPresenter =new RegisterPresenter(this);
        ini();

    }
    public void ini(){
        BmobSMS.initialize(this,"7326a6be68c5fe21196c69385f87d65b");//应用id

        ed_stuid=(EditText)findViewById(R.id.ed_stuid);
        ed_password=(EditText)findViewById(R.id.ed_password);
        ed_realname=(EditText)findViewById(R.id.ed_realname);
        ed_phonenumber=(EditText)findViewById(R.id.ed_phonenumber);
        bt_yanzhengma=(Button)findViewById(R.id.bt_yanzhengma);
        bt_startface=(Button)findViewById(R.id.bt_startface);
        ed_yzm=(EditText)findViewById(R.id.ed_yzm);
        bt_startface.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verYZM(ed_phonenumber.getText().toString(),ed_yzm.getText().toString());
            }
        });
        bt_yanzhengma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registerPresenter.getYZM(ed_stuid.getText().toString(),ed_phonenumber.getText().toString());


            }  });

    }



    public void getData(){
        userModel muser = null;
        muser.setStuid(ed_stuid.getText().toString());
        muser.setPassword(ed_password.getText().toString());
        muser.setRealname(ed_realname.getText().toString());
        muser.setPhonenumber(ed_phonenumber.getText().toString());
    }
    public void getYzMa(String phonenumber){

        //获取验证码


    }

    @Override
    public void showDialog() {
        if (progressDialog==null){
            progressDialog= ProgressDialog.show(this,"","正在加载",true,false);

        }else if(progressDialog.isShowing())
        {
            progressDialog.setTitle("");
            progressDialog.setMessage("正在加载");
        }
        progressDialog.show();
    }

    @Override
    public void hideDialog() {
        if (progressDialog!=null&&progressDialog.isShowing()){
            progressDialog.dismiss();
        }

    }

    @Override
    public void sendYZM() {
        final MyCountDownTimer   myCountDownTimer = new MyCountDownTimer(60000,1000,bt_yanzhengma);
        SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sendTime = format.format(new Date());
        BmobSMS.requestSMSCode(this,ed_phonenumber.getText().toString(), "sms1",new RequestSMSCodeListener() {

            @Override
            public void done(Integer smsId,BmobException ex) {
                // TODO Auto-generated method stub
                if(ex==null){//验证码发送成功
                    Toast.makeText(RegisterActivity.this,"验证码发送成功！",Toast.LENGTH_SHORT).show();

                    myCountDownTimer.start();
                    Log.i("bmob", "短信id："+smsId);//用于查询本次短信发送详情
                }
            }
        });

    }
}
