package com.xwl.mvvm.base.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: mvvm
 * @Package: com.xwl.mvvm.base.bean
 * @ClassName: PageBean
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2021/2/1 11:32
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/2/1 11:32
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class PageBean<D> {
    private long totalCount;
    private int pageSize;
    private int totalPage = Integer.MAX_VALUE;
    private int currPage;
    private final List<D> list = new ArrayList<>();

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public List<D> getList() {
        return list;
    }
}
