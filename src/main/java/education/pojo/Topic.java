package education.pojo;

import java.util.Date;

public class Topic {
    private int topic_id;  //主题编号

    private Date topic_time;  //发帖时间

    private String topic_name; //主题名称

    private String topic_content;  //主题内容

    private String user_img;//用户头像

    private String user_name;//用户姓名

    private String course_name;  //课程名

    private int replys;  //回复数

    private int looks;  //查看数

    public Topic() {
    }

    public Topic(int topic_id, Date topic_time, String topic_name, String topic_content,String user_img, String user_name, String course_name, int replys, int looks) {
        this.topic_id = topic_id;
        this.topic_time = topic_time;
        this.topic_name = topic_name;
        this.topic_content = topic_content;
        this.user_img = user_img;
        this.user_name = user_name;
        this.course_name = course_name;
        this.replys = replys;
        this.looks = looks;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public Date getTopic_time() {
        return topic_time;
    }

    public void setTopic_time(Date topic_time) {
        this.topic_time = topic_time;
    }

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }

    public String getTopic_content() {
        return topic_content;
    }

    public void setTopic_content(String topic_content) {
        this.topic_content = topic_content;
    }

    public String getUser_img() {
        return user_img;
    }

    public void setUser_img(String user_img) {
        this.user_img = user_img;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public int getReplys() {
        return replys;
    }

    public void setReplys(int replys) {
        this.replys = replys;
    }

    public int getLooks() {
        return looks;
    }

    public void setLooks(int looks) {
        this.looks = looks;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "topic_id=" + topic_id +
                ", topic_time=" + topic_time +
                ", topic_name='" + topic_name + '\'' +
                ", topic_content='" + topic_content + '\'' +
                ", user_img='" + user_img + '\'' +
                ", user_name='" + user_name + '\'' +
                ", course_name=" + course_name +
                ", replys=" + replys +
                ", looks=" + looks +
                '}';
    }
}
