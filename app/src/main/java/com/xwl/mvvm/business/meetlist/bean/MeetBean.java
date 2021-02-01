package com.xwl.mvvm.business.meetlist.bean;

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
public class MeetBean implements Serializable {
    private String personnelNo;
    private String personnelName;
    private String meetingTheme;
    private Date toTime;

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

    public String getMeetingTheme() {
        return meetingTheme;
    }

    public void setMeetingTheme(String meetingTheme) {
        this.meetingTheme = meetingTheme;
    }

    public Date getToTime() {
        return toTime;
    }

    public void setToTime(Date toTime) {
        this.toTime = toTime;
    }
}
