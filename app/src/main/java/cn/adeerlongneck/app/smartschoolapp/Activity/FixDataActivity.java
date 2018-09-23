package cn.adeerlongneck.app.smartschoolapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cn.adeerlongneck.app.smartschoolapp.R;

public class FixDataActivity extends AppCompatActivity {
WebView wb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_data);
        wb=(WebView)findViewById(R.id.wb_fix);
        wb.setWebViewClient(new WebViewClient());
        wb.getSettings().setJavaScriptEnabled(true);
        wb.loadUrl("http://47.95.254.7:8887/xadmin");
    }
}
