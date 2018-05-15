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

import cn.adeerlongneck.app.smartschoolapp.Adapter.HistoryInfoAdapter;
import cn.adeerlongneck.app.smartschoolapp.Adapter.SIgnHistoryAdapter;
import cn.adeerlongneck.app.smartschoolapp.Model.HistoryModel;
import cn.adeerlongneck.app.smartschoolapp.Model.studentSignModel;
import cn.adeerlongneck.app.smartschoolapp.R;
import cn.adeerlongneck.app.smartschoolapp.Utility.HttpUtil;

public class HistoryInfoActivity extends AppCompatActivity {
    String random;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_info);
        Intent intent = getIntent();
        //从Intent当中根据key取得value
        if (intent != null) {
            random = intent.getStringExtra("random");
        }
        getData();

    }
    public void getData() {

        Map<String, String> map = new HashMap<>();

        map.put("random", random);

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
        httpUtil.sendPostHttp("http://www.adeerlongneck.cn:8000/SelectSign", map);


    }

    public List<studentSignModel> dealData(String data) {
        List<studentSignModel> list = new ArrayList<>();
        Gson gson = new Gson();
        List<studentSignModel> courseModel = gson.fromJson(data, new TypeToken<List<studentSignModel>>() {
        }.getType());

        for (studentSignModel courseModel2 : courseModel) {
            Log.d("111-----", courseModel2.getRandoms());

            list.add(courseModel2);
        }



        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rec1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        HistoryInfoAdapter historyInfoAdapter= new HistoryInfoAdapter(list,this);
        recyclerView.setAdapter(historyInfoAdapter);


        return list;
    }
}
