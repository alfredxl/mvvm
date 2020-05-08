package com.xwl.mvvm.business.javatest;

import com.xwl.mvvm.base.mvvm.BusinessBaseModel;
import com.xwl.mvvm.base.net.LocalRetrofit;
import com.xwl.mvvm.base.net.NetCallBack;

/**
 * @ProjectName: mvvm
 * @Package: com.xwl.mvvm.business.javatest
 * @ClassName: JavaTestModel
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2020/5/8 9:33
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/8 9:33
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class JavaTestModel extends BusinessBaseModel {
    public <D> void getIpAddress(String id, NetCallBack<D> netCallBack) {
        joinNet(LocalRetrofit.getRetrofit().create(JavaTestProtocol.class).getIpAddress(id), netCallBack);
    }
}
