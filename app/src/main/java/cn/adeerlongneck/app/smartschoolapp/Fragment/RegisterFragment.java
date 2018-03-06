package cn.adeerlongneck.app.smartschoolapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import cn.adeerlongneck.app.smartschoolapp.Activity.RegisterActivity;
import cn.adeerlongneck.app.smartschoolapp.R;

/**
 * Created by 长脖鹿 on 2017/11/21.
 */

public class RegisterFragment extends Fragment {
    Button bt_register;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.registerviewpager, container, false);

        bt_register=(Button)view.findViewById(R.id.bt_register);
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
