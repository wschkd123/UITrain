package com.wsc.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ComposeShader;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.view.View;

import com.wsc.R;
import com.wsc.utils.BitmapUtils;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;

/**
 * @author shichao5
 * @date 2018/11/6
 * @describ 通过Paint.setShader绘制圆形的Bitmap
 */
public class ComposeShaderView extends View {

    private Paint mPaint;

    public ComposeShaderView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPaint = new Paint(ANTI_ALIAS_FLAG);
        // 第一个 Shader：头像的 Bitmap
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
        bitmap1 = BitmapUtils.createScaledBitmap(bitmap1, 300, 300, Bitmap.Config.ARGB_8888);
        Shader shader1 = new BitmapShader(bitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        // 第二个 Shader：从上到下的线性渐变（由透明到黑色）
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.praise_red);
        bitmap2 = BitmapUtils.createScaledBitmap(bitmap2, 100, 100, Bitmap.Config.ARGB_8888);
        Shader shader2 = new BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        // ComposeShader：结合两个 Shader
        Shader shader = new ComposeShader(shader1, shader2, PorterDuff.Mode.SRC_OVER);
        mPaint.setShader(shader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, 200,300, mPaint);
    }
}
