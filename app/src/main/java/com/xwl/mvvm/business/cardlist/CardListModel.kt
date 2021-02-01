package com.xwl.mvvm.business.cardlist

import com.xwl.mvvm.base.mvvm.BusinessBaseModel
import com.xwl.mvvm.base.net.LocalRetrofit
import com.xwl.mvvm.base.net.NetCallBack
import com.xwl.mvvm.business.cardlist.bean.CardListRequest

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
    fun <D> list(cardListRequest: CardListRequest, callBack: NetCallBack<D?>?) {
        joinNet(
                LocalRetrofit.getRetrofit().create(CardListProtocol::class.java)
                        .list(cardListRequest), callBack
        )
    }
}