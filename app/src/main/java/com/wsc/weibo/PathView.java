package com.wsc.weibo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;

public class PathView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path = new Path();
    private RectF mArcRect1, mArcRect2;

    {
        mArcRect1 = new RectF(200, 200, 400, 400);
        mArcRect2 = new RectF(400, 200, 600, 400);
    }

    public PathView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画心
        paint.setStrokeWidth(8);
        paint.setStyle(Paint.Style.STROKE);
        path.addArc(mArcRect1, -225, 225);
        path.arcTo(mArcRect2, -180, 225, false);
        path.lineTo(400, 542);
        path.close();
        canvas.drawPath(path, paint);

        paint.reset();
        paint.setStyle(Paint.Style.FILL);
        path.reset();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.addCircle(300, 400, 200, Path.Direction.CW);
        path.addCircle(600, 400, 200, Path.Direction.CW);
        canvas.translate(0, 500);
        canvas.drawPath(path, paint);

        path.setFillType(Path.FillType.WINDING);
        canvas.translate(0, 500);
        canvas.drawPath(path, paint);
    }
}
