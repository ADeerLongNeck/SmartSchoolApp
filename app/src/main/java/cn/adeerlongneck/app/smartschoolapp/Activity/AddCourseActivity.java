package cn.adeerlongneck.app.smartschoolapp.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.adeerlongneck.app.smartschoolapp.Adapter.AllCourseAdapter;
import cn.adeerlongneck.app.smartschoolapp.Adapter.TeacherSignAdapter;
import cn.adeerlongneck.app.smartschoolapp.Model.CourseModel;
import cn.adeerlongneck.app.smartschoolapp.MyApplication;
import cn.adeerlongneck.app.smartschoolapp.Presenster.AddCoursePresent;
import cn.adeerlongneck.app.smartschoolapp.Presenster.TeacherSignPresenter;
import cn.adeerlongneck.app.smartschoolapp.R;
import cn.adeerlongneck.app.smartschoolapp.Utility.HttpUtil;
import cn.adeerlongneck.app.smartschoolapp.View.AddCourseView;

public class AddCourseActivity extends AppCompatActivity implements AddCourseView {

    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        List<CourseModel> list = new ArrayList<>();
                      getData();
                      setToolBar();
    }
 public void setToolBar(){
     ImageView im = (ImageView)findViewById(R.id.fanhui);
     TextView tx_title=(TextView)findViewById(R.id.toolbar_title);
     tx_title.setText("添加课程");
     im.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             finish();
         }
     });
 }

    public void getData() {

        Map<String, String> map = new HashMap<>();

        map.put("teacher", "");

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
        httpUtil.sendPostHttp("http://adeerlongneck.cn:8000/ListAllClass", map);


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


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rec2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        AllCourseAdapter allCourseAdapter = new  AllCourseAdapter(list, this, this);
        recyclerView.setAdapter(allCourseAdapter);


        return list;
    }



    @Override
    public void success() {
        Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void fail() {
        Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void add(String courseid) {

        MyApplication application = (MyApplication) getApplicationContext();
        final String stuid = application.getStuid();
        final AddCoursePresent addCoursePresent = new AddCoursePresent(this);

                addCoursePresent.add(courseid, stuid);

    }
}
