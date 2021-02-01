package com.xwl.mvvm.business.cardlist

import android.app.Application
import com.xwl.mvvm.base.mvvm.BusinessBaseViewModel
import com.xwl.mvvm.base.net.BaseResponse
import com.xwl.mvvm.base.util.UserUtil

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
    fun getCodeImageUrl(): String {
        return model.getCodeImageUrl()
    }

    fun login(tvUserName: String, tvPassword: String, tvCode: String) {
        model.login(tvUserName, tvPassword, tvCode, object : BusinessNetCallBack<String?>("登录中...", false) {
            override fun onRequestSuccess(baseResponse: BaseResponse<String?>?) {
                super.onRequestSuccess(baseResponse)
                baseResponse?.data?.let {
                    UserUtil.setToken(it)
                    showDialog("登录成功", it)
                }
            }
        })
    }
}