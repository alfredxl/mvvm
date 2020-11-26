package com.xwl.mvvm.business.sudoku

import androidx.databinding.library.baseAdapters.BR
import com.xwl.mvvm.R
import com.xwl.mvvm.base.mvvm.BusinessBaseActivity
import com.xwl.mvvm.databinding.SudokuActivityBinding
import kotlinx.android.synthetic.main.sudoku_activity.*

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
        SudokuContact {
    override fun initView() {}
    override fun initData() {}
    override fun getViewModelId(): Int {
        return BR.sudokuViewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.sudoku_activity
    }

    override fun getBoxSelect(): Int? {
        return recyclerViewBox.tag as? Int
    }

    override fun setBoxSelect(position: Int) {
        recyclerViewBox?.adapter?.notifyItemChanged(position)
    }
}