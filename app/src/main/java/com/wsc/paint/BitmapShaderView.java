package com.wsc.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.view.View;

import com.wsc.R;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;

/**
 * @author shichao5
 * @date 2018/11/6
 * @describ 通过Paint.setShader绘制圆形的Bitmap
 */
public class BitmapShaderView extends View {

    private Paint mPaint;

    public BitmapShaderView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPaint = new Paint(ANTI_ALIAS_FLAG);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
        Shader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint.setShader(shader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(300,300,200,mPaint);
    }
}
