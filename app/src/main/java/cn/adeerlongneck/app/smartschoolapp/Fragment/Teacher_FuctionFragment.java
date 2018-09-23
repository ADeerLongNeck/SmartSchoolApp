package cn.adeerlongneck.app.smartschoolapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.adeerlongneck.app.smartschoolapp.Activity.CreatCourseActivity;
import cn.adeerlongneck.app.smartschoolapp.Activity.FixDataActivity;
import cn.adeerlongneck.app.smartschoolapp.Activity.JiaoWuActivity;
import cn.adeerlongneck.app.smartschoolapp.Activity.TeacherSignActivity;
import cn.adeerlongneck.app.smartschoolapp.Activity.TicketActivity;
import cn.adeerlongneck.app.smartschoolapp.R;

/**
 * Created by chao on 2018/3/27.
 */

public class Teacher_FuctionFragment extends Fragment {
    CardView creatcourse;
    CardView sign;
    CardView cardView_jiaowu;
    CardView cardView_ticket;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.teacher_fragment_function, container, false);
        iniViews(view);

        return view;
    }

    private void iniViews(View view) {
        creatcourse = (view).findViewById(R.id.fun_card_creat);
        cardView_jiaowu=(CardView)view.findViewById(R.id.jiaowu) ;
        cardView_ticket=(CardView)view.findViewById(R.id.ticket) ;
        sign = (view).findViewById(R.id.sign);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TeacherSignActivity.class);
                startActivity(intent);
            }
        });


        creatcourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreatCourseActivity.class);
                startActivity(intent);
            }
        });

        cardView_jiaowu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),FixDataActivity.class);
                startActivity(intent);
            }
        });
        cardView_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), TicketActivity.class);
                startActivity(intent);
            }
        });
    }

}
