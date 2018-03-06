package cn.adeerlongneck.app.smartschoolapp.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.isnc.facesdk.SuperID;
import com.isnc.facesdk.common.Cache;
import com.isnc.facesdk.common.SDKConfig;

import cn.adeerlongneck.app.smartschoolapp.Activity.MainActivity;
import cn.adeerlongneck.app.smartschoolapp.Activity.MainMenuActivity;
import cn.adeerlongneck.app.smartschoolapp.Presenster.LoginPresenter;
import cn.adeerlongneck.app.smartschoolapp.R;
import cn.adeerlongneck.app.smartschoolapp.Utility.ClearEditText;
import cn.adeerlongneck.app.smartschoolapp.View.LoginView;

/**
 * Created by 长脖鹿 on 2017/11/21.
 */

public class LoginFragment extends Fragment implements LoginView {
    Button bt_login_face;
    Button bt_login_id;
    EditText ed_account;
    EditText ed_password;
    LoginPresenter loginPresenter;
    int IdLoginINDEX=1;
    ProgressDialog progressDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.loginviewpager, container, false);

        ini(view);

        return view;
    }
    public void ini(View view){
        loginPresenter=new LoginPresenter(this);
        ed_account=(EditText) view.findViewById(R.id.ed_account);
        ed_password=(EditText) view.findViewById(R.id.ed_password);
        bt_login_id=(Button) view.findViewById(R.id.login_id);
        bt_login_face=(Button)view.findViewById(R.id.login_face);
        bt_login_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {idLogin(IdLoginINDEX);}});
        bt_login_face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              //  faceLogin();
               loginSuccess();
            }});
        hideText();

    }
    private void idLogin(int i){
        if (i==1){

            if (ed_account.getText().toString().isEmpty()){

                Toast.makeText(getActivity(),"请输入学号后继续",Toast.LENGTH_SHORT).show();
                //学号为空

            }else {
                // p

                loginPresenter.isRegister(ed_account.getText().toString(),1);
                showDialog();
            }
        }
        if(i==2){
            if (ed_account.getText().toString().isEmpty()){
                Toast.makeText(getActivity(),"请输入学号后继续",Toast.LENGTH_SHORT).show();
                //学号为空
            }
            if (ed_password.getText().toString().isEmpty())
            {
                Toast.makeText(getActivity(),"请输入密码后继续",Toast.LENGTH_SHORT).show();
                //密码为空
            }
            else {

            loginPresenter.Login(ed_account.getText().toString(),ed_password.getText().toString());
                showDialog();
                //p
            }
        }
    }
    private void faceLogin(){
        if (ed_account.getText().toString().isEmpty()){
            Toast.makeText(getActivity(),"请输入学号后继续",Toast.LENGTH_SHORT).show();
            //学号为空
        }else {
            // p
            loginPresenter.isRegister(ed_account.getText().toString(),2);
            showDialog();
        }
    }




    @Override
    public void hideText() {
        ed_password.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showText() {
        ed_password.setVisibility(View.VISIBLE);
    }

    @Override
    public void loginFail(String error) {
        Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(getActivity(),"登陆成功",Toast.LENGTH_SHORT).show();
        Intent intent =new Intent(getActivity(),MainMenuActivity.class);
        startActivity(intent);
    }

    @Override
    public void showDialog() {
if (progressDialog==null){
    progressDialog=ProgressDialog.show(getActivity(),"","正在加载",true,false);

}else if(progressDialog.isShowing())
{
    progressDialog.setTitle("");
    progressDialog.setMessage("正在加载");
}
progressDialog.show();
    }

    @Override
    public void hideDialog() {
        if (progressDialog!=null&&progressDialog.isShowing()){
            progressDialog.dismiss();
        }

    }

    @Override
    public void notRegister() {
        //未注册
        Toast.makeText(getActivity(),"该学号未注册",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startFaceLogin() {
        Toast.makeText(getActivity(),"开始刷脸",Toast.LENGTH_SHORT).show();
        SuperID.faceLogin(getActivity());
    }

    @Override
    public void isRegister(int i) {
  switch (i){
    case 1:
        showText();
        IdLoginINDEX=2;break;
    case 2:startFaceLogin();break;
   }

    }
}
