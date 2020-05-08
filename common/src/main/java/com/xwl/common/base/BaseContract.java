package com.xwl.common.base;

/**
 * @ProjectName: GcService
 * @Package: com.smart.gc.common.base
 * @ClassName: BaseContract
 * @Description: 基础契约，VM回调V
 * @Author: 谢文良
 * @CreateDate: 2019/11/7 16:48
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/11/7 16:48
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface BaseContract {
    void showLoading(String message);
    void showDialog(int tileRes, int messageRes);
    void showDialog(int tileRes, int messageRes, boolean endFinish);
    void showDialog(String tile, String message);
    void showDialog(String tile, String message, boolean endFinish);
    void dismissLoading();
    void dismissDialog();
    void finish();
    void onBackPressed();
    void showToast(String message);
}
