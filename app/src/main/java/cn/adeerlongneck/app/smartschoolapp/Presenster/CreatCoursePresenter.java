package cn.adeerlongneck.app.smartschoolapp.Presenster;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import cn.adeerlongneck.app.smartschoolapp.Model.CourseModel;
import cn.adeerlongneck.app.smartschoolapp.Utility.HttpUtil;
import cn.adeerlongneck.app.smartschoolapp.View.CreatCourseView;

/**
 * Created by chao on 2018/4/18.
 */

public class CreatCoursePresenter {
    CreatCourseView creatCourseView;

    public CreatCoursePresenter(CreatCourseView creatCourseView) {
        this.creatCourseView = creatCourseView;
    }


    public void postData(CourseModel course) {
        Map<String, String> map = new HashMap<>();
        map.put("name", course.getName());
        map.put("info", course.getInfo());
        map.put("teacher", course.getTeacher());
        map.put("classid", "null");
        HttpUtil httpUtil = new HttpUtil(new HttpUtil.HttpResponse() {
            @Override
            public void onSuccess(Object object) {
                Log.d("1", "2222222222222222222222221--------------------1");
                creatCourseView.success();
                creatCourseView.hideDialog();
            }

            @Override
            public void onFail(String error) {
                Log.d("1", "11111111113211111111111111111111111--------------------1");
                creatCourseView.fail();
                creatCourseView.hideDialog();
            }
        });
        httpUtil.sendPostHttp("http://www.adeerlongneck.cn:8000/Createcourse", map);


    }


}
