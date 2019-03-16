package com.wsc.paint;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author shichao5
 * @date 2018/11/4
 * @describ
 */
public class MyDrawable extends Drawable {
    private Paint mRedPaint;

    public MyDrawable() {
        mRedPaint = new Paint();
        mRedPaint.setARGB(255, 255,0,0);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        int width = getBounds().width();
        int height = getBounds().height();
        float radius = Math.min(width, height) / 2;
        canvas.drawCircle(width/2,height/2,radius,mRedPaint);
    }

    @Override
    public void setAlpha(int i) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }
}
