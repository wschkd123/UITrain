package com.wsc.weibo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

/**
 * @author shichao5
 * @date 2018/12/10
 * @describ
 */
public class CustomView extends View {

    private Paint mPaint;
    private RectF mArcRectF;
    private RectF mRoundRext;
    private RectF mOvalRext;

    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mArcRectF = new RectF(100, 700, 400, 1000);
        mRoundRext = new RectF(700, 100, 900, 300);
        mOvalRext = new RectF(100, 400, 400, 600);
    }

    public CustomView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(200, 200, 100, mPaint);
        canvas.drawRect(400, 100, 600, 300, mPaint);
        canvas.drawRoundRect(mRoundRext, 20, 20, mPaint);

        canvas.drawOval(mOvalRext, mPaint);
        canvas.drawLine(450, 400, 600, 600, mPaint);
        canvas.drawPoint(800, 500, mPaint);
        //弧形、扇形
        canvas.drawArc(mArcRectF, 0, 100, false, mPaint);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawArc(mArcRectF, 130, 100, true, mPaint);
        canvas.drawArc(mArcRectF, 240, 100, false, mPaint);
        mPaint.setTextSize(80);
        canvas.drawText("hello", 500, 900, mPaint);

    }
}
