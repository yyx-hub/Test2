package education.util;

import java.util.List;

/**
 * 封装分页查询
 */

public class PageBean {
    /**
     *  当前页码
     */
    private int  pageIndex;


    /**
     *  每页最大显示记录数
     */
    private int  pageSize;


    /**
     *  总记录数
     */
    private int  pageCount;


    /**
     *  总页数
     */
    private int  totalCount;


    /**
     * 需要分页的数据
     */
    private List pageList;

    public PageBean() {
    }

    public PageBean(int pageIndex, int pageSize, int pageCount, int totalCount, List pageList) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.pageCount = pageCount;
        this.totalCount = totalCount;
        this.pageList = pageList;
    }


    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getTotalCount() {
        totalCount=this.getPageCount()%this.getPageSize()==0?this.getPageCount()/this.getPageSize() :this.getPageCount()/this.getPageSize()+1;
        return totalCount ;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List getPageList() {
        return pageList;
    }

    public void setPageList(List pageList) {
        this.pageList = pageList;
    }

}
