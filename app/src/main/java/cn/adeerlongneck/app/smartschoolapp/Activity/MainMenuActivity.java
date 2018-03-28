package cn.adeerlongneck.app.smartschoolapp.Activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import cn.adeerlongneck.app.smartschoolapp.Fragment.MainMenu_FunctionFragment;
import cn.adeerlongneck.app.smartschoolapp.Fragment.MainMenu_HomeFragment;
import cn.adeerlongneck.app.smartschoolapp.Fragment.MainMenu_MeFragment;
import cn.adeerlongneck.app.smartschoolapp.R;

public class MainMenuActivity extends AppCompatActivity {
    MainMenu_HomeFragment fragment_home;
    MainMenu_FunctionFragment fragment_function;
    MainMenu_MeFragment fragment_me;
    private Fragment[] fragments;
    private int FRAGMENT_NOW=1;
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    replaceFragment(fragment_home);
                    return true;
                case R.id.navigation_dashboard:
                        replaceFragment(fragment_function);
                    return true;
                case R.id.navigation_notifications:
              replaceFragment(fragment_me);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        ini();
        replaceFragment(fragment_home);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }
    private void ini(){
        fragment_home=new MainMenu_HomeFragment();
        fragment_function=new MainMenu_FunctionFragment();
        fragment_me=new MainMenu_MeFragment();
    }
    private void replaceFragment(android.support.v4.app.Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transcation =fragmentManager.beginTransaction();
        transcation.replace(R.id.content,fragment);
        transcation.commit();


    }


}
