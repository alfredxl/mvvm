package com.xwl.mvvm.business.kotlintest

import android.app.Application
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.xwl.mvvm.base.mvvm.BusinessBaseViewModel
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
    @get:Bindable
    var times = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.times)
        }

    var job: Job? = null

    fun coroutine() {
        job?.cancel()
        job = GlobalScope.launch(Dispatchers.Main) {
            val a = withContext(Dispatchers.IO) {
                // TODO issues 在子线程做些耗时操作
                Thread.sleep(2000)
                1
            }
            // 主线程更新
            times += a
        }
    }

    override fun onCleared() {
        super.onCleared()
        // 取消协程
        job?.cancel()
    }
}