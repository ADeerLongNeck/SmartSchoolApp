package cn.adeerlongneck.app.smartschoolapp.Activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.adeerlongneck.app.smartschoolapp.Model.CourseModel;
import cn.adeerlongneck.app.smartschoolapp.MyApplication;
import cn.adeerlongneck.app.smartschoolapp.Presenster.CreatCoursePresenter;
import cn.adeerlongneck.app.smartschoolapp.R;
import cn.adeerlongneck.app.smartschoolapp.View.CreatCourseView;

public class CreatCourseActivity extends AppCompatActivity implements CreatCourseView {
    CreatCoursePresenter creatCoursePresenter;
    EditText info;
    EditText name;

    Button submit;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_course);
        ini();
    }
    public void ini(){
       creatCoursePresenter=new CreatCoursePresenter(this);
        info=(EditText)findViewById(R.id.info);
        name=(EditText)findViewById(R.id.name);

        submit=(Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check();
            }
        });
    }
    public void check(){
        if(info.getText().toString().isEmpty()|name.getText().toString().isEmpty()){
            Toast.makeText(this,"有空",Toast.LENGTH_SHORT).show();
        }
        else {
            showDialog();
            CourseModel course=new CourseModel();
            MyApplication application = (MyApplication)this.getApplicationContext();
            String stuid=application.getStuid();
            course.setTeacher(stuid);
            course.setName(name.getText().toString());
            course.setInfo(info.getText().toString());
            creatCoursePresenter.postData(course);

        }

    }


    @Override
    public void success() {
        Toast.makeText(this,"创建成功！",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fail() {
        Toast.makeText(this,"创建失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialog() {
        if (progressDialog==null){
            progressDialog= ProgressDialog.show(this,"","正在加载",true,false);

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
}
