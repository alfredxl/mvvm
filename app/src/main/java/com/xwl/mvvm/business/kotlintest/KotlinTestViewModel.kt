package com.xwl.mvvm.business.kotlintest

import android.app.Application
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.xwl.mvvm.base.mvvm.BusinessBaseViewModel
import com.xwl.mvvm.base.net.BaseResponse
import com.xwl.mvvm.base.util.UserUtil
import kotlinx.coroutines.*

/**
 * @ProjectName: mvvm
 * @Package: com.xwl.mvvm.business.kotlintest
 * @ClassName: KotlinTestViewModel
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2020/5/8 9:34
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/8 9:34
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class KotlinTestViewModel(application: Application) : BusinessBaseViewModel<KotlinTestModel>(application) {

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