package com.wsc.paint.flash;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.wsc.R;
import com.wsc.utils.DisplayUtil;

public class DynamicHeartView extends View {
  
    private static final String TAG = "DynamicHeartView";  
    private static final int PATH_WIDTH = 100;
    // 起始点  
    private static final int[] START_POINT = new int[] {  
            300, 270
    };  
    // 爱心下端点  
    private static final int[] BOTTOM_POINT = new int[] {  
            300, 1000
    };  
    // 左侧控制点  
    private static final int[] LEFT_CONTROL_POINT = new int[] {  
            450, 200
    };  
    // 右侧控制点  
    private static final int[] RIGHT_CONTROL_POINT = new int[] {  
            150, 200
    };  
  
    private PathMeasure mPathMeasure;
    private Paint mPaint;
    private Paint mStarPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);


    private Path mPath;
    private float[] mCurrentPosition = new float[2];

    private int mWidth;
    private int mHeight;
    private float mLineWidth = 50;
    private int mCursorWidth = 154;
    private int mCursorHeight = 254;

    private int YELLOW = Color.rgb(254, 228, 0);
    private int RED = Color.rgb(255, 46, 46);
    private int PURPLE = Color.rgb(204, 32, 176);
    private int BLUE = Color.rgb(62, 104, 255);
    private int CYAN = Color.rgb(33, 252, 255);
    private int GREEN = Color.rgb(63, 250, 51);
    private SweepGradient bottomGradient = new SweepGradient(0f, 0f, new int[]{
            GREEN, CYAN, BLUE, PURPLE, RED, YELLOW, GREEN,
            GREEN, CYAN, BLUE, PURPLE, RED, YELLOW, GREEN,
            GREEN, CYAN, BLUE, PURPLE, RED, YELLOW, GREEN,
            GREEN, CYAN, BLUE, PURPLE, RED, YELLOW, GREEN,
            GREEN, CYAN, BLUE, PURPLE, RED, YELLOW, GREEN,
            GREEN, CYAN, BLUE, PURPLE, RED, YELLOW, GREEN,
            GREEN, CYAN, BLUE, PURPLE, RED, YELLOW, GREEN,
            GREEN, CYAN, BLUE, PURPLE, RED, YELLOW, GREEN,
            GREEN, CYAN, BLUE, PURPLE, RED, YELLOW, GREEN,


    }, null);
    private Bitmap mStarBitmap;


    public DynamicHeartView(Context context) {
        this(context, null);
    }

    public DynamicHeartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public DynamicHeartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);  
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(PATH_WIDTH);

        mStarPaint.setShader(bottomGradient);
        mStarPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mStarBitmap = DisplayUtil.getBitmapFromVectorDrawable(getContext(), R.drawable.ic_star0);

        mCurrentPosition = new float[2];
        post(new Runnable() {
            @Override
            public void run() {

                mWidth = getMeasuredWidth();
                mHeight = getMeasuredHeight();

                Log.w(TAG, "w:" + mWidth + ";h:" + mHeight);

                mPath = new Path();
                mPath.addRect(mLineWidth, mLineWidth, mWidth - mLineWidth, mHeight - mLineWidth, Path.Direction.CW);
                mPathMeasure = new PathMeasure(mPath, true);

                startPathAnim(10_000L);
            }
        });
    }
  
    @Override  
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mWidth == 0) {
            return;
        }
        canvas.drawColor(Color.LTGRAY);

        int saved = canvas.saveLayer(null,null,Canvas.ALL_SAVE_FLAG);

        canvas.save();
        canvas.translate(mCurrentPosition[0] - mCursorWidth / 2, mCurrentPosition[1] - mCursorHeight / 2);
//        MultiStar.draw(canvas, mCursorWidth, mCursorHeight);
        canvas.drawBitmap(mStarBitmap,mCurrentPosition[0] - mStarBitmap.getWidth() / 2, mCurrentPosition[1] - mStarBitmap.getHeight() / 2, mStarPaint);
        canvas.restore();

        canvas.drawRect(mLineWidth, mLineWidth, mWidth - mLineWidth, mHeight - mLineWidth, mStarPaint);
//        canvas.drawPath(mPath, mPaint);
        canvas.restoreToCount(saved);
    }
  
    public void startPathAnim(long duration) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, mPathMeasure.getLength());
        Log.i(TAG, "measure length = " + mPathMeasure.getLength());
        valueAnimator.setDuration(duration);
        valueAnimator.setRepeatCount(-1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
  
            @Override  
            public void onAnimationUpdate(ValueAnimator animation) {  
                float value = (Float) animation.getAnimatedValue();  
                // 获取当前点坐标封装到mCurrentPosition  
                mPathMeasure.getPosTan(value, mCurrentPosition, null);  
                postInvalidate();  
            }  
        });  
        valueAnimator.start();  
  
    }


}  