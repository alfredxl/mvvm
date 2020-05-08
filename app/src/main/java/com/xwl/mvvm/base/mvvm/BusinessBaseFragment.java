package com.xwl.mvvm.base.mvvm;

import android.app.Activity;

import androidx.databinding.ViewDataBinding;

import com.xwl.common.base.BaseFragment;


/**
 * @ProjectName: GcService
 * @Package: com.smart.gc.service.base.mvvm
 * @ClassName: PlanningBaseFragment
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2019/11/8 16:37
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/11/8 16:37
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class BusinessBaseFragment<M extends BusinessBaseModel, V extends ViewDataBinding,
        VM extends BusinessBaseViewModel<M>> extends BaseFragment<M, V, VM> implements BusinessBaseContact {

    @Override
    public void startLoadingActivity() {
        Activity activity = getActivity();
        if (activity instanceof BusinessBaseActivity) {
            ((BusinessBaseActivity) activity).startLoadingActivity();
        }
    }
}
