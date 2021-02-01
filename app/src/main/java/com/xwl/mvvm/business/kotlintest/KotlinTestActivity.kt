package com.xwl.mvvm.business.kotlintest

import android.content.Intent
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import com.xwl.mvvm.R
import com.xwl.mvvm.base.mvvm.BusinessBaseActivity
import com.xwl.mvvm.business.banner.BannerActivity
import com.xwl.mvvm.business.card.CardActivity
import com.xwl.mvvm.business.cardlist.CardListActivity
import com.xwl.mvvm.business.kotlintest.weight.LoginDialog
import com.xwl.mvvm.business.sudoku.SudokuActivity
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
        View.OnClickListener, LoginDialog.LoginDialogListener {
    override fun initView() {
        dataBinding.setVariable(BR.itemClick, this)
        LoginDialog(this, this).show()
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
            R.id.bt_sudoku -> startActivity(Intent(this, SudokuActivity::class.java))
            R.id.bt_banner -> startActivity(Intent(this, BannerActivity::class.java))
            R.id.bt_card -> startActivity(Intent(this, CardActivity::class.java))
            R.id.bt_cardList -> startActivity(Intent(this, CardListActivity::class.java))
            R.id.bt_MTList -> {
            }
        }
    }

    override fun getCodeUrl(): String {
        return viewModel.getCodeImageUrl()
    }

    override fun login(tvUserName: String, tvPassword: String, tvCode: String) {
        viewModel.login(tvUserName, tvPassword, tvCode)
    }
}