package cn.adeerlongneck.app.smartschoolapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.adeerlongneck.app.smartschoolapp.Adapter.SIgnHistoryAdapter;
import cn.adeerlongneck.app.smartschoolapp.Adapter.TeacherSignAdapter;
import cn.adeerlongneck.app.smartschoolapp.Model.CourseModel;
import cn.adeerlongneck.app.smartschoolapp.Model.HistoryModel;
import cn.adeerlongneck.app.smartschoolapp.Presenster.TeacherSignPresenter;
import cn.adeerlongneck.app.smartschoolapp.R;
import cn.adeerlongneck.app.smartschoolapp.Utility.HttpUtil;

public class HistoryActivity extends AppCompatActivity {
     String courseid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Intent intent = getIntent();
        //从Intent当中根据key取得value
        if (intent != null) {
            courseid = intent.getStringExtra("courseid");
        }
        getData();

    }
    public void getData() {

        Map<String, String> map = new HashMap<>();

        map.put("courseid", courseid);

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
        httpUtil.sendPostHttp("http://www.adeerlongneck.cn:8000/SelectRandom", map);


    }

    public List<HistoryModel> dealData(String data) {
        List<HistoryModel> list = new ArrayList<>();
        Gson gson = new Gson();
        List<HistoryModel> courseModel = gson.fromJson(data, new TypeToken<List<HistoryModel>>() {
        }.getType());

        for (HistoryModel courseModel2 : courseModel) {
            Log.d("111-----", courseModel2.getRandoms());

            list.add(courseModel2);
        }



        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rec1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        SIgnHistoryAdapter sIgnHistoryAdapter = new SIgnHistoryAdapter(list,this);
        recyclerView.setAdapter(sIgnHistoryAdapter);


        return list;
    }
}
