package cn.adeerlongneck.app.smartschoolapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import cn.adeerlongneck.app.smartschoolapp.Activity.AboutActivity;
import cn.adeerlongneck.app.smartschoolapp.Activity.MainActivity;
import cn.adeerlongneck.app.smartschoolapp.MyApplication;
import cn.adeerlongneck.app.smartschoolapp.R;

/**
 * Created by 长脖鹿 on 2017/12/9.
 */

public class MainMenu_MeFragment extends Fragment {
    LinearLayout linearLayout_kaquan;
    LinearLayout linearLayout_about;
    TextView text_me;
    Button bt_logout;
String name;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mainmenu_fragment_me, container, false);
        ini(view);
        linearLayout_about=(LinearLayout)view.findViewById(R.id.about);
        linearLayout_kaquan=(LinearLayout)view.findViewById(R.id.me_kaquan);
        linearLayout_kaquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"暂无卡券",Toast.LENGTH_SHORT).show();
            }
        });
        linearLayout_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            Intent intent = new Intent(getActivity(), AboutActivity.class);
            startActivity(intent);
            }
        });


        return view;
    }

    public void ini(View view){
        MyApplication application = (MyApplication)getActivity().getApplicationContext();
        name= application.getName();
        text_me=(TextView)view.findViewById(R.id.me_tx_name);
        bt_logout=(Button)view.findViewById(R.id.logout);
        text_me.setText(name);
        bt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

}
