package com.wsc.paint.flash;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.wsc.paint.svg.MultiStar;

/**
 * Created by zyr
 * DATE: 16-3-7
 * Time: 上午11:52
 * Email: yanru.zhang@renren-inc.com
 */
public class HeartView extends View {

    private static final String TAG = HeartView.class.getSimpleName();
    private Paint mPaint;
    private Path mPath;
    private Path mPath2;
    private Paint mPaint2;
 
    private ValueAnimator valueAnimator;
    private PathMeasure mPathMeasure;
    private float[] mCurrentPosition = new float[2];

    private int mWidth;
    private int mHeight;
    private float mLineWidth = 50;


    public HeartView(Context context) {
        this(context, null);
    }
 
    public HeartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
 
    public HeartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
 
    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(40);
        mPaint.setStyle(Paint.Style.STROKE);
 
        mPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint2.setColor(Color.BLUE);
        mPaint2.setTextSize(40);
        mPaint2.setStyle(Paint.Style.FILL);
 
        mPath = new Path();
        mPath.addRect(mLineWidth, mLineWidth, mWidth - mLineWidth, mHeight - mLineWidth, Path.Direction.CW);
//        mPath.addCircle(0, 200, 200, Path.Direction.CW);
 
        mPath2 = new Path();
        mPathMeasure = new PathMeasure(mPath,false);
        mPathMeasure.getPosTan(0, mCurrentPosition, null);
//        mPath2.moveTo(mCurrentPosition[0], mCurrentPosition[1]);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);//关闭硬件加速
 
        valueAnimator = ValueAnimator.ofFloat(0,mPathMeasure.getLength());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float)animation.getAnimatedValue();
                mPathMeasure.getPosTan(value, mCurrentPosition, null);
//                mPath2.lineTo(mCurrentPosition[0],mCurrentPosition[1]);
                postInvalidate();
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
 
            }
 
            @Override
            public void onAnimationEnd(Animator animation) {
 
            }
 
            @Override
            public void onAnimationCancel(Animator animation) {
 
            }
 
            @Override
            public void onAnimationRepeat(Animator animation) {
//                mPath2 = new Path();
//                mPathMeasure = new PathMeasure(mPath,false);
//                mPathMeasure.getPosTan(0, mCurrentPosition, null);
//                mPath2.moveTo(mCurrentPosition[0], mCurrentPosition[1]);
//                if(mPaint2.getColor() ==  Color.BLUE){
//                    mPaint2.setColor(Color.YELLOW);
//                }else{
//                    mPaint2.setColor(Color.BLUE);
//                }
            }
        });
        valueAnimator.setDuration(10_000);
        valueAnimator.setRepeatCount(-1);
//        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.start();
 
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        Log.i(TAG, "w:" + w + ";oldW:" + oldw + "h:" + h + ";oldh:" + oldh);
        if (w != oldw || h != oldh) {
            mWidth = w;
            mHeight = h;

            init();
        }
    }
 
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
//        canvas.translate(50, 50);
        canvas.drawPath(mPath, mPaint);
//        canvas.drawCircle(mCurrentPosition[0], mCurrentPosition[1], 20, mPaint);

        canvas.save();
        canvas.translate(mCurrentPosition[0], mCurrentPosition[1]);
        MultiStar.draw(canvas, 154 * 2, 254 * 2);
        canvas.restore();


//        canvas.drawPath(mPath2, mPaint2);
        canvas.restore();
 
    }
}