package com.xwl.mvvm.business.sudoku

import android.view.View
import androidx.databinding.library.baseAdapters.BR
import com.xwl.mvvm.R
import com.xwl.mvvm.base.mvvm.BusinessBaseActivity
import com.xwl.mvvm.databinding.SudokuActivityBinding

/**
 * @ProjectName: mvvm
 * @Package: com.xwl.mvvm.business.sudoku
 * @ClassName: SudokuActivity
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2020/11/26 9:30
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/11/26 9:30
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class SudokuActivity : BusinessBaseActivity<SudokuModel, SudokuActivityBinding, SudokuViewModel>(),
        SudokuContact, View.OnClickListener {
    override fun initView() {
        dataBinding.setVariable(BR.itemClick, this)
    }

    override fun initData() {}
    override fun getViewModelId(): Int {
        return BR.sudokuViewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.sudoku_activity
    }

    override fun getBoxSelect(): Int? {
        return dataBinding.recyclerViewBox.tag as? Int
    }

    override fun setBoxSelect(position: Int) {
        dataBinding.recyclerViewBox.adapter?.notifyItemChanged(position)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.reSet -> {
                viewModel.resetBox()
                dataBinding.recyclerViewBox.adapter?.notifyDataSetChanged()
            }
            R.id.play -> {
                viewModel.play {
                    dataBinding.recyclerViewBox.adapter?.notifyDataSetChanged()
                }
            }
        }
    }
}