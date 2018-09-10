package cn.adeerlongneck.app.smartschoolapp.Presenster;

import java.util.HashMap;
import java.util.Map;

import cn.adeerlongneck.app.smartschoolapp.Utility.HttpUtil;
import cn.adeerlongneck.app.smartschoolapp.View.CreateSignView;

/**
 * Created by 长脖鹿 on 2018/5/15.
 */

public class CreateSignPresenter {
CreateSignView createSignView;

public CreateSignPresenter(CreateSignView createSignView){

    this.createSignView=createSignView;
}


    public void postdata(String courseid, String time,String jing,String wei) {
        Map<String, String> map = new HashMap<>();
        map.put("courseid",courseid);
        map.put("time",time);
        map.put("la",jing);
        map.put("lo",wei);


        HttpUtil httpUtil = new HttpUtil(new HttpUtil.HttpResponse() {
            @Override
            public void onSuccess(Object object) {
                String res = object.toString();
                System.out.println("============================RANDOM"+res);
                if (res.equals("0")) {

                }else {
                    createSignView.success(res);
                }

            }

            @Override
            public void onFail(String error) {

            }
        });
        httpUtil.sendPostHttp("http://www.adeerlongneck.cn:8000/CreateSignClass", map);
    }



}
