package com.xwl.mvvm.business.cardlist.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @ProjectName: mvvm
 * @Package: com.xwl.mvvm.business.cardlist.bean
 * @ClassName: CardBean
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2021/2/1 11:36
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/2/1 11:36
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CardBean implements Serializable {
    private String personnelNo;
    private String personnelName;
    private String deviceName;
    private Date clockInTime;

    public String getPersonnelNo() {
        return personnelNo;
    }

    public void setPersonnelNo(String personnelNo) {
        this.personnelNo = personnelNo;
    }

    public String getPersonnelName() {
        return personnelName;
    }

    public void setPersonnelName(String personnelName) {
        this.personnelName = personnelName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Date getClockInTime() {
        return clockInTime;
    }

    public void setClockInTime(Date clockInTime) {
        this.clockInTime = clockInTime;
    }
}
