package com.xwl.mvvm.business.cardlist

import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.constant.SpinnerStyle
import com.xwl.mvvm.R
import com.xwl.mvvm.base.mvvm.BusinessBaseActivity
import com.xwl.mvvm.business.cardlist.weigth.CardListAdapter
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
class CardListActivity : BusinessBaseActivity<CardListModel, CardListActivityBinding, CardListViewModel>() {
    override fun initView() {
        viewModel.activityTitle = getString(R.string.cardList)
        dataBinding.refreshLayout.run {
            setRefreshHeader(ClassicsHeader(this@CardListActivity))
            setRefreshFooter(ClassicsFooter(this@CardListActivity).setSpinnerStyle(SpinnerStyle.FixedBehind))
            setOnRefreshListener {
                viewModel.refresh {
                    finishRefresh(it)
                    if (it) dataBinding.recyclerView.adapter?.notifyDataSetChanged()
                }
            }
            setOnLoadMoreListener {
                viewModel.loadMore { result, start, itemCount ->
                    finishLoadMore(result)
                    if (result) dataBinding.recyclerView.adapter?.notifyItemRangeChanged(start, itemCount)
                }
            }
        }
        dataBinding.recyclerView.run {
            layoutManager = LinearLayoutManager(this@CardListActivity)
            itemAnimator = DefaultItemAnimator()
            adapter = CardListAdapter(viewModel.cardList.list)
        }
    }

    override fun initData() {
        dataBinding.refreshLayout.autoRefresh()
        viewModel.refresh {
            dataBinding.refreshLayout.finishRefresh(it)
            if (it) dataBinding.recyclerView.adapter?.notifyDataSetChanged()
        }
    }

    override fun getViewModelId(): Int {
        return BR.cardListViewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.card_list_activity
    }
}