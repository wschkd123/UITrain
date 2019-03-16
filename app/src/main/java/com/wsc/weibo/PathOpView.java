package com.wsc.weibo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class PathOpView extends View {

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    {
        mPaint.setTextSize(60);
    }

    public PathOpView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int x = 80, r = 100;
        Path path1 = new Path();
        Path path2 = new Path();
        Path pathOpResult = new Path();
        canvas.translate(250,0);
        path1.addCircle(-x, 0, r, Path.Direction.CW);
        path2.addCircle(x, 0, r, Path.Direction.CW);
        //1.Path1中减去Path2后剩下的部分(差集)
        pathOpResult.op(path1,path2, Path.Op.DIFFERENCE);
        canvas.translate(0, 200);
        canvas.drawPath(pathOpResult,mPaint);
        canvas.drawText("DIFFERENCE", 240,0,mPaint);
        //2.Path2中减去Path1后剩下的部分（差集）
        pathOpResult.op(path1,path2, Path.Op.REVERSE_DIFFERENCE);
        canvas.translate(0, 300);
        canvas.drawPath(pathOpResult,mPaint);
        canvas.drawText("REVERSE_DIFFERENCE", 240,0,mPaint);
        //3.Path1与Path2相交的部分(交集)
        pathOpResult.op(path1,path2, Path.Op.INTERSECT);
        canvas.translate(0, 300);
        canvas.drawPath(pathOpResult,mPaint);
        canvas.drawText("INTERSECT", 240,0,mPaint);
        //4.包含全部Path1和Path2(并集)
        pathOpResult.op(path1,path2, Path.Op.UNION);
        canvas.translate(0, 300);
        canvas.drawPath(pathOpResult,mPaint);
        canvas.drawText("UNION", 240,0,mPaint);
        //5.包含Path1与Path2但不包括两者相交的部分（异或）
        pathOpResult.op(path1,path2, Path.Op.XOR);
        canvas.translate(0, 300);
        canvas.drawPath(pathOpResult,mPaint);
        canvas.drawText("XOR", 240,0,mPaint);

    }
}
