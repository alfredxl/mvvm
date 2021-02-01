package com.xwl.mvvm.business.meetlist

import android.os.Handler
import android.os.Looper
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.constant.SpinnerStyle
import com.xwl.mvvm.R
import com.xwl.mvvm.base.mvvm.BusinessBaseActivity
import com.xwl.mvvm.business.meetlist.weigth.MeetListAdapter
import com.xwl.mvvm.databinding.MeetListActivityBinding

/**
 * @ProjectName: mvvm
 * @Package: com.xwl.mvvm.business.meetlist
 * @ClassName: MeetList
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2021/2/1 15:27
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/2/1 15:27
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class MeetListActivity : BusinessBaseActivity<MeetListModel, MeetListActivityBinding, MeetListViewModel>() {
    private val handler = Handler(Looper.getMainLooper(), Handler.Callback {
        if (it.what == 1) {
            initData()
            it.target.sendEmptyMessageDelayed(1, 1000 * 60)
        }
        true
    })

    override fun initView() {
        viewModel.activityTitle = getString(R.string.MTList)
        dataBinding.refreshLayout.run {
            setRefreshHeader(ClassicsHeader(this@MeetListActivity))
            setRefreshFooter(ClassicsFooter(this@MeetListActivity).setSpinnerStyle(SpinnerStyle.FixedBehind))
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
            autoRefresh()
        }
        dataBinding.recyclerView.run {
            layoutManager = LinearLayoutManager(this@MeetListActivity)
            itemAnimator = DefaultItemAnimator()
            adapter = MeetListAdapter(viewModel.meetList.list)
        }
    }

    override fun initData() {
        viewModel.refresh {
            dataBinding.refreshLayout.finishRefresh(it)
            if (it) dataBinding.recyclerView.adapter?.notifyDataSetChanged()
        }
        handler.sendEmptyMessageDelayed(1, 1000 * 60)
    }

    override fun getViewModelId(): Int {
        return BR.meetListViewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.meet_list_activity
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeMessages(1)
    }
}