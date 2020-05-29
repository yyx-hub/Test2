package education.pojo;

/**
 * 关注实体类
 * @author xulibin
 */
public class Care {
    private Integer careId;

    private Integer userId;

    private Integer courseId;

    public Integer getCareId() {
        return careId;
    }

    public void setCareId(Integer careId) {
        this.careId = careId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Care{" +
                "careId=" + careId +
                ", userId=" + userId +
                ", courseId=" + courseId +
                '}';
    }
}