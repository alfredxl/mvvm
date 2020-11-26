package com.xwl.mvvm.business.sudoku

import com.xwl.mvvm.base.mvvm.BusinessBaseContact

/**
 * @ProjectName: mvvm
 * @Package: com.xwl.mvvm.business.sudoku
 * @ClassName: SudokuContact
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2020/11/26 17:34
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/11/26 17:34
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
interface SudokuContact : BusinessBaseContact {
    fun getBoxSelect():Int?
    fun setBoxSelect(position: Int)
}