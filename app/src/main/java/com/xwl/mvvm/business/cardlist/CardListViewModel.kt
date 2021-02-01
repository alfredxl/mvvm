package com.xwl.mvvm.business.cardlist

import android.app.Application
import com.xwl.mvvm.base.bean.PageBean
import com.xwl.mvvm.base.mvvm.BusinessBaseViewModel
import com.xwl.mvvm.base.net.BaseResponse
import com.xwl.mvvm.business.cardlist.bean.CardBean
import com.xwl.mvvm.business.cardlist.bean.CardListRequest

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

    fun refresh(block: (success: Boolean) -> Unit) {
        load(true, block)
    }

    fun loadMore(block: (success: Boolean) -> Unit) {
        load(false, block)
    }

    private fun load(isRefresh: Boolean, block: (success: Boolean) -> Unit) {
        val request = CardListRequest().apply {
            if (cardList.totalPage != 0) {
                if (!isRefresh) {
                    if (cardList.totalPage > cardList.currPage) {
                        page = cardList.currPage + 1;
                    } else {
                        showToast("没有更多数据!")
                        block(true)
                        return@load
                    }
                }
            }
        }
        model.list(request, object : BusinessNetCallBack<PageBean<CardBean?>?>("", false) {
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