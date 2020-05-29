package education.pojo;

/**
 * 路径表实体类
 * @author xulibin
 */
public class Route {
    private Integer routeId;

    private String routeName;

    private String routeDesc;

    private Integer routeCount;

    private Integer courseCount;

    private String routeDetail;

    private String comment;

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName == null ? null : routeName.trim();
    }

    public String getRouteDesc() {
        return routeDesc;
    }

    public void setRouteDesc(String routeDesc) {
        this.routeDesc = routeDesc == null ? null : routeDesc.trim();
    }

    public Integer getRouteCount() {
        return routeCount;
    }

    public void setRouteCount(Integer routeCount) {
        this.routeCount = routeCount;
    }

    public Integer getCourseCount() {
        return courseCount;
    }

    public void setCourseCount(Integer courseCount) {
        this.courseCount = courseCount;
    }

    public String getRouteDetail() {
        return routeDetail;
    }

    public void setRouteDetail(String routeDetail) {
        this.routeDetail = routeDetail == null ? null : routeDetail.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    @Override
    public String toString() {
        return "Route{" +
                "routeId=" + routeId +
                ", routeName='" + routeName + '\'' +
                ", routeDesc='" + routeDesc + '\'' +
                ", routeCount=" + routeCount +
                ", courseCount=" + courseCount +
                ", routeDetail='" + routeDetail + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}