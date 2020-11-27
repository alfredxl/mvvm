package com.xwl.mvvm.business.sudoku.bind

import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.xwl.common.util.RxBus
import com.xwl.mvvm.R
import kotlinx.android.synthetic.main.sudoku_box_line.view.*

/**
 * @ProjectName: mvvm
 * @Package: com.xwl.mvvm.business.sudoku.bind
 * @ClassName: SudokuBind
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2020/11/26 9:50
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/11/26 9:50
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
object SudokuBind {

    @JvmStatic
    @BindingAdapter("sudokuBindBack")
    fun sudokuBindBack(recyclerView: RecyclerView, size: Int) {
        recyclerView.adapter?.notifyDataSetChanged() ?: let {
            val layoutManager = GridLayoutManager(recyclerView.context, 3)
            val itemAnimator = DefaultItemAnimator()
            recyclerView.layoutManager = layoutManager
            recyclerView.itemAnimator = itemAnimator
            val adapter = object : RecyclerView.Adapter<ViewHolder>() {
                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                    return object : ViewHolder(LayoutInflater.from(recyclerView.context).inflate(R.layout.sudoku_item_back, parent, false)) {}
                }

                override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                    holder.itemView.setBackgroundColor()
                }

                override fun getItemCount(): Int {
                    return size
                }

                operator fun get(x: Float): Color? {
                    var x = x
                    var r = 0.0f
                    var g = 0.0f
                    var b = 1.0f
                    if (x >= 0.0f && x < 0.2f) {
                        x = x / 0.2f
                        r = 0.0f
                        g = x
                        b = 1.0f
                    } else if (x >= 0.2f && x < 0.4f) {
                        x = (x - 0.2f) / 0.2f
                        r = 0.0f
                        g = 1.0f
                        b = 1.0f - x
                    } else if (x >= 0.4f && x < 0.6f) {
                        x = (x - 0.4f) / 0.2f
                        r = x
                        g = 1.0f
                        b = 0.0f
                    } else if (x >= 0.6f && x < 0.8f) {
                        x = (x - 0.6f) / 0.2f
                        r = 1.0f
                        g = 1.0f - x
                        b = 0.0f
                    } else if (x >= 0.8f && x <= 1.0f) {
                        x = (x - 0.8f) / 0.2f
                        r = 1.0f
                        g = 0.0f
                        b = x
                    }
                    return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        Color.valueOf(r, g, b)
                    } else {
                        Color.rgb(r.toInt(), g.toInt(), b.toInt())
                    }
                }

            }
            recyclerView.adapter = adapter
        }
    }

    @JvmStatic
    @BindingAdapter("sudokuBindBoard")
    fun sudokuBindBoard(recyclerView: RecyclerView, board: Array<CharArray>) {
        recyclerView.adapter?.notifyDataSetChanged() ?: let {
            val click = View.OnClickListener {
                (recyclerView.tag as? Int)?.let { old ->
                    recyclerView.adapter?.notifyItemChanged(old)
                }
                recyclerView.tag = it.tag
                recyclerView.adapter?.notifyItemChanged(it.tag as Int)
            }
            val layoutManager = GridLayoutManager(recyclerView.context, 9)
            val itemAnimator = DefaultItemAnimator()
            recyclerView.layoutManager = layoutManager
            recyclerView.itemAnimator = itemAnimator
            val adapter = object : RecyclerView.Adapter<ViewHolder>() {
                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                    return object : ViewHolder(LayoutInflater.from(recyclerView.context).inflate(R.layout.sudoku_box_line, parent, false)) {}
                }

                override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                    val i = position / 9
                    val j = position % 9
                    val charItem = board[i][j]
                    holder.itemView.tv_content.text = if ('.' == charItem) "" else charItem.toString()
                    holder.itemView.tv_content.tag = position
                    holder.itemView.tv_content.setOnClickListener(click)
                    (recyclerView.tag as? Int)?.let {
                        holder.itemView.tv_content.isActivated = it == position
                    }
                }

                override fun getItemCount(): Int {
                    return board.size * 9
                }

            }
            recyclerView.adapter = adapter
            val dp1 = dipToPx(recyclerView.context, 1f)
            recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                        outRect: Rect,
                        view: View,
                        parent: RecyclerView,
                        state: RecyclerView.State
                ) {
                    val position = (view.layoutParams as RecyclerView.LayoutParams).viewLayoutPosition
                    val i = position / 9
                    val j = position % 9
                    val bottom = if (i % 3 == 2) dp1 else 0
                    val right = if (j % 3 == 2) dp1 else 0
                    outRect.set(dp1, dp1, dp1, dp1)
                    android.util.Log.i("ccccc", "$position:($i,$j):($dp1,$dp1,$right,$bottom)")
                }
            })
        }
    }

    private fun dipToPx(context: Context, dip: Float): Int {
        val density = context.resources.displayMetrics.density
        return (dip * density + 0.5f * if (dip >= 0) 1 else -1).toInt()
    }

    @JvmStatic
    @BindingAdapter("sudokuBindBoardInput")
    fun sudokuBindBoardInput(recyclerView: RecyclerView, input: Array<Char>) {
        recyclerView.adapter?.notifyDataSetChanged() ?: let {
            val click = View.OnClickListener {
                RxBus.getInstance().post("boxInput:${it.tag}")
            }
            val layoutManager = GridLayoutManager(recyclerView.context, 9)
            val itemAnimator = DefaultItemAnimator()
            recyclerView.layoutManager = layoutManager
            recyclerView.itemAnimator = itemAnimator
            val adapter = object : RecyclerView.Adapter<ViewHolder>() {
                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                    return object : ViewHolder(LayoutInflater.from(recyclerView.context).inflate(R.layout.sudoku_box_input, parent, false)) {}
                }

                override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                    holder.itemView.tv_content.text = input[position].toString()
                    holder.itemView.tv_content.tag = position
                    holder.itemView.tv_content.setOnClickListener(click)
                    (recyclerView.tag as? Int)?.let {
                        holder.itemView.tv_content.isActivated = it == position
                    }
                }

                override fun getItemCount(): Int {
                    return input.size
                }

            }
            recyclerView.adapter = adapter
            val dp1 = dipToPx(recyclerView.context, 1f)
            recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                        outRect: Rect,
                        view: View,
                        parent: RecyclerView,
                        state: RecyclerView.State
                ) {
                    outRect.set(dp1, dp1, dp1, dp1)
                }
            })
        }
    }
}