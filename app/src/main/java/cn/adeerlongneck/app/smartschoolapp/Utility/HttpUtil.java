package cn.adeerlongneck.app.smartschoolapp.Utility;

import android.os.Looper;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 长脖鹿 on 2017/12/15.
 */

public class HttpUtil {
    private String mUrl;
    private Map<String,String> pamrameter;//参数
    Request request;
    HttpResponse mhttpResponse;
    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    OkHttpClient client =new OkHttpClient();
    OkHttpClient client2 =new OkHttpClient();

    android.os.Handler mhandler=new android.os.Handler(Looper.getMainLooper());


    public interface HttpResponse{
        void onSuccess(Object object);
        void onFail(String error);
    }

    public HttpUtil(HttpResponse httpResponse){

        mhttpResponse=httpResponse;

    }

    public void sendPostHttp(String url,Map<String,String> pamram){
        mUrl=url;
        pamrameter=pamram;
        sendHttp(mUrl,pamrameter,true);


    }

    public void sendGetHttp(String url,Map<String,String> pamram){
        mUrl=url;
        pamrameter=pamram;
        sendHttp(mUrl,pamrameter,false);
    }

    private void sendHttp(String url,Map<String,String> pamram ,boolean isPost){
        mUrl=url;
        pamrameter=pamram;
        run(isPost);
    }

    private  void run(boolean isPost){
        Request request=creatRequest(isPost);

        client=new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (mhttpResponse != null) {

                    mhandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mhttpResponse.onFail("请求错误");
                        }
                    });

                }
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (mhttpResponse == null) return;
                mhandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (!response.isSuccessful()) {
                            mhttpResponse.onFail("失败code="+response);
                        } else {
                            try {
                                mhttpResponse.onSuccess(response.body().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                                mhttpResponse.onFail("转换失败");
                            }
                        }
                    }
                });
            }

        });
    }





    private Request creatRequest(boolean isPost) {
        Request request = null;
        if (isPost) {
            //如果是post请求，构造Post请求参数；

            MultipartBody.Builder multipartBody = new MultipartBody.Builder();
            multipartBody.setType(MultipartBody.FORM);
            Iterator<Map.Entry<String, String>> iterator = pamrameter.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                multipartBody.addFormDataPart(entry.getKey(), entry.getValue());
            }
            request = new okhttp3.Request.Builder()
//                    .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; rv:56.0) Gecko/20100101 Firefox/56.0")
//                    .addHeader("Referer","http://ecampus.ujn.edu.cn")
//                    .addHeader("Accept-Language","en-US,en;q=0.8,zh-Hans-CN;q=0.5,zh-Hans;q=0.3")
//                    .addHeader("host","auth.ujn.edu.cn")
//                    .addHeader("Accept-Encoding","deflate")
//                    .addHeader("Accept","text/html, application/xhtml+xml, image/jxr, */*")
//                    .addHeader("Connection","Keep-Alive")
//                    .addHeader("Content-Type","application/x-www-form-urlencoded")
//                    .addHeader("Upgrade-Insecure-Requests","1")
                    .url(mUrl)
                    .post(multipartBody.build())
                    .build();
        } else {
            //如果是Get请求构造Get请求参数
            String url=mUrl+"?"+mapToString(pamrameter);
            request =new Request.Builder().url(url).build();

        }

        return request;
    }

    private String mapToString(Map<String,String>pamrameter){
        //写一个字符串拼接函数
        StringBuilder stringBuilder =new StringBuilder();
        Iterator<Map.Entry<String, String>> iterator = pamrameter.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            stringBuilder.append(entry.getKey()+"="+entry.getValue()+"&");
        }
        String str =stringBuilder.toString().substring(0,stringBuilder.length()-1);
        return  str;
    }



}