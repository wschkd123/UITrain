package com.wsc.weibo;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class TranslateCustomPathView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path = new Path();
    private RectF mRectF;
    private ObjectAnimator mObjectAnimator = ObjectAnimator.ofFloat(this, "height", 600, 200);
    private float mWidth = 600;
    private float mHeight = 600;
    private float mArcHeight = 300;

    public TranslateCustomPathView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mObjectAnimator.setDuration(3000);
        mObjectAnimator.setInterpolator(new LinearInterpolator());
        mObjectAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        mObjectAnimator.setRepeatMode(ObjectAnimator.RESTART);
        mObjectAnimator.start();

        //画心
        paint.setStrokeWidth(8);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mRectF = new RectF(0, mHeight - mArcHeight / 2, mWidth, mHeight + mArcHeight / 2);
        path.lineTo(mWidth, 0);
        path.lineTo(mWidth, mHeight);
        path.arcTo(mRectF, 0, 180, false);
        path.close();
        canvas.drawPath(path, paint);
    }

    @SuppressWarnings("unused")
    public void setHeight(float height) {
        this.mHeight = height;
        invalidate();
    }
}
