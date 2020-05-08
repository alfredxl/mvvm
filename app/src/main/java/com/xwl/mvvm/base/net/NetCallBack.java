package com.xwl.mvvm.base.net;

/**
 * @ProjectName: GcService
 * @Package: com.smart.gc.service.base.net
 * @ClassName: NetCallBack
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2019/11/1 16:54
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/11/1 16:54
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface NetCallBack<D> {
    void onStart() throws Exception;

    void onRequestSuccess(BaseResponse<D> baseResponse);

    void onRequestFailure(Throwable e);

    void onRequestNotWork();
}
