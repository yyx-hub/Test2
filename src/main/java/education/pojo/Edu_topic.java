package education.pojo;

import java.util.Date;

/**
 * @Created: Edu_topicService
 * @Author: 高晋成 on 2019/4/10 9:07
 * @Description: 帖子表的实体类
 */
public class Edu_topic {

    private int topic_id;  //主题编号

    private Date topic_time;  //发帖时间

    private String topic_name; //主题名称

    private String topic_content;  //主题内容

    private int block_id;  //板块编号

    private int user_id;  //用户编号

    private int course_id;  //课程编号

    private int replys;  //回复数

    private int looks;  //查看数
    public Edu_topic() {
    }

    public Edu_topic(int topic_id, Date topic_time, String topic_name, String topic_content, int block_id, int user_id, int course_id, int replys, int looks) {
        this.topic_id = topic_id;
        this.topic_time = topic_time;
        this.topic_name = topic_name;
        this.topic_content = topic_content;
        this.block_id = block_id;
        this.user_id = user_id;
        this.course_id = course_id;
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

    public int getBlock_id() {
        return block_id;
    }

    public void setBlock_id(int block_id) {
        this.block_id = block_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
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
        return "Edu_topic{" +
                "topic_id=" + topic_id +
                ", topic_time=" + topic_time +
                ", topic_name='" + topic_name + '\'' +
                ", topic_content='" + topic_content + '\'' +
                ", block_id=" + block_id +
                ", user_id=" + user_id +
                ", course_id=" + course_id +
                ", replys=" + replys +
                ", looks=" + looks +
                '}';
    }
}
