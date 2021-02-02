package com.xwl.mvvm.business.meetlist.bean;

import androidx.annotation.NonNull;

import java.io.Serializable;

/**
 * @ProjectName: mvvm
 * @Package: com.xwl.mvvm.business.meetlist.bean
 * @ClassName: MeetListRequest
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2021/2/1 15:31
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/2/1 15:31
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MeetListRequest implements Serializable, Cloneable {
    private int schoolId = 2;
    private String order = "desc";
    private String sidx = "to_time";
    private int page = 1;
    private int limit = 10;
    private String startTime;
    private String endTime;
    private String personnelName;
    private String personnelNo;

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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPersonnelName() {
        return personnelName;
    }

    public void setPersonnelName(String personnelName) {
        this.personnelName = personnelName;
    }

    public String getPersonnelNo() {
        return personnelNo;
    }

    public void setPersonnelNo(String personnelNo) {
        this.personnelNo = personnelNo;
    }

    @NonNull
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
