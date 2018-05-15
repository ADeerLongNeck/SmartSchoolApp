package cn.adeerlongneck.app.smartschoolapp.Model;

/**
 * Created by 长脖鹿 on 2018/5/15.
 */

public class studentSignModel {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getRandoms() {
        return randoms;
    }

    public void setRandoms(String randoms) {
        this.randoms = randoms;
    }

    public String getIssign() {
        return issign;
    }

    public void setIssign(String issign) {
        this.issign = issign;
    }

    String id;
    String stuid;
    String realname;
    String randoms;
    String issign;

}
