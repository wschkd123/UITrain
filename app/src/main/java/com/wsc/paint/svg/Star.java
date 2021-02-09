package com.wsc.paint.svg;// TODO Include your package name here

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.ColorFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;

public class Star {
    private static final Paint  p  = new Paint();
    private static final Paint  ps = new Paint();
    private static final Path   t  = new Path();
    private static final Matrix m  = new Matrix();
    private static float od;
    protected static ColorFilter cf = null;

    /**
     *  IMPORTANT: Due to the static usage of this class this
     *  method sets the tint color statically. So it is highly
     *  recommended to call the clearColorTint method when you
     *  have finished drawing. 
     *
     *  Sets the color to use when drawing the SVG. This replaces
     *  all parts of the drawable which are not completely
     *  transparent with this color.
     */
    public static void setColorTint(int color){
        cf = new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN);
    }

    public static void clearColorTint(int color){
        cf = null;
    }

    public static void draw(Canvas c, int w, int h){
        draw(c, w, h, 0, 0);
    }

    public static void draw(Canvas c, int w, int h, int dx, int dy){
        float ow = 128f;
        float oh = 128f;

        od = (w / ow < h / oh) ? w / ow : h / oh;

        r();
        c.save();
        c.translate((w - od * ow) / 2f + dx, (h - od * oh) / 2f + dy);

        m.reset();
        m.setScale(od, od);

        c.save();
        ps.setColor(Color.argb(0,0,0,0));
        ps.setStrokeCap(Paint.Cap.BUTT);
        ps.setStrokeJoin(Paint.Join.MITER);
        ps.setStrokeMiter(4.0f*od);
        c.scale(1.0f,1.0f);
        c.save();
        c.restore();
        r(5,1,0,2);
        c.save();
        c.restore();
        r(5,1,0,2);
        c.save();
        p.setColor(Color.argb(0,0,0,0));
        ps.setStrokeWidth(1.0f*od);
        c.save();
        p.setColor(Color.parseColor("#FFFFFF"));
        t.reset();
        t.moveTo(57.09f,65.15f);
        t.lineTo(19.0f,64.0f);
        t.lineTo(57.09f,62.85f);
        t.cubicTo(57.16f,62.48f,57.25f,62.11f,57.37f,61.76f);
        t.lineTo(38.32f,52.84f);
        t.lineTo(57.84f,60.68f);
        t.cubicTo(58.01f,60.36f,58.21f,60.05f,58.43f,59.75f);
        t.lineTo(37.13f,37.13f);
        t.lineTo(59.75f,58.43f);
        t.cubicTo(60.08f,58.19f,60.42f,57.97f,60.79f,57.78f);
        t.lineTo(53.28f,38.13f);
        t.lineTo(61.87f,57.33f);
        t.cubicTo(62.19f,57.23f,62.52f,57.15f,62.85f,57.09f);
        t.lineTo(64.0f,19.0f);
        t.lineTo(65.15f,57.09f);
        t.cubicTo(65.52f,57.16f,65.89f,57.25f,66.24f,57.37f);
        t.lineTo(75.16f,38.32f);
        t.lineTo(67.32f,57.84f);
        t.cubicTo(67.64f,58.01f,67.95f,58.21f,68.25f,58.43f);
        t.lineTo(90.87f,37.13f);
        t.lineTo(69.57f,59.75f);
        t.cubicTo(69.81f,60.08f,70.03f,60.42f,70.22f,60.79f);
        t.lineTo(89.87f,53.28f);
        t.lineTo(70.67f,61.87f);
        t.cubicTo(70.77f,62.19f,70.85f,62.52f,70.91f,62.85f);
        t.lineTo(109.0f,64.0f);
        t.lineTo(70.91f,65.15f);
        t.cubicTo(70.84f,65.52f,70.75f,65.89f,70.63f,66.24f);
        t.lineTo(89.68f,75.16f);
        t.lineTo(70.16f,67.32f);
        t.cubicTo(69.99f,67.64f,69.79f,67.95f,69.57f,68.25f);
        t.lineTo(90.87f,90.87f);
        t.lineTo(68.25f,69.57f);
        t.cubicTo(67.92f,69.81f,67.58f,70.03f,67.21f,70.22f);
        t.lineTo(74.72f,89.87f);
        t.lineTo(66.13f,70.67f);
        t.cubicTo(65.81f,70.77f,65.48f,70.85f,65.15f,70.91f);
        t.lineTo(64.0f,109.0f);
        t.lineTo(62.85f,70.91f);
        t.cubicTo(62.48f,70.84f,62.11f,70.75f,61.76f,70.63f);
        t.lineTo(52.84f,89.68f);
        t.lineTo(60.68f,70.16f);
        t.cubicTo(60.36f,69.99f,60.05f,69.79f,59.75f,69.57f);
        t.lineTo(37.13f,90.87f);
        t.lineTo(58.43f,68.25f);
        t.cubicTo(58.19f,67.92f,57.97f,67.58f,57.78f,67.21f);
        t.lineTo(38.13f,74.72f);
        t.lineTo(57.33f,66.13f);
        t.cubicTo(57.23f,65.81f,57.15f,65.48f,57.09f,65.15f);
        
        t.transform(m);
        c.drawPath(t, p);
        c.drawPath(t, ps);
        c.restore();
        r(5,1,0,2,3,4);
        p.setColor(Color.parseColor("#FFFFFF"));
        c.restore();
        r(5,1,0,2);
        p.setColor(Color.parseColor("#FFFFFF"));
        ps.setStrokeWidth(1.0f*od);
        c.restore();
        r();

        c.restore();
    }

    public static Drawable getDrawable(int size){
        return new starDrawable(size);
    }

    public static Drawable getTintedDrawable(int size, int color){
        return new starDrawable(size, color);
    }

    private static class starDrawable extends Drawable {
        private int s = 0;
        private ColorFilter cf = null;

        public starDrawable(int s) {
            this.s = s;
            setBounds(0, 0, s, s);
            invalidateSelf();
        }

        public starDrawable(int s, int c) {
            this(s);
            cf = new PorterDuffColorFilter(c, PorterDuff.Mode.SRC_IN);
        }

        @Override
        public int getIntrinsicHeight() {
            return s;
        }

        @Override
        public int getIntrinsicWidth() {
            return s;
        }

        @Override
        public void draw(Canvas c) {
            Rect b = getBounds();
            Star.cf = cf;
            Star.draw(c, b.width(), b.height(), b.left, b.top);
            Star.cf = null;
        }

        @Override
        public void setAlpha(int i) {}

        @Override
        public void setColorFilter(ColorFilter c) { cf = c; invalidateSelf(); }

        @Override
        public int getOpacity() {
            return 0;
        }
    }

    private static void r(Integer... o){
        p.reset();
        ps.reset();
        if(cf != null){
            p.setColorFilter(cf);
            ps.setColorFilter(cf);
        }
        p.setAntiAlias(true);
        ps.setAntiAlias(true);
        p.setStyle(Paint.Style.FILL);
        ps.setStyle(Paint.Style.STROKE);
        for(Integer i : o){
            switch (i){
                case 0: ps.setStrokeJoin(Paint.Join.MITER); break;
                case 1: ps.setStrokeCap(Paint.Cap.BUTT); break;
                case 2: ps.setStrokeMiter(4.0f*od); break;
                case 3: p.setColor(Color.argb(0,0,0,0)); break;
                case 4: ps.setStrokeWidth(1.0f*od); break;
                case 5: ps.setColor(Color.argb(0,0,0,0)); break;
            }
        }
    }
};