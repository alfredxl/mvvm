package com.xwl.mvvm.base.net;

/**
 * @ProjectName: mvvm
 * @Package: com.xwl.mvvm.base.net
 * @ClassName: NeedLoadError
 * @Description: 需要登录异常
 * @Author: 谢文良
 * @CreateDate: 2020/5/8 9:21
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/8 9:21
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class NeedLoadError extends RuntimeException {
    public NeedLoadError(String message) {
        super(message);
    }
}
