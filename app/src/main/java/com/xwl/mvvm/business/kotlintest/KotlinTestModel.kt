package com.xwl.mvvm.business.kotlintest

import com.xwl.mvvm.base.mvvm.BusinessBaseModel
import com.xwl.mvvm.base.net.LocalRetrofit
import com.xwl.mvvm.base.net.NetCallBack
import com.xwl.mvvm.business.kotlintest.bean.LoginBean
import java.util.*

/**
 * @ProjectName: mvvm
 * @Package: com.xwl.mvvm.business.kotlintest
 * @ClassName: KotlinTestModel
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2020/5/8 9:34
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/8 9:34
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class KotlinTestModel : BusinessBaseModel() {
    private var codeUUID = ""

    fun getCodeImageUrl(): String {
        codeUUID = UUID.randomUUID().toString()
        return "https://face.hk-ai.cn:8094/captcha.jpg?uuid=$codeUUID"
    }

    fun <D> login(tvUserName: String, tvPassword: String, tvCode: String, callBack: NetCallBack<D?>?) {
        val loginBean = LoginBean().apply {
            this.username = tvUserName
            this.password = tvPassword
            this.captcha = tvCode
            this.t = System.currentTimeMillis()
            this.uuid = codeUUID
        }
        joinNet(
                LocalRetrofit.getRetrofit().create(KotlinProtocol::class.java)
                        .sysLogin(loginBean), callBack
        )
    }
}