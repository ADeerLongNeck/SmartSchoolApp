package cn.adeerlongneck.app.smartschoolapp.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.isnc.facesdk.SuperID;
import com.isnc.facesdk.common.Cache;
import com.isnc.facesdk.common.SDKConfig;

import java.lang.reflect.Field;
import java.util.ArrayList;

import cn.adeerlongneck.app.smartschoolapp.Adapter.LoginFragmentAdapter;
import cn.adeerlongneck.app.smartschoolapp.R;
import cn.adeerlongneck.app.smartschoolapp.Utility.ShowToast;
import cn.adeerlongneck.app.smartschoolapp.View.LoginView;

public class MainActivity extends AppCompatActivity implements  ViewPager.OnPageChangeListener{
    private static final String TAG = "there";
    private LoginFragmentAdapter mAdapter;
    PagerTitleStrip mPagerTitleStrip;
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    ViewPager vpager;
    ArrayList<String> titlelists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SuperID.initFaceSDK(this);

        ini();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("11111111111","1111111111111");

    }


    private void ini(){
        titlelists =new ArrayList<String>();
        titlelists.add("登 陆");
        titlelists.add("注 册");
        mAdapter = new LoginFragmentAdapter(getSupportFragmentManager(),titlelists);
        mPagerTitleStrip = (PagerTitleStrip) findViewById(R.id.pagertitle);
        vpager = (ViewPager) findViewById(R.id.viewpager_login);
        vpager.setAdapter(mAdapter);
        vpager.setCurrentItem(0);
        setTitleStyle(1);



            }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position==1){
            //页面选择
            //TODO
            //这里是测试Toast工具类
        }if(position==0){


        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
            if (state == 2) {
                switch (vpager.getCurrentItem()) {
                    case PAGE_ONE:
                        vpager.setCurrentItem(PAGE_ONE);
                        setTitleStyle(2);
                        break;
                    case PAGE_TWO:
                        setTitleStyle(3);
                        vpager.setCurrentItem(PAGE_TWO);
                        break;
                }
        }
    }
    public void setTitleStyle(int position){
        try {
            //这里重写 viewpager里面的巴拉巴拉
            Field field2=mPagerTitleStrip.getClass().getDeclaredField("mNextText");
            Field field1=mPagerTitleStrip.getClass().getDeclaredField("mCurrText");
            Field field3=mPagerTitleStrip.getClass().getDeclaredField("mPrevText");
            field3.setAccessible(true);
            field1.setAccessible(true);
            field2.setAccessible(true);
            TextView viewnext=(TextView) field2.get(mPagerTitleStrip);
            TextView viewcurr=(TextView) field1.get(mPagerTitleStrip);
            TextView viewpre=(TextView) field3.get(mPagerTitleStrip);
            switch(position){
                case 1:
                      viewcurr.setTextSize(36);
                      viewnext.setTextSize(24);
                      viewnext.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View view) {
                              vpager.setCurrentItem(PAGE_TWO);
                          }
                      });
                      break;
                case 2:
                    viewcurr.setTextSize(36);
                    viewnext.setTextSize(24);
                    viewnext.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            vpager.setCurrentItem(PAGE_TWO);
                        }
                    });
                    break;
                case 3:
                    viewcurr.setTextSize(36);
                    viewpre.setTextSize(24);
                    viewpre.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            vpager.setCurrentItem(PAGE_ONE);
                        }
                    });
                    break;
            }
        } catch (IllegalArgumentException e) {

            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


    }





}
