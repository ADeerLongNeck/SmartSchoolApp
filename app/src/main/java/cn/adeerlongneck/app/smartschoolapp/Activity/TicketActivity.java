package cn.adeerlongneck.app.smartschoolapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import cn.adeerlongneck.app.smartschoolapp.R;

public class TicketActivity extends AppCompatActivity {
    WebView wb_ticket;
    ImageView fanhiu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        ini();
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
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
                finish();break;


        }
        return super.onOptionsItemSelected(item);
    }

    public void ini(){
        fanhiu=(ImageView)findViewById(R.id.fanhui);
        wb_ticket=(WebView)findViewById(R.id.wb_ticket);
        wb_ticket.setWebViewClient(new WebViewClient());
        wb_ticket.getSettings().setJavaScriptEnabled(true);
        wb_ticket.loadUrl("https://www.baidu.com");
    }

}
