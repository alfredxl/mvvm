package com.xwl.mvvm.business.cardlist

import com.xwl.mvvm.base.mvvm.BusinessBaseModel
import com.xwl.mvvm.base.net.LocalRetrofit
import com.xwl.mvvm.base.net.NetCallBack
import com.xwl.mvvm.business.cardlist.bean.LoginBean
import java.util.*

/**
 * @ProjectName: mvvm
 * @Package: com.xwl.mvvm.business.cardlist
 * @ClassName: CardListModel
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2021/2/1 8:51
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/2/1 8:51
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class CardListModel : BusinessBaseModel() {
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
                LocalRetrofit.getRetrofit().create(CardListProtocol::class.java)
                        .sysLogin(loginBean), callBack
        )
    }

}