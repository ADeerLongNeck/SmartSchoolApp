package cn.adeerlongneck.app.smartschoolapp.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.adeerlongneck.app.smartschoolapp.Adapter.TeacherSignAdapter;
import cn.adeerlongneck.app.smartschoolapp.Model.CourseModel;
import cn.adeerlongneck.app.smartschoolapp.MyApplication;
import cn.adeerlongneck.app.smartschoolapp.Presenster.TeacherSignPresenter;
import cn.adeerlongneck.app.smartschoolapp.R;
import cn.adeerlongneck.app.smartschoolapp.Utility.HttpUtil;
import cn.adeerlongneck.app.smartschoolapp.View.TeacherSignView;

public class TeacherSignActivity extends AppCompatActivity implements TeacherSignView {
    Button bt_re;
    TeacherSignPresenter teacherSignPresenter;

    String teacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_sign);


        ini();
    }

    public void ini() {
        MyApplication application = (MyApplication) getApplicationContext();
        teacher = application.getStuid();

        getData();


        bt_re = (Button) findViewById(R.id.ref);
        bt_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });
        setToolBar();

    }

    public void getData() {

        Map<String, String> map = new HashMap<>();

        map.put("teacher", teacher);

        HttpUtil httpUtil = new HttpUtil(new HttpUtil.HttpResponse() {
            @Override
            public void onSuccess(Object object) {
                Log.d("1", object.toString());
                String data = object.toString();

                dealData(data);

            }

            @Override
            public void onFail(String error) {
                Log.d("1", "11111111113211111111111111111111111--------------------1" + error);

            }
        });
        httpUtil.sendPostHttp("http://www.adeerlongneck.cn:8000/SelectTeacherClass", map);


    }
    public void setToolBar(){
        ImageView im = (ImageView)findViewById(R.id.fanhui);
        TextView tx_title=(TextView)findViewById(R.id.toolbar_title);
        tx_title.setText("我的课程");
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public List<CourseModel> dealData(String data) {
        List<CourseModel> list = new ArrayList<>();
        Gson gson = new Gson();
        List<CourseModel> courseModel = gson.fromJson(data, new TypeToken<List<CourseModel>>() {
        }.getType());

        for (CourseModel courseModel2 : courseModel) {
            Log.d("111-----", courseModel2.getName());

            list.add(courseModel2);
        }


        teacherSignPresenter = new TeacherSignPresenter();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rec1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        TeacherSignAdapter teacherSignAdapter = new TeacherSignAdapter(list, this,this);
        recyclerView.setAdapter(teacherSignAdapter);


        return list;
    }


    @Override
    public void success() {

    }

    @Override
    public void fail() {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void re() {
        getData();
    }
}
