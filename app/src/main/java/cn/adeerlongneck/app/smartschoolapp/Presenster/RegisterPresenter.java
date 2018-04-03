package cn.adeerlongneck.app.smartschoolapp.Presenster;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.adeerlongneck.app.smartschoolapp.Activity.RegisterActivity;
import cn.adeerlongneck.app.smartschoolapp.Model.userModel;
import cn.adeerlongneck.app.smartschoolapp.Utility.HttpUtil;
import cn.adeerlongneck.app.smartschoolapp.View.RegisterActivityView;
import cn.bmob.newsmssdk.BmobSMS;
import cn.bmob.newsmssdk.exception.BmobException;
import cn.bmob.newsmssdk.listener.RequestSMSCodeListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


/**
 * Created by 长脖鹿 on 2017/12/16.
 */

public class RegisterPresenter {
    Map<String,String> prama;
    String stuid;
    int ERRORCODE=4;
RegisterActivityView registerActivityView;
Context context;


    public RegisterPresenter(RegisterActivityView registerActivityView,Context context){
this.registerActivityView=registerActivityView;
this.context=context;
 }


    /**
     * 判断是否注册
     * @param stuid
     * @param phonenumber
     * @return
     */
    public int isRegister(String stuid, final String phonenumber){


     Log.d("222","jinru");

        prama = new HashMap<String, String>();
        prama.put("stuid",stuid);
        prama.put("phone",phonenumber);
        this.stuid=stuid;
        HttpUtil httpUtil =new HttpUtil(new HttpUtil.HttpResponse() {
            @Override
            public void onSuccess(Object object) {
                //取得服务器返回的数据
                String respone=object.toString();
                Log.d("1111111"+respone+"1111111","1111111"+respone);
                String respones=respone.substring(0,1);
                if(respones.equals("1")){
                    //学号已经被注册了
                    ERRORCODE=1;
                }
                if(respones.equals("2")){
                    //手机号号已经被注册了
                    ERRORCODE=2;
                }
                if(respones.equals("3")){
                    //都被注册了

                    ERRORCODE=3;
                }
                if(respones.equals("0")){
                    Log.d("2224","error");
                    registerActivityView.sendYZM();
                    //没有注册，可以进行下一步
                    ERRORCODE=0;

                }

            }
            @Override
            public void onFail(String error) {
                //请求失败
                Log.d("44444444444444444444",error);
            }
        });
        httpUtil.sendPostHttp("http://www.adeerlongneck.cn/smartschool/isregister.php",prama);
        return ERRORCODE;
    }

    public void getYZM(String stuid,String phonenumber){
        isRegister(stuid, phonenumber);

    }



    /**
     * 入口
     * @param bitmap
     */
    public void  dealPhoto(Bitmap bitmap){
String a=     bitmapToBase64(bitmapdeal(bitmap));
Log.d("123",a);



    }

    /**
     * 图片转base64
     * @param
     * @return
     */

    public static Bitmap transform_Cut(byte[] rawImage){
        int width,height;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; // 只获取图片的大小信息，而不是将整张图片载入在内存中，避免内存溢出
        BitmapFactory.decodeByteArray(rawImage, 0, rawImage.length, options);
        options.inJustDecodeBounds = false; // 计算好压缩比例后，这次可以去加载原图了
        if (options.outWidth<1000){
            options.inSampleSize = 1;
        }else if (options.outWidth<2400) {
            options.inSampleSize = 2; // 设置为刚才计算的压缩比例
        }else{
            options.inSampleSize = 4;
        }
        options.inPreferredConfig = Bitmap.Config.RGB_565;//该模式是默认的,可不设
        options.inPurgeable = true;// 同时设置才会有效
        options.inInputShareable = true;//。当系统内存不够时候图片自动被回收
        Bitmap bm = BitmapFactory.decodeByteArray(rawImage, 0, rawImage.length, options); // 解码文件
        //Log.d("bm的width",bm.getWidth()+"");
        //Log.d("bm的height",bm.getHeight()+"");
        width=bm.getWidth();
        height=bm.getHeight();
        double x=(float)1/(float)4;
        double y=(float)2/(float)9;
        double _width=(float)1/(float)2;
        double _height=(float)4/(float)7;
        Bitmap bmp= Bitmap.createBitmap(bm,(int)(width*x),(int)(height*y),(int)(width*_width),(int)(height*_height));

        return  bmp;
    }

    /**
     * 压缩图片
     * @param image
     * @return
     */
    public Bitmap encodeImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 90;
        while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset(); // 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
            Log.d("baos大小",baos.toByteArray().length/1024+"");
            Log.d("options大小",options+"");
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        bitmapToBase64(bitmap);
        return bitmap;

    }


    /**
     * 裁剪压缩完的图片
     * @param bitmap
     * @return
     */

    public Bitmap bitmapdeal(Bitmap bitmap) {
        String result = null;
        Bitmap bitmapok = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
                baos.flush();
                baos.close();
                byte[] bitmapBytes = baos.toByteArray();

         bitmapok  = encodeImage(transform_Cut(bitmapBytes)) ;

            }
        } catch (IOException e) {

        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {

            }
        }
//        Log.d("22",result);
        return bitmapok;
    }


    public String bitmapToBase64(Bitmap bitmap) {
        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
                baos.flush();
                baos.close();
                byte[] bitmapBytes = baos.toByteArray();

                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            return "";
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                return "";
            }
        }
        Log.d("22",result);
        return result;
    }


    /**
     * 注册
     *
     * @param
     */


    public  void register(Map<String,String> map){
     //   Map<String,String> map =new HashMap<>();
//        map.put("stuid",user.getStuid());
//        map.put("realname",user.getRealname());
//        map.put("password",user.getPassword());
//        map.put("phone",user.getPhonenumber());

        HttpUtil httpUtil = new HttpUtil(new HttpUtil.HttpResponse() {
            @Override
            public void onSuccess(Object object) {
                String respone=object.toString();
                String respones=respone.substring(0,1);
                Log.d("==",respones);
                    if(respones.equals("0")){

                        registerActivityView.showTx("注册成功");
                    }
else {
                        registerActivityView.showTx("注册失败");
                    }


            }

            @Override
            public void onFail(String error) {

            }
        });
        httpUtil .sendPostHttp("http://www.adeerlongneck.cn/smartschool/register.php",map);







    }



/**
 * 上传图片
 */
public void upImage(Bitmap bm) {


    OkHttpClient client=new OkHttpClient();
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    bm.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);

    MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart("img_1","1.jpg", RequestBody.create(MediaType.parse("image/jpeg"),byteArrayOutputStream.toByteArray()))
            ;
    //有多个图片就用for循环添加即可

    MultipartBody build = builder.build();

    okhttp3.Request bi = new okhttp3.Request.Builder()
            .url("http://www.adeerlongneck.cn/you/ii.php")
            .post(build)
            .build();



    client.newCall(bi).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.i("TAG", "onFailure: 失败");
        }

        @Override
        public void onResponse(Call call, okhttp3.Response response) throws IOException {
            Log.i("TAG", "onResponse: " + response.body().string());
            //提交成功处理结果....
        }
    });
}

}




