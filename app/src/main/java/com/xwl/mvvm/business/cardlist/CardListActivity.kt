package com.xwl.mvvm.business.cardlist

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.jzxiang.pickerview.TimePickerDialog
import com.jzxiang.pickerview.data.Type
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.constant.SpinnerStyle
import com.xwl.mvvm.R
import com.xwl.mvvm.base.mvvm.BusinessBaseActivity
import com.xwl.mvvm.base.util.DateFormatUtils
import com.xwl.mvvm.business.cardlist.weigth.CardListAdapter
import com.xwl.mvvm.databinding.CardListActivityBinding
import java.util.*

/**
 * @ProjectName: mvvm
 * @Package: com.xwl.mvvm.business.cardlist
 * @ClassName: CardListActivity
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2021/2/1 8:52
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/2/1 8:52
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class CardListActivity : BusinessBaseActivity<CardListModel, CardListActivityBinding, CardListViewModel>(),
        View.OnClickListener {
    override fun initView() {
        dataBinding.setVariable(BR.itemClick, this)
        viewModel.activityTitle = getString(R.string.cardList)
        dataBinding.refreshLayout.run {
            setRefreshHeader(ClassicsHeader(this@CardListActivity))
            setRefreshFooter(ClassicsFooter(this@CardListActivity).setSpinnerStyle(SpinnerStyle.FixedBehind))
            setOnRefreshListener {
                viewModel.refresh {
                    finishRefresh(it)
                    if (it) dataBinding.recyclerView.adapter?.notifyDataSetChanged()
                }
            }
            setOnLoadMoreListener {
                viewModel.loadMore { result, start, itemCount ->
                    finishLoadMore(result)
                    if (result) dataBinding.recyclerView.adapter?.notifyItemRangeChanged(start, itemCount)
                }
            }
        }
        dataBinding.recyclerView.run {
            layoutManager = LinearLayoutManager(this@CardListActivity)
            itemAnimator = DefaultItemAnimator()
            adapter = CardListAdapter(viewModel.cardList.list)
        }
        dataBinding.titleGroup.titleRight.setImageResource(R.mipmap.activity_down)
        dataBinding.titleGroup.titleRight.setOnClickListener(this)
        dataBinding.moreBack.setOnClickListener(this)
        dataBinding.moreBack.isClickable = false// 需要在设置监听之后
        dataBinding.titleGroup.titleRight.tag = false
    }

    override fun initData() {
        dataBinding.refreshLayout.autoRefresh()
    }

    override fun getViewModelId(): Int {
        return BR.cardListViewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.card_list_activity
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.titleRight -> clickRight(v)
            R.id.moreBack -> clickRight(dataBinding.titleGroup.titleRight)
            R.id.btSearch -> {
                clickRight(dataBinding.titleGroup.titleRight)
                dataBinding.refreshLayout.autoRefresh()
            }
            R.id.startTimeValue -> showDateSelect(true)
            R.id.endTimeValue -> showDateSelect(false)
        }
    }

    private fun clickRight(v: View) {
        if (v.tag == false) {
            val animator: ObjectAnimator = ObjectAnimator.ofFloat(dataBinding.moreBack, "alpha", 0.0f, 0.5f)
            animator.duration = 300
            animator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    dataBinding.titleGroup.titleRight.setImageResource(R.mipmap.activity_up)
                    dataBinding.moreBack.isClickable = true
                    v.tag = true
                }
            })
            animator.start()
            val y = dataBinding.lineTop.y + dataBinding.groupSearch.height
            val animatorY = ValueAnimator.ofFloat(dataBinding.lineMore.y, y.toFloat())
            animatorY.duration = 300
            animatorY.addUpdateListener { animation ->
                (animation.animatedValue as Float).run {
                    dataBinding.lineMore.setGuidelineBegin(this.toInt())
                }
            }
            animatorY.start()
        } else {
            val animator: ObjectAnimator = ObjectAnimator.ofFloat(dataBinding.moreBack, "alpha", 0.5f, 0.0f)
            animator.duration = 300
            animator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    dataBinding.titleGroup.titleRight.setImageResource(R.mipmap.activity_down)
                    dataBinding.moreBack.isClickable = false
                    v.tag = false
                }
            })
            animator.start()
            val y = dataBinding.lineTop.y
            val animatorY = ValueAnimator.ofFloat(dataBinding.lineMore.y, y.toFloat())
            animatorY.duration = 300
            animatorY.addUpdateListener { animation ->
                (animation.animatedValue as Float).run {
                    dataBinding.lineMore.setGuidelineBegin(this.toInt())
                }
            }
            animatorY.start()
        }
    }

    private fun showDateSelect(isStart: Boolean) {
        val currentTime = if (isStart) (System.currentTimeMillis() - 1000 * 60 * 60 * 24L) else System.currentTimeMillis()
        TimePickerDialog.Builder()
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
                .setMaxMillseconds(currentTime + 1000L * 60L * 60L * 24L * 365L * 20L)
                .setCurrentMillseconds(currentTime)
                .setThemeColor(ContextCompat.getColor(this, R.color.timepicker_dialog_bg))
                .setType(Type.ALL)
                .setWheelItemTextNormalColor(ContextCompat.getColor(this, R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(ContextCompat.getColor(this, R.color.timepicker_toolbar_bg))
                .setWheelItemTextSize(12)
                .setCallBack { _, time ->
                    if (isStart) {
                        DateFormatUtils.getFormatToStringYmdsz(Date(time)).run {
                            viewModel.request.startTime = this
                            dataBinding.startTimeValue.text = this
                        }
                    } else {
                        DateFormatUtils.getFormatToStringYmdsSSS(Date(time)).run {
                            viewModel.request.endTime = this
                            dataBinding.endTimeValue.text = this
                        }
                    }
                }
                .build().show(supportFragmentManager, "mDialogAll")
    }
}