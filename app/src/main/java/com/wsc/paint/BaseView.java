package com.wsc.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author shichao5
 * @date 2018/11/2
 * @describ
 */
public class BaseView extends View {

    private Paint mPaint;

    public BaseView(Context context) {
        super(context);
        init();
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(100,100,10, mPaint);

        mPaint.setStrokeWidth(100);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawPoint(500,500,mPaint);


    }
}
