package cn.adeerlongneck.app.smartschoolapp.Presenster;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.adeerlongneck.app.smartschoolapp.Model.CourseModel;
import cn.adeerlongneck.app.smartschoolapp.Utility.HttpUtil;

/**
 * Created by chao on 2018/5/10.
 */

public class TeacherSignPresenter {
    List<CourseModel> list;

    public List<CourseModel> getCourseData(String teacher) {
        list = new ArrayList<>();
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
        return list;


    }

    public List<CourseModel> dealData(String data) {
        Gson gson = new Gson();
        List<CourseModel> courseModels = gson.fromJson(data, new TypeToken<List<CourseModel>>() {
        }.getType());
        list = courseModels;
        return courseModels;
    }
}
