package cn.adeerlongneck.app.smartschoolapp.Model;

/**
 * Created by 长脖鹿 on 2018/5/15.
 */

public class HistoryModel {

    public String getRandoms() {
        return randoms;
    }

    public void setRandoms(String randoms) {
        this.randoms = randoms;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    public String getLa() {
        return la;
    }

    public void setLa(String la) {
        this.la = la;
    }

    public String getLo() {
        return lo;
    }

    public void setLo(String lo) {
        this.lo = lo;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    String randoms;
    String id;
    String courseid;
    String la;
    String lo;
    String times;
}
