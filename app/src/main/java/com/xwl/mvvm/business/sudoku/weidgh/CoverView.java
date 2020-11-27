package com.xwl.mvvm.business.sudoku.weidgh;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.xwl.mvvm.R;

/**
 * @ProjectName: mvvm
 * @Package: com.xwl.mvvm.business.sudoku.weidgh
 * @ClassName: CoverView
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2020/11/27 8:48
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/11/27 8:48
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CoverView extends View {
    private Paint middleCirclePaint;
    private int dp1;

    public CoverView(Context context) {
        super(context);
        init(context);
    }

    public CoverView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CoverView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        dp1 = (int) (1 * density + 0.5f);
        middleCirclePaint = new Paint();
        middleCirclePaint.setAntiAlias(true);//用于防止边缘的锯齿
        middleCirclePaint.setColor(ContextCompat.getColor(context, R.color.colorAccent));//设置颜色
        middleCirclePaint.setStyle(Paint.Style.STROKE);//设置样式为空心矩形
        middleCirclePaint.setStrokeWidth(dp1 * 3);//设置空心矩形边框的宽度
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float width = getWidth();
        float height = getHeight();
        middleCirclePaint.setStrokeWidth(dp1 * 6);
        canvas.drawRect(0f, 0f, width, height, middleCirclePaint);
        middleCirclePaint.setStrokeWidth(dp1 * 3);
        canvas.drawLine(0f, height / 3f, width, height / 3f, middleCirclePaint);
        canvas.drawLine(0f, height / 3f * 2f, width, height / 3f * 2f, middleCirclePaint);
        canvas.drawLine(width / 3f, 0, width / 3f, height, middleCirclePaint);
        canvas.drawLine(width / 3f * 2f, 0, width / 3f * 2f, height, middleCirclePaint);
    }
}
