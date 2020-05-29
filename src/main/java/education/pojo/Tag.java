package education.pojo;


/*
 * @Description:标签实体类
 * @author nss
 * @date 2019/4/8 13:58
 */
public class Tag {

    private Integer tagId;//标签id

    private String tagName;// 标签名

    private String tagDesc;//标签描述

    /**
     * 标签实体类的有参无参构造方法
     */
    public Tag() {
    }

    public Tag(Integer tagId, String tagName, String tagDesc) {
        this.tagId = tagId;
        this.tagName = tagName;
        this.tagDesc = tagDesc;
    }

    /**
     * get,set方法
     * @return
     */
    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName == null ? null : tagName.trim();
    }

    public String getTagDesc() {
        return tagDesc;
    }

    public void setTagDesc(String tagDesc) {
        this.tagDesc = tagDesc == null ? null : tagDesc.trim();
    }

    /**
     * toString 方法
     * @return
     */
    @Override
    public String toString() {
        return "Tag{" +
                "tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                ", tagDesc='" + tagDesc + '\'' +
                '}';
    }
}