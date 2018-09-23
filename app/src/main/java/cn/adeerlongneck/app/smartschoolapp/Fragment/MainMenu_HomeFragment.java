package cn.adeerlongneck.app.smartschoolapp.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import cn.adeerlongneck.app.smartschoolapp.R;

/**
 * Created by 长脖鹿 on 2017/12/9.
 */

public class MainMenu_HomeFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate( R.layout.mainmenu_fragment_home, container, false);
        Toast.makeText(getActivity(),"2",Toast.LENGTH_SHORT).show();
        init(view);
        return view;
    }


    private void init(View mview) {
    WebView webView=(WebView)mview.findViewById(R.id.webView1);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://47.95.254.7:8887/index");

   }

}
