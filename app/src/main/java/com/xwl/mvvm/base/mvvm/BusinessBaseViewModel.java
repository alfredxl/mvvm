package com.xwl.mvvm.base.mvvm;

import android.app.Application;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;

import com.xwl.common.base.BaseContract;
import com.xwl.common.base.BaseViewModel;
import com.xwl.mvvm.R;
import com.xwl.mvvm.base.net.BaseResponse;
import com.xwl.mvvm.base.net.NeedLoadError;
import com.xwl.mvvm.base.net.NetCallBack;
import com.xwl.mvvm.base.net.NetUtil;
import com.xwl.mvvm.base.util.UserUtil;

/**
 * @ProjectName: GcService
 * @Package: com.smart.gc.service.base
 * @ClassName: PlanningBaseViewModel
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2019/11/1 16:49
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/11/1 16:49
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BusinessBaseViewModel<M extends BusinessBaseModel> extends BaseViewModel<M> {
    private String activityTitle;
    private View.OnClickListener commonTileClick = this::onTitleClick;

    public View.OnClickListener getCommonTileClick() {
        return commonTileClick;
    }

    protected void onTitleClick(View view) {
        onBackPressed();
    }

    @Bindable
    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
        notifyPropertyChanged(com.xwl.mvvm.BR.activityTitle);
    }

    public void setActivityTitle(int resId) {
        setActivityTitle(getStringRes(resId));
    }

    public BusinessBaseViewModel(@NonNull Application application) {
        super(application);
    }


    public abstract class BusinessNetCallBack<D> implements NetCallBack<D> {
        private String message;
        private boolean needLoad;

        public BusinessNetCallBack(int resId) {
            this(getApplication().getString(resId));
        }

        public BusinessNetCallBack(String message) {
            this(message, true);
        }

        public BusinessNetCallBack(int resId, boolean needLoad) {
            this(getApplication().getString(resId), needLoad);
        }

        public BusinessNetCallBack(String message, boolean needLoad) {
            this.message = message;
            this.needLoad = needLoad;
        }

        @Override
        public void onStart() {
            // 判断网络
            if (!NetUtil.isNetworkConnected(getApplication())) {
                String message = getApplication().getResources().getString(R.string.networkUnavailable);
                showToast(message);
                onRequestNotWork();
                throw new IllegalArgumentException(message);
            }
            // 判断是否需要登录
            if (needLoad && !UserUtil.getLogIn()) {
                String message = getApplication().getResources().getString(R.string.userNotLoggedIn);
                throw new NeedLoadError(message);
            }
            // 加载中弹窗
            if (!TextUtils.isEmpty(message)) {
                BusinessBaseContact baseContract = (BusinessBaseContact) getBaseContract();
                if (baseContract != null) {
                    baseContract.showLoading(message);
                }
            }
        }

        @Override
        public void onRequestSuccess(BaseResponse<D> baseResponse) {
            BaseContract baseContract = getBaseContract();
            if (baseContract != null) {
                baseContract.dismissLoading();
            }
        }

        @Override
        public void onRequestFailure(Throwable e) {
            BaseContract baseContract = getBaseContract();
            if (baseContract instanceof BusinessBaseContact) {
                if (e instanceof NeedLoadError) {
                    // 未登录错误,弹出登录弹窗
                    ((BusinessBaseContact) baseContract).startLoadingActivity();
                } else {
                    // 非静默加载，弹出错误弹窗
                    if (!TextUtils.isEmpty(message)) {
                        baseContract.dismissLoading();
                        baseContract.showDialog(getApplication().getResources().getString(R.string.point),
                                e == null ? getApplication().getResources().getString(R.string.unknownMistake) : e.getMessage());
                    }
                }
            }
        }

        @Override
        public void onRequestNotWork() {
            // 非静默加载，弹出错误弹窗
            if (!TextUtils.isEmpty(message)) {
                BaseContract baseContract = getBaseContract();
                if (baseContract != null) {
                    String messageTips = getApplication().getResources().getString(R.string.networkUnavailable);
                    baseContract.dismissLoading();
                    baseContract.showDialog(getApplication().getResources().getString(R.string.point), messageTips);
                }
            }
        }
    }
}
