package com.xwl.mvvm.business.cardlist

import android.app.Application
import android.text.TextUtils
import androidx.databinding.Bindable
import com.xwl.mvvm.base.bean.PageBean
import com.xwl.mvvm.base.mvvm.BusinessBaseViewModel
import com.xwl.mvvm.base.net.BaseResponse
import com.xwl.mvvm.base.util.DateFormatUtils
import com.xwl.mvvm.business.cardlist.bean.CardBean
import com.xwl.mvvm.business.cardlist.bean.CardListRequest
import java.util.*

/**
 * @ProjectName: mvvm
 * @Package: com.xwl.mvvm.business.cardlist
 * @ClassName: CardListViewModel
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2021/2/1 8:52
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/2/1 8:52
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class CardListViewModel(application: Application) : BusinessBaseViewModel<CardListModel>(application) {
    val cardList = PageBean<CardBean>()

    @get:Bindable
    val request = CardListRequest().apply {
        startTime = DateFormatUtils.getFormatToStringYmdsz(Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24L))
    }

    fun refresh(block: (success: Boolean) -> Unit) {
        load(true, block)
    }

    fun loadMore(block: (success: Boolean, positionStart: Int, itemCount: Int) -> Unit) {
        val start = cardList.list.size
        load(false) { block(it, start, cardList.list.size - start) }
    }

    private fun load(isRefresh: Boolean, block: (success: Boolean) -> Unit) {
        request.run {
            page = if (isRefresh) {
                1
            } else {
                if (cardList.totalPage > cardList.currPage) {
                    cardList.currPage + 1
                } else {
                    showToast("没有更多数据!")
                    block(true)
                    return@load
                }
            }
        }
        val rq = (this.request.clone() as CardListRequest).apply {
            if (TextUtils.isEmpty(startTime)) startTime = null
            if (TextUtils.isEmpty(endTime)) endTime = null
            if (TextUtils.isEmpty(personnelName)) personnelName = null
            if (TextUtils.isEmpty(personnelNo)) personnelNo = null
        }
        model.list(rq, object : BusinessNetCallBack<PageBean<CardBean?>?>("", false) {
            override fun onRequestSuccess(baseResponse: BaseResponse<PageBean<CardBean?>?>?) {
                super.onRequestSuccess(baseResponse)
                baseResponse?.data?.run {
                    cardList.totalCount = totalCount
                    cardList.totalPage = totalPage
                    cardList.currPage = currPage
                    if (isRefresh) {
                        cardList.list.clear()
                    }
                    cardList.list.addAll(list)
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