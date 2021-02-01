package com.xwl.mvvm.business.cardlist.bean;

/**
 * @ProjectName: mvvm
 * @Package: com.xwl.mvvm.business.cardlist.bean
 * @ClassName: CardListRequest
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2021/2/1 11:27
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/2/1 11:27
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CardListRequest {
    private int clockInType = 2;
    private int schoolId = 2;
    private String order = "desc";
    private String sidx = "c.clock_in_time";
    private int page = 1;
    private int limit = 10;

    public int getClockInType() {
        return clockInType;
    }

    public void setClockInType(int clockInType) {
        this.clockInType = clockInType;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
