package com.wsc.weibo;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.wsc.R;

public class ShakeView extends View {

    private Paint mPaint;
    private float centerX;
    private float centerY;
    private float mCurrentAngle;
    private ObjectAnimator mObjectAnimator = ObjectAnimator.ofFloat(this, "angle", -30, 30);
    private Bitmap mBitmap;

    public ShakeView(Context context) {
        super(context);
        init();
    }

    private void init() {

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
        centerX = mBitmap.getWidth() / 2;
        centerY = mBitmap.getHeight() / 2;

        //当前进度
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);

        mObjectAnimator.setDuration(600);
        mObjectAnimator.setInterpolator(new LinearInterpolator());
        mObjectAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        mObjectAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        mObjectAnimator.start();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.rotate(mCurrentAngle, centerX, centerY);
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        canvas.restore();
    }

    @SuppressWarnings("unused")
    public void setAngle(float angle) {
        this.mCurrentAngle = angle;
        invalidate();
    }
}
