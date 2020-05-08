package com.xwl.mvvm.business.javatest;


import android.content.Intent;
import android.view.View;

import com.xwl.mvvm.BR;
import com.xwl.mvvm.R;
import com.xwl.mvvm.base.mvvm.BusinessBaseActivity;
import com.xwl.mvvm.business.kotlintest.KotlinTestActivity;
import com.xwl.mvvm.databinding.JavaTestActivityBinding;

public class JavaTestActivity extends BusinessBaseActivity<JavaTestModel, JavaTestActivityBinding, JavaTestViewModel>
        implements View.OnClickListener {


    @Override
    protected void initView() {
        dataBinding.setVariable(BR.itemClick, this);
    }

    @Override
    protected void initData() {
        viewModel.setActivityTitle(R.string.javaTestPage);
    }

    @Override
    protected int getViewModelId() {
        return BR.javaTestViewModel;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.java_test_activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_rxBus:
                viewModel.sendRxBus();
                break;
            case R.id.bt_net:
                viewModel.loadNetData();
                break;
            case R.id.bt_kotlin:
                startActivity(new Intent(this, KotlinTestActivity.class));
                break;
        }
    }
}
