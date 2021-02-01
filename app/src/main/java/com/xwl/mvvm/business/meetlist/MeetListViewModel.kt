package com.xwl.mvvm.business.meetlist

import android.app.Application
import com.xwl.mvvm.base.bean.PageBean
import com.xwl.mvvm.base.mvvm.BusinessBaseViewModel
import com.xwl.mvvm.base.net.BaseResponse
import com.xwl.mvvm.business.meetlist.bean.MeetBean
import com.xwl.mvvm.business.meetlist.bean.MeetListRequest

/**
 * @ProjectName: mvvm
 * @Package: com.xwl.mvvm.business.meetlist
 * @ClassName: MeetListViewModel
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2021/2/1 15:28
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/2/1 15:28
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class MeetListViewModel(application: Application) : BusinessBaseViewModel<MeetListModel>(application) {
    val meetList = PageBean<MeetBean>()

    fun refresh(block: (success: Boolean) -> Unit) {
        load(true, block)
    }

    fun loadMore(block: (success: Boolean, positionStart: Int, itemCount: Int) -> Unit) {
        val start = meetList.list.size
        load(false) { block(it, start, meetList.list.size - start) }
    }

    private fun load(isRefresh: Boolean, block: (success: Boolean) -> Unit) {
        val request = MeetListRequest().apply {
            if (meetList.totalPage != 0) {
                if (!isRefresh) {
                    if (meetList.totalPage > meetList.currPage) {
                        page = meetList.currPage + 1;
                    } else {
                        showToast("没有更多数据!")
                        block(true)
                        return@load
                    }
                }
            }
        }
        model.list(request, object : BusinessNetCallBack<PageBean<MeetBean?>?>("", false) {
            override fun onRequestSuccess(baseResponse: BaseResponse<PageBean<MeetBean?>?>?) {
                super.onRequestSuccess(baseResponse)
                baseResponse?.data?.run {
                    meetList.totalCount = totalCount
                    meetList.totalPage = totalPage
                    meetList.currPage = currPage
                    if (isRefresh) {
                        meetList.list.clear()
                    }
                    meetList.list.addAll(list)
                }
                block(true)
            }

            override fun onRequestFailure(e: Throwable?) {
                super.onRequestFailure(e)
                block(false)
            }
        })
    }
}