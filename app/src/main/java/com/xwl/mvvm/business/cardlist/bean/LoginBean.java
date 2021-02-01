package com.xwl.mvvm.business.cardlist.bean;

import java.io.Serializable;

/**
 * @ProjectName: mvvm
 * @Package: com.xwl.mvvm.business.cardlist.bean
 * @ClassName: LoginBean
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2021/2/1 10:18
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/2/1 10:18
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LoginBean implements Serializable {
    private String username;
    private String password;
    private String captcha;
    private long t;
    private String uuid;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public long getT() {
        return t;
    }

    public void setT(long t) {
        this.t = t;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
