package com.wsc.weibo;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class UploadCircleView extends View {

    private RectF bgRect;
    private Paint mBgPaint;
    private Paint mProgressPaint;
    /**
     * 圆心坐标
     */
    private float centerX;
    private float centerY;
    private float mCurrentAngle;
    private float progressWidth = 60;
    private ObjectAnimator mObjectAnimator = ObjectAnimator.ofFloat(this, "angle", 360f, 0f);

    public UploadCircleView(Context context) {
        super(context);
        init();
    }

    private void init() {
        //背景
        mBgPaint = new Paint();
        mBgPaint.setAntiAlias(true);
        mBgPaint.setStyle(Paint.Style.FILL);
        mBgPaint.setColor(Color.parseColor("#33FF374A"));

        //当前进度
        mProgressPaint = new Paint();
        mProgressPaint.setAntiAlias(true);
        mProgressPaint.setStyle(Paint.Style.STROKE);
        mProgressPaint.setStrokeWidth(progressWidth);
        mProgressPaint.setColor(Color.parseColor("#FF9D8E"));
        mProgressPaint.setStrokeCap(Paint.Cap.ROUND);

        mObjectAnimator.setDuration(3000);
        mObjectAnimator.setInterpolator(new LinearInterpolator());
        mObjectAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        mObjectAnimator.setRepeatMode(ObjectAnimator.RESTART);

        post(new Runnable() {
            @Override
            public void run() {
                centerX = getMeasuredHeight() / 2;
                centerY = getMeasuredHeight() / 2;
                bgRect = new RectF(progressWidth / 2, progressWidth / 2, getMeasuredWidth() - progressWidth / 2, getMeasuredHeight() - progressWidth / 2);
                SweepGradient mSweepGradient = new SweepGradient(centerX, centerY, new int[]{Color.parseColor("#BA2509"), Color.parseColor("#BA2509"),
                        Color.parseColor("#BA2509"), Color.parseColor("#BA2509"),
                        Color.parseColor("#FF9D8E"), Color.parseColor("#FF9D8E"),
                        Color.parseColor("#FF9D8E"), Color.parseColor("#BA2509")}, null);

                mProgressPaint.setShader(mSweepGradient);

                mObjectAnimator.start();
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(500, 500);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //算出这里需要的区域
        canvas.drawCircle(centerX, centerY, getMeasuredWidth() / 2, mBgPaint);
        //当前进度
        canvas.drawArc(bgRect, 270, mCurrentAngle, false, mProgressPaint);
    }

    @SuppressWarnings("unused")
    public void setAngle(float angle) {
        this.mCurrentAngle = angle;
        invalidate();
    }
}
