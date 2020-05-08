package com.xwl.mvvm.business.javatest;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.lifecycle.LifecycleOwner;

import com.xwl.common.util.RxBus;
import com.xwl.mvvm.BR;
import com.xwl.mvvm.base.mvvm.BusinessBaseViewModel;
import com.xwl.mvvm.base.net.BaseResponse;
import com.xwl.mvvm.business.javatest.bean.NetMessage;

/**
 * @ProjectName: mvvm
 * @Package: com.xwl.mvvm.business.javatest
 * @ClassName: JavaTestViewModel
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2020/5/8 9:34
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/8 9:34
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class JavaTestViewModel extends BusinessBaseViewModel<JavaTestModel> {
    private NetMessage netMessage;

    @Bindable
    public NetMessage getNetMessage() {
        return netMessage;
    }

    private void setNetMessage(NetMessage netMessage) {
        this.netMessage = netMessage;
        notifyPropertyChanged(BR.netMessage);
    }

    public JavaTestViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        // RxBus监听注册
        toObservable(this::setNetMessage, NetMessage.class);
    }

    // 加载网络数据
    public void loadNetData() {
        model.getIpAddress("28654780", new BusinessNetCallBack<NetMessage>("加载中...", true) {
            @Override
            public void onRequestSuccess(BaseResponse<NetMessage> baseResponse) {
                super.onRequestSuccess(baseResponse);
                if (baseResponse != null) {
                    setNetMessage(baseResponse.getData());
                }
            }
        });
    }

    // 发送RxBus消息
    public void sendRxBus() {
        NetMessage netMessage = new NetMessage();
        netMessage.setText("RxBus");
        netMessage.setName("mvvm");
        RxBus.getInstance().post(netMessage);
    }
}
