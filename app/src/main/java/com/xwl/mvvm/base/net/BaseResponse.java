package com.xwl.mvvm.base.net;

/**
 * @ProjectName: GcService
 * @Package: com.smart.gc.service.base.net
 * @ClassName: BaseResponse
 * @Description: 接口返回基类
 * @Author: 谢文良
 * @CreateDate: 2019/11/1 16:52
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/11/1 16:52
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BaseResponse<D> {
    private int code;
    private String msg;
    private D data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }
}
