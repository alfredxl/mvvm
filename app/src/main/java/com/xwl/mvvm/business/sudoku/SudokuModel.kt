package com.xwl.mvvm.business.sudoku

import com.xwl.mvvm.base.mvvm.BusinessBaseModel

/**
 * @ProjectName: mvvm
 * @Package: com.xwl.mvvm.business.sudoku
 * @ClassName: SudokuModel
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2020/11/26 9:30
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/11/26 9:30
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class SudokuModel : BusinessBaseModel() {

    fun solveSudoku(board: Array<CharArray>): Boolean {
        // 第i行，第‘j’个字符是否已经包含(字符'1'~'9'转行成坐标0~8,如row[0][0]为true ,就标识第0行已经包含'1'这个字符了)
        val row = Array(9) { BooleanArray(9) }
        // 第j列，第'i'个字符是否已经包含
        val col = Array(9) { BooleanArray(9) }
        // 第index个小宫格（9个宫格），第'i'个字符是否已经包含
        val box = Array(9) { BooleanArray(9) }
        for (i in 0..8) {
            for (j in 0..8) {
                val item = board[i][j]
                if (item != '.') {
                    // 把字符item转换为坐标，-‘1’即可
                    val index = item - '1'
                    // 第几个宫格
                    val boxIndex = i / 3 * 3 + j / 3
                    if (row[i][index] || col[j][index] || box[boxIndex][index]) {
                        // 行、列、九宫格任何一个已经包含item字符串，返回false
                        return false
                    }
                    row[i][index] = true
                    col[j][index] = true
                    box[boxIndex][index] = true
                }
            }
        }
        return solveSudoku(board, row, col, box, 0, 0)
    }

    private fun solveSudoku(board: Array<CharArray>, row: Array<BooleanArray>, col: Array<BooleanArray>, box: Array<BooleanArray>, i: Int, j: Int): Boolean {
        return if (i == 9) {
            true
        } else {
            val item = board[i][j]
            if (item == '.') {
                // 第几个宫格
                val boxIndex = i / 3 * 3 + j / 3
                for (c in 0..8) {
                    if (row[i][c] || col[j][c] || box[boxIndex][c]) {
                        continue
                    }
                    board[i][j] = (c + '1'.toInt()).toChar()
                    row[i][c] = true
                    col[j][c] = true
                    box[boxIndex][c] = true
                    val next = if (j >= 8) solveSudoku(board, row, col, box, i + 1, 0) else solveSudoku(board, row, col, box, i, j + 1)
                    if (next) {
                        return true
                    } else {
                        row[i][c] = false
                        col[j][c] = false
                        box[boxIndex][c] = false
                    }
                }
                board[i][j] = '.'
                false
            } else {
                if (j >= 8) {
                    solveSudoku(board, row, col, box, i + 1, 0)
                } else {
                    solveSudoku(board, row, col, box, i, j + 1)
                }
            }
        }
    }
}