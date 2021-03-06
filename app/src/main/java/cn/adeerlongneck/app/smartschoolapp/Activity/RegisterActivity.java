package cn.adeerlongneck.app.smartschoolapp.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    ImageView img;
    private Uri imageUri;
    public static final int TAKE_PHOTO=1;
    ProgressDialog progressDialog;
    RegisterPresenter registerPresenter;
    int ERRORCODE=0;
    String YZM;
    userModel user1;

    private void verYZM(String phonenumber,String sms){
        BmobSMS.verifySmsCode(this,phonenumber, sms, new VerifySMSCodeListener() {
            @Override
            public void done(BmobException ex) {
                // TODO Auto-generated method stub
                if(ex==null){//短信验证码已验证成功
                    Log.i("bmob", "验证通过");

                    sendPhoto();

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
        registerPresenter =new RegisterPresenter(this,this);
        ini();
setToolBar();
    }
    public void setToolBar(){
        ImageView im = (ImageView)findViewById(R.id.fanhui);
        TextView tx_title=(TextView)findViewById(R.id.toolbar_title);
        tx_title.setText("注册");
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void ini(){
        BmobSMS.initialize(this,"7326a6be68c5fe21196c69385f87d65b");//应用id
        img=(ImageView)findViewById(R.id.img);

        ed_stuid=(EditText)findViewById(R.id.ed_stuid);
        ed_password=(EditText)findViewById(R.id.ed_password);
        ed_realname=(EditText)findViewById(R.id.ed_realname);
        ed_phonenumber=(EditText)findViewById(R.id.ed_phonenumber);
        bt_yanzhengma=(Button)findViewById(R.id.bt_yanzhengma);
        bt_startface=(Button)findViewById(R.id.bt_startface);
        bt_startface.setEnabled(false);
        bt_startface.setBackgroundResource(R.drawable.shapeclick);
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
//        userModel muser = null;
//        muser.setStuid(ed_stuid.getText().toString());
//        muser.setPassword(ed_password.getText().toString());
//        muser.setRealname(ed_realname.getText().toString());
//        muser.setPhonenumber(ed_phonenumber.getText().toString());
        Map<String,String> map=new HashMap<>();
        map.put("stuid",ed_stuid.getText().toString());
        map.put("realname",ed_realname.getText().toString());
        map.put("password",ed_password.getText().toString());
        map.put("phone",ed_phonenumber.getText().toString());

        registerPresenter.register(map);

    }
    public void getYzMa(String phonenumber){

        //获取验证码


    }
    /**
    * 获取人脸图片
     *
    * */
    public void sendPhoto(){




        File outputImage=new File(this.getExternalCacheDir(),ed_stuid.getText().toString()+"-image.jpg");
        try{
            if (outputImage.exists()){
                outputImage.delete();
            }
            outputImage.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT>=24){
            imageUri= FileProvider.getUriForFile(this,"cn.adeerlongneck.app.smartschoolapp.fileprovider",outputImage);

        }else {
            imageUri = Uri.fromFile(outputImage);
        }
        Intent intent =new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);

        startActivityForResult(intent,TAKE_PHOTO);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case TAKE_PHOTO:
                if(requestCode==1){
                    try{

                        Bitmap bitmap = BitmapFactory.decodeStream(this.getContentResolver().openInputStream(imageUri));
                        img.setImageBitmap(bitmap);
                       registerPresenter.upImage(bitmap);
                        getData();

                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }



    //////////////////

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
        Log.i("bmob", "222222222222222222");//用于查询本次短信发送详情
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

                    bt_startface.setEnabled(true);
                    bt_startface.setBackgroundResource(R.drawable.bt_style_fang);

                    Log.i("bmob", "短信id："+smsId);//用于查询本次短信发送详情
                }
            }
        });

    }

    @Override
    public void showTx(String string) {
        Toast.makeText(RegisterActivity.this, string,Toast.LENGTH_SHORT).show();
    }
}
