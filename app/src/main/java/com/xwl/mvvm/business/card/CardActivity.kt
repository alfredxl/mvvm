package com.xwl.mvvm.business.card

import androidx.databinding.library.baseAdapters.BR
import com.xwl.mvvm.R
import com.xwl.mvvm.base.mvvm.BusinessBaseActivity
import com.xwl.mvvm.databinding.CardActivityBinding

/**
 * @ProjectName: mvvm
 * @Package: com.xwl.mvvm.business.card
 * @ClassName: CardActivity
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2020/12/31 8:01
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/12/31 8:01
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class CardActivity : BusinessBaseActivity<CardModel, CardActivityBinding, CardViewModel>() {
    override fun initView() {}
    override fun initData() {}
    override fun getViewModelId(): Int {
        return BR.cardViewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.card_activity
    }
}