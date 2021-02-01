package com.xwl.mvvm.business.cardlist

import androidx.databinding.library.baseAdapters.BR
import com.xwl.mvvm.R
import com.xwl.mvvm.base.mvvm.BusinessBaseActivity
import com.xwl.mvvm.business.cardlist.weight.LoginDialog
import com.xwl.mvvm.databinding.CardListActivityBinding

/**
 * @ProjectName: mvvm
 * @Package: com.xwl.mvvm.business.cardlist
 * @ClassName: CardListActivity
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2021/2/1 8:52
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/2/1 8:52
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class CardListActivity : BusinessBaseActivity<CardListModel, CardListActivityBinding, CardListViewModel>(),
        LoginDialog.LoginDialogListener {
    override fun initView() {
        viewModel.activityTitle = getString(R.string.list)
        LoginDialog(this, this).show()
    }

    override fun initData() {

    }

    override fun getViewModelId(): Int {
        return BR.cardListViewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.card_list_activity
    }

    override fun getCodeUrl(): String {
        return viewModel.getCodeImageUrl()
    }

    override fun login(tvUserName: String, tvPassword: String, tvCode: String) {
        viewModel.login(tvUserName, tvPassword, tvCode)
    }
}