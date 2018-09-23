package cn.adeerlongneck.app.smartschoolapp.Activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import cn.adeerlongneck.app.smartschoolapp.MyApplication;
import cn.adeerlongneck.app.smartschoolapp.R;
import cn.adeerlongneck.app.smartschoolapp.Utility.HttpUtil;
import cn.google.zxing.activity.CaptureActivity;

import static android.app.Activity.RESULT_OK;

public class TicketActivity extends AppCompatActivity {
    WebView wb_ticket;
    ImageView fanhiu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        ini();
        Toolbar toolbar = (Toolbar)findViewById(R.id.mtoolbar);
        toolbar.setTitle("");
        fanhiu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toobar,menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.backup:
                scan();break;
            case R.id.add:
                add();break;
            case R.id.list:
                list();break;


        }
        return super.onOptionsItemSelected(item);
    }

public void scan(){
    Intent intent = new Intent(TicketActivity.this,CaptureActivity.class);
    startActivityForResult(intent,1);
}


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("=============","==================="+resultCode);
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode ==161) { //RESULT_OK = -1
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("qr_scan_result");
            Log.d("=============","===================1");
            checkIn(scanResult);
           // Toast.makeText(TicketActivity.this, scanResult, Toast.LENGTH_LONG).show();
        }
    }

    public void ini(){
        fanhiu=(ImageView)findViewById(R.id.fanhui);
        wb_ticket=(WebView)findViewById(R.id.wb_ticket);
        wb_ticket.setWebViewClient(new WebViewClient());
        wb_ticket.getSettings().setJavaScriptEnabled(true);
        MyApplication application = (MyApplication) getApplicationContext();
        final String stuid = application.getStuid();
        wb_ticket.loadUrl("http://47.95.254.7:8887/activity_list?user_id="+stuid);
        if (ContextCompat.checkSelfPermission(TicketActivity.this, Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("温馨提示")
                    .setMessage("为了您更好的使用扫描功能，请您赋予相机权限")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.M)
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
                        }
                    })
                    .show();

        } else {

        }
    }


    public void list(){
        MyApplication application = (MyApplication) getApplicationContext();
        final String stuid = application.getStuid();
        wb_ticket.loadUrl("http://47.95.254.7:8887/activity_list?user_id="+stuid);
    }
    public void add(){
        MyApplication application = (MyApplication) getApplicationContext();
        final String stuid = application.getStuid();
        wb_ticket.loadUrl("http://47.95.254.7:8887/activity?user_id="+stuid);
    }
public void checkIn(String code){
    MyApplication application = (MyApplication) getApplicationContext();
    final String stuid = application.getStuid();
    Map<String, String> map = new HashMap<>();
    map.put("ticket_id",code);
    map.put("creator_id",stuid);
    HttpUtil httpUtil =new HttpUtil(new HttpUtil.HttpResponse() {
        @Override
        public void onSuccess(Object object) {
            String res = object.toString();
            if (res.equals("1")) {
                Toast.makeText(TicketActivity.this,"进场成功！",Toast.LENGTH_SHORT).show();
                scan();
            }else Toast.makeText(TicketActivity.this,"进场失败！",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFail(String error) {
            Toast.makeText(TicketActivity.this,"联接错误",Toast.LENGTH_SHORT).show();
            Log.d("1111111111111",error);
        }
    });

    httpUtil.sendPostHttp("http://47.95.254.7:8887/check_ticket/",map);


}



}
