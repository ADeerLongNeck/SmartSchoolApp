package cn.adeerlongneck.app.smartschoolapp.Model;

/**
 * Created by 长脖鹿 on 2017/11/16.
 */

public class userModel {
    String realname;
    String nickname;
    String password;
    String phonenumber;
    String stuid;
    String classid;

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public boolean isstu() {
        return isstu;
    }

    public void setIsstu(boolean isstu) {
        this.isstu = isstu;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    boolean isstu;
    String age;
    boolean sex;
}
