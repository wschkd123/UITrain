package com.wsc.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.Log;
import android.view.View;

import com.wsc.R;

/**
 * @author shichao5
 * @date 2018/11/7
 * @describ
 */
public class PorterDuffView extends View {

    private Paint mPaint;
    private Bitmap mDestinationImage, mSourceImage;

    public PorterDuffView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mDestinationImage = BitmapFactory.decodeResource(getResources(), R.drawable.composite_dst);
        mSourceImage = BitmapFactory.decodeResource(getResources(), R.drawable.composite_src);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int saved = canvas.saveLayer(null,null,Canvas.ALL_SAVE_FLAG);

        Log.w("PorterDuffView", "onDraw");
        canvas.drawBitmap(mDestinationImage, 0,0,mPaint);
        PorterDuff.Mode mMode = PorterDuff.Mode.OVERLAY;
        mPaint.setXfermode(new PorterDuffXfermode(mMode));
        canvas.drawBitmap(mSourceImage,0,0,mPaint);

        canvas.restoreToCount(saved);
    }
}
