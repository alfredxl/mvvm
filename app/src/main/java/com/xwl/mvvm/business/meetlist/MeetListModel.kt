package com.xwl.mvvm.business.meetlist

import com.xwl.mvvm.base.mvvm.BusinessBaseModel
import com.xwl.mvvm.base.net.LocalRetrofit
import com.xwl.mvvm.base.net.NetCallBack
import com.xwl.mvvm.business.meetlist.bean.MeetListRequest

/**
 * @ProjectName: mvvm
 * @Package: com.xwl.mvvm.business.meetlist
 * @ClassName: MeetListModel
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2021/2/1 15:28
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/2/1 15:28
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class MeetListModel : BusinessBaseModel() {
    fun <D> list(meetListRequest: MeetListRequest, callBack: NetCallBack<D?>?) {
        joinNet(
                LocalRetrofit.getRetrofit().create(MeetListProtocol::class.java)
                        .list(meetListRequest), callBack
        )
    }
}