package com.xwl.mvvm.business.card

import android.app.Application
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.xwl.mvvm.R
import com.xwl.mvvm.base.mvvm.BusinessBaseViewModel
import com.xwl.mvvm.base.util.DateFormatUtils
import java.util.*

/**
 * @ProjectName: mvvm
 * @Package: com.xwl.mvvm.business.card
 * @ClassName: CardViewModel
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2020/12/31 8:02
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/12/31 8:02
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class CardViewModel(application: Application) : BusinessBaseViewModel<CardModel>(application) {
    var deviceSn = "V5YVG3EYI3SF2MAG"

    var isLoad = false

    // 时间字符串
    @get:Bindable
    var timeStr: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.timeStr)
        }

    fun play() {
        if (!isLoad) {
            isLoad = true
            if (timeStr.isEmpty()) {
                timeStr = DateFormatUtils.getFormatToStringYmdsz(Date())
            }
            val inputStream = getApplication<Application>().assets.open("17140-20201230120425501228.jpg")
            val byteArray = model.toByteArray(inputStream)
            val result = model.clock(timeStr, deviceSn, byteArray)
            showDialog(R.string.point, if (result) R.string.success else R.string.failure)
            isLoad = false
        }
    }
}