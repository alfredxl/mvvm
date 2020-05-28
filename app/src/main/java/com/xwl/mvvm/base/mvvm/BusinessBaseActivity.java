package com.xwl.mvvm.base.mvvm;


import android.util.DisplayMetrics;

import androidx.databinding.ViewDataBinding;

import com.xwl.common.base.BaseActivity;
import com.xwl.mvvm.R;

/**
 * @ProjectName: GcService
 * @Package: com.smart.gc.service.base
 * @ClassName: PlanningBaseActivity
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2019/11/1 16:48
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/11/1 16:48
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class BusinessBaseActivity<M extends BusinessBaseModel, V extends ViewDataBinding,
        VM extends BusinessBaseViewModel<M>> extends BaseActivity<M, V, VM> implements BusinessBaseContact {
    @Override
    public void startLoadingActivity() {
        // TODO 跳转到登录界面
    }

    @Override
    protected int getStatusBarColorResId() {
        return R.color.colorAccent;
    }

    @Override
    protected int getTargetWightDp() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return (int) (displayMetrics.density * displayMetrics.widthPixels);
    }
}
