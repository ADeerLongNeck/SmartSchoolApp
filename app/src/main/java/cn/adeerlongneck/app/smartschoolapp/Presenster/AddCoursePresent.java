package cn.adeerlongneck.app.smartschoolapp.Presenster;

import java.util.HashMap;
import java.util.Map;

import cn.adeerlongneck.app.smartschoolapp.Utility.HttpUtil;
import cn.adeerlongneck.app.smartschoolapp.View.AddCourseView;

/**
 * Created by chao on 2018/5/2.
 */

public class AddCoursePresent {
    AddCourseView addCourseView;

    public AddCoursePresent(AddCourseView addCourseView) {
        this.addCourseView = addCourseView;
    }

    public void add(String courseid, String stuid) {


        Map<String, String> map = new HashMap<>();
        map.put("studentid", stuid);
        map.put("courseid", courseid);
        HttpUtil httpUtil = new HttpUtil(new HttpUtil.HttpResponse() {
            @Override
            public void onSuccess(Object object) {
                String res = object.toString();
                if (res.equals("1")) {
                    addCourseView.success();
                }

            }

            @Override
            public void onFail(String error) {

            }
        });
        httpUtil.sendPostHttp("http://www.adeerlongneck.cn:8000/Chosecourse", map);
    }


}
