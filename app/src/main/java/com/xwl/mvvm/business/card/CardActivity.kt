package com.xwl.mvvm.business.card

import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.library.baseAdapters.BR
import com.jzxiang.pickerview.TimePickerDialog
import com.jzxiang.pickerview.data.Type
import com.jzxiang.pickerview.listener.OnDateSetListener
import com.xwl.mvvm.R
import com.xwl.mvvm.base.mvvm.BusinessBaseActivity
import com.xwl.mvvm.base.util.DateFormatUtils
import com.xwl.mvvm.databinding.CardActivityBinding
import java.util.*

/**
 * @ProjectName: mvvm
 * @Package: com.xwl.mvvm.business.card
 * @ClassName: CardActivity
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2020/12/31 8:01
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/12/31 8:01
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class CardActivity : BusinessBaseActivity<CardModel, CardActivityBinding, CardViewModel>(), OnDateSetListener,
        View.OnClickListener {
    private var mDialogAll: TimePickerDialog? = null
    override fun initView() {
        dataBinding.setVariable(BR.itemClick, this)
        dataBinding.radioGroupTime.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.rbt_system) {
                viewModel.timeStr = ""
            } else {
                showTimeDialog()
            }
        }
        dataBinding.radioGroupType.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.rbt_attendance) {
                viewModel.deviceSn = "V5YVG3EYI3SF2MAG"
            } else {
                viewModel.deviceSn = "BE2IVGSS5YBNFHAN"
            }
        }
    }

    override fun initData() {}
    override fun getViewModelId(): Int {
        return BR.cardViewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.card_activity
    }

    private fun showTimeDialog() {
        if (mDialogAll == null) {
            mDialogAll = TimePickerDialog.Builder()
                    .setCallBack(this)
                    .setCancelStringId("取消")
                    .setSureStringId("确认")
                    .setTitleStringId("选择时间")
                    .setYearText("年")
                    .setMonthText("月")
                    .setDayText("日")
                    .setHourText("小时")
                    .setMinuteText("分钟")
                    .setCyclic(false)
                    .setMinMillseconds(1)
                    .setMaxMillseconds(System.currentTimeMillis() + 1000L * 60L * 60L * 24L * 365L * 20L)
                    .setCurrentMillseconds(System.currentTimeMillis())
                    .setThemeColor(ContextCompat.getColor(this, R.color.timepicker_dialog_bg))
                    .setType(Type.ALL)
                    .setWheelItemTextNormalColor(ContextCompat.getColor(this, R.color.timetimepicker_default_text_color))
                    .setWheelItemTextSelectorColor(ContextCompat.getColor(this, R.color.timepicker_toolbar_bg))
                    .setWheelItemTextSize(12)
                    .build()
        }
        mDialogAll?.show(supportFragmentManager, "mDialogAll")
    }

    override fun onDateSet(timePickerView: TimePickerDialog?, millseconds: Long) {
        viewModel.timeStr = DateFormatUtils.getFormatToStringYmdsz(Date(millseconds))
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_button -> viewModel.play()
        }
    }
}