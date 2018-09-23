package cn.adeerlongneck.app.smartschoolapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import cn.adeerlongneck.app.smartschoolapp.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ImageView im = (ImageView)findViewById(R.id.fanhui);
        TextView tx_title=(TextView)findViewById(R.id.toolbar_title);
        tx_title.setText("关于团队");
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        WebView webView=(WebView)findViewById(R.id.wb_about);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://47.95.254.7/ssapp/about/");
    }
}
