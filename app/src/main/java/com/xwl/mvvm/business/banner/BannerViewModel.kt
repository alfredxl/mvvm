package com.xwl.mvvm.business.banner

import android.app.Application
import com.xwl.mvvm.base.mvvm.BusinessBaseViewModel

/**
 * @ProjectName: mvvm
 * @Package: com.xwl.mvvm.business.banner
 * @ClassName: BannerViewModel
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2020/12/11 15:36
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/12/11 15:36
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class BannerViewModel(application: Application) : BusinessBaseViewModel<BannerModel>(application) {
    fun getData(): List<String> {
        return model.getData()
    }
}