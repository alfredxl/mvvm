package com.xwl.mvvm.business.kotlintest

import android.view.View
import androidx.databinding.library.baseAdapters.BR
import com.xwl.mvvm.R
import com.xwl.mvvm.base.mvvm.BusinessBaseActivity
import com.xwl.mvvm.databinding.KotlinTestActivityBinding

/**
 * @ProjectName: mvvm
 * @Package: com.xwl.mvvm.business.kotlintest
 * @ClassName: KotlinTestActivity
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2020/5/8 9:33
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/8 9:33
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class KotlinTestActivity : BusinessBaseActivity<KotlinTestModel, KotlinTestActivityBinding, KotlinTestViewModel>(),
        View.OnClickListener {
    override fun initView() {
        dataBinding.setVariable(BR.itemClick, this)
    }

    override fun initData() {
        viewModel.setActivityTitle(R.string.kotlinTestPage)
    }

    override fun getViewModelId(): Int {
        return BR.kotlinTestViewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.kotlin_test_activity
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.bt_coroutine -> viewModel.coroutine()
        }
    }
}