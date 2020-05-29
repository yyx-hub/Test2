package education.pojo;

import java.util.Date;

public class Course {
    private Integer courseId;//课程ID

    private String courseName;//课程名称

    private Integer tagId;//标签ID

    private Date onlineDate;//上线日期

    private String courseDesc;//课程描述

    private Integer teacherId;//教师ID

    private String imgAdd;//图片路径

    private Integer coursePrice;//课程价格

    private Integer courseHour;//课时

    private Integer courseEasy;//难易程度

    public Course() {
    }

    public Course(Integer courseId, String courseName, Integer tagId, Date onlineDate, String courseDesc, Integer teacherId, String imgAdd, Integer coursePrice, Integer courseHour, Integer courseEasy) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.tagId = tagId;
        this.onlineDate = onlineDate;
        this.courseDesc = courseDesc;
        this.teacherId = teacherId;
        this.imgAdd = imgAdd;
        this.coursePrice = coursePrice;
        this.courseHour = courseHour;
        this.courseEasy = courseEasy;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Date getOnlineDate() {
        return onlineDate;
    }

    public void setOnlineDate(Date onlineDate) {
        this.onlineDate = onlineDate;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc == null ? null : courseDesc.trim();
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getImgAdd() {
        return imgAdd;
    }

    public void setImgAdd(String imgAdd) {
        this.imgAdd = imgAdd;
    }

    public Integer getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(Integer coursePrice) {
        this.coursePrice = coursePrice;
    }

    public Integer getCourseHour() {
        return courseHour;
    }

    public void setCourseHour(Integer courseHour) {
        this.courseHour = courseHour;
    }

    public Integer getCourseEasy() {
        return courseEasy;
    }

    public void setCourseEasy(Integer courseEasy) {
        this.courseEasy = courseEasy;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", tagId=" + tagId +
                ", onlineDate=" + onlineDate +
                ", courseDesc='" + courseDesc + '\'' +
                ", teacherId=" + teacherId +
                ", imgAdd='" + imgAdd + '\'' +
                ", coursePrice=" + coursePrice +
                ", courseHour=" + courseHour +
                ", courseEasy=" + courseEasy +
                '}';
    }
}