package cn.adeerlongneck.app.smartschoolapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import cn.adeerlongneck.app.smartschoolapp.Activity.JiaoWuActivity;
import cn.adeerlongneck.app.smartschoolapp.Activity.SignActivity;
import cn.adeerlongneck.app.smartschoolapp.R;
import cn.adeerlongneck.app.smartschoolapp.Utility.MyOnTouch;

/**
 * Created by 长脖鹿 on 2017/12/9.
 */

public class MainMenu_FunctionFragment extends Fragment {
        CardView cardView_qiandao;
        CardView cardView_sign;
        CardView cardView_jiaowu;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mainmenu_fragment_function, container, false);
        iniViews(view);

        return view;
    }
    private void iniViews(View view){

        cardView_sign=(CardView)view.findViewById(R.id.sign) ;
        cardView_jiaowu=(CardView)view.findViewById(R.id.jiaowu) ;
        cardView_qiandao=(CardView)view.findViewById(R.id.fun_card_qiandao);

        cardView_qiandao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"不要点我了，~~暂时未开放",Toast.LENGTH_SHORT).show();
            }
        });
        cardView_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), SignActivity.class);
                startActivity(intent);
            }
        });
        cardView_jiaowu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
Intent intent=new Intent(getActivity(), JiaoWuActivity.class);
startActivity(intent);
            }
        });


    }
}
