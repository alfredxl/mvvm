package com.xwl.common.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @ProjectName: GcService
 * @Package: com.smart.gc.common.base
 * @ClassName: BaseActivity
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2019/11/1 10:45
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/11/1 10:45
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class BaseFragment<M extends BaseModel, V extends ViewDataBinding, VM extends BaseViewModel<M>>
        extends Fragment implements BaseContract {
    protected V dataBinding;
    protected VM viewModel;
    private boolean isInit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = initMVVM(inflater, container);
        if (viewModel != null) {
            getLifecycle().addObserver(viewModel);
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isInit) {
            isInit = true;
            initView();
            initData();
        }
    }

    protected abstract void initView();

    protected abstract void initData();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (viewModel != null) {
            getLifecycle().removeObserver(viewModel);
        }
        dataBinding.unbind();
    }

    protected View initMVVM(LayoutInflater inflater, ViewGroup container) {
        Type superType = getClass().getGenericSuperclass();
        if (superType instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) superType;
            Type[] types = pt.getActualTypeArguments();
            if (types.length > 2) {
                Class mClass = (Class) types[2];
                try {
                    viewModel = (VM) ViewModelProviders.of(this).get(mClass);
                    viewModel.setBaseContract(this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        dataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        dataBinding.setVariable(getViewModelId(), viewModel);
        return dataBinding.getRoot();
    }

    protected abstract int getViewModelId();

    protected abstract int getLayoutId();

    @Override
    public void showLoading(String message) {
        Activity activity = getActivity();
        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).showLoading(message);
        }
    }

    @Override
    public void showDialog(int tileRes, int messageRes) {
        Activity activity = getActivity();
        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).showDialog(tileRes, messageRes);
        }
    }

    @Override
    public void showDialog(String tile, String message) {
        Activity activity = getActivity();
        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).showDialog(tile, message);
        }
    }

    @Override
    public void dismissLoading() {
        Activity activity = getActivity();
        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).dismissLoading();
        }
    }

    @Override
    public void dismissDialog() {
        Activity activity = getActivity();
        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).dismissDialog();
        }
    }

    @Override
    public void finish() {
        Activity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    @Override
    public void onBackPressed() {
        Activity activity = getActivity();
        if (activity != null) {
            activity.onBackPressed();
        }
    }

    @Override
    public void showToast(String message) {
        Activity activity = getActivity();
        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).showToast(message);
        }
    }
}
