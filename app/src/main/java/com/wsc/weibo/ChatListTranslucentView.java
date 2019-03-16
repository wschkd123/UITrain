package com.wsc.weibo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class ChatListTranslucentView extends RelativeLayout {
    private Paint mPaint;
    private float gradientSize = 60;
    private int[] mGradientColors = {0xffffffff, 0x00000000};
    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mPaint.setShader(new LinearGradient(0, 0, 0, gradientSize, mGradientColors,
                null, Shader.TileMode.CLAMP));
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }

    public ChatListTranslucentView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        int layerSave = canvas.saveLayer(0, 0, getWidth(), getHeight(), null,
                Canvas.ALL_SAVE_FLAG);
        super.dispatchDraw(canvas);
        canvas.drawRect(0, 0, getMeasuredWidth(), gradientSize, mPaint);
        canvas.restoreToCount(layerSave);
    }
}
