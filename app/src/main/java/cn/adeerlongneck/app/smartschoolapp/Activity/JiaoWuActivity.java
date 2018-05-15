package cn.adeerlongneck.app.smartschoolapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cn.adeerlongneck.app.smartschoolapp.R;

public class JiaoWuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiao_wu);
        WebView webView=(WebView)findViewById(R.id.webView1);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http:/jwgl.ujn.edu.cn");
    }
}
