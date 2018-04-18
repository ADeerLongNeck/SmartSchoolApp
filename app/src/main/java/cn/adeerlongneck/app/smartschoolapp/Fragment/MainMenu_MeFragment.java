package cn.adeerlongneck.app.smartschoolapp.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import cn.adeerlongneck.app.smartschoolapp.MyApplication;
import cn.adeerlongneck.app.smartschoolapp.R;

/**
 * Created by 长脖鹿 on 2017/12/9.
 */

public class MainMenu_MeFragment extends Fragment {
    LinearLayout linearLayout_kaquan;
    TextView text_me;
String name;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mainmenu_fragment_me, container, false);

        linearLayout_kaquan=(LinearLayout)view.findViewById(R.id.me_kaquan);
        linearLayout_kaquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"别点我，滚~",Toast.LENGTH_SHORT).show();
            }
        });

    ini(view);
        return view;
    }

    public void ini(View view){
        MyApplication application = (MyApplication)getActivity().getApplicationContext();
        name= application.getName();
        text_me=(TextView)view.findViewById(R.id.me_tx_name);
        text_me.setText(name);

    }

}
