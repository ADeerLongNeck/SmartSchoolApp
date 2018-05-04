package cn.adeerlongneck.app.smartschoolapp;

import android.app.Application;

/**
 * Created by chao on 2018/4/18.
 */

public class MyApplication extends Application {
    String stuid;
    String name;

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
