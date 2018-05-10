package cn.adeerlongneck.app.smartschoolapp.Model;

/**
 * Created by chao on 2018/4/18.
 */

public class CourseModel {
    String teacher;
    String classid;
    String name;
    String info;
    String id;

    public CourseModel(String name, String info) {
        this.name = name;
        this.info = info;
    }

    public CourseModel() {

    }
    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
