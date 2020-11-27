package com.xwl.mvvm.business.sudoku

import android.app.Application
import androidx.databinding.Bindable
import com.xwl.mvvm.base.mvvm.BusinessBaseViewModel
import java.util.*

/**
 * @ProjectName: mvvm
 * @Package: com.xwl.mvvm.business.sudoku
 * @ClassName: SudokuViewModel
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2020/11/26 9:32
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/11/26 9:32
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class SudokuViewModel(application: Application) : BusinessBaseViewModel<SudokuModel>(application) {
    @get:Bindable
    var board = Array(9) { CharArray(9) }
    val boardInput = arrayOf('.', '1', '2', '3', '4', '5', '6', '7', '8', '9')

    init {
        resetBox()
        toObservable({
            if (it.startsWith("boxInput:")) {
                val position = it.split(":")[1].toInt()
                (getBaseContract() as? SudokuContact)?.let { contract ->
                    contract.getBoxSelect()?.let { select ->
                        val i = select / 9
                        val j = select % 9
                        board[i][j] = boardInput[position]
                        contract.setBoxSelect(select)
                    }
                }
            }
        }, String::class.java)
    }

    fun resetBox() {
        for (item in board) {
            Arrays.fill(item, '.')
        }
    }

    fun play(block: () -> Unit) {
        if (model.solveSudoku(board)) {
            block()
        } else {
            showToast("已有数据无法正确构建九宫格")
        }
    }


}