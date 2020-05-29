package education.pojo;

public class Block {
    private Integer blockId;//板块id

    private String blockName;//板块名

    private String blockDesc;//板块描述

    private Integer blockOrder;//板块

    private Integer blockVisibility;//板块显示属性

    /**
     * 有参无参构造方法
     */
    public Block() {
    }

    public Block(Integer blockId, String blockName, String blockDesc, Integer blockOrder, Integer blockVisibility) {
        this.blockId = blockId;
        this.blockName = blockName;
        this.blockDesc = blockDesc;
        this.blockOrder = blockOrder;
        this.blockVisibility = blockVisibility;
    }

    /**
     * get和set方法
     * @return
     */
    public Integer getBlockId() {
        return blockId;
    }

    public void setBlockId(Integer blockId) {
        this.blockId = blockId;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName == null ? null : blockName.trim();
    }

    public String getBlockDesc() {
        return blockDesc;
    }

    public void setBlockDesc(String blockDesc) {
        this.blockDesc = blockDesc == null ? null : blockDesc.trim();
    }

    public Integer getBlockOrder() {
        return blockOrder;
    }

    public void setBlockOrder(Integer blockOrder) {
        this.blockOrder = blockOrder;
    }

    public Integer getBlockVisibility() {
        return blockVisibility;
    }

    public void setBlockVisibility(Integer blockVisibility) {
        this.blockVisibility = blockVisibility;
    }

    /**
     * toString方法
     * @return
     */
    @Override
    public String toString() {
        return "Block{" +
                "blockId=" + blockId +
                ", blockName='" + blockName + '\'' +
                ", blockDesc='" + blockDesc + '\'' +
                ", blockOrder=" + blockOrder +
                ", blockVisibility=" + blockVisibility +
                '}';
    }
}