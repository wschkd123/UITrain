package com.wsc.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.view.View;

/**
 * @author shichao5
 * @date 2018/11/7
 * @describ
 */
public class PathEffectView extends View {

    private Paint mPaint;

    public PathEffectView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();
        path.moveTo(100,100);
        path.lineTo(200, 100);
        path.lineTo(150,150);
        path.lineTo(300,100);
        path.addRect(new RectF(50,50,500,500), Path.Direction.CCW);
        //三角形
        Path deshPath = new Path();
        deshPath.moveTo(100,100);
        deshPath.lineTo(200, 100);
        deshPath.lineTo(150,150);
        deshPath.close();

        mPaint.getFillPath(path,path);

        PathEffect pathEffct = new PathDashPathEffect(deshPath,40,0,PathDashPathEffect.Style.TRANSLATE);
        mPaint.setPathEffect(pathEffct);
        canvas.drawPath(path, mPaint);
    }
}
