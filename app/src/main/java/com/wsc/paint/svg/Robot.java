package com.wsc.paint.svg;// TODO Include your package name here

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;

public class Robot {
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
        float ow = 512f;
        float oh = 512f;

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
        p.setColor(Color.parseColor("#FFFFFF"));
        c.scale(1.0f,-1.0f);
        c.translate(0.0f,-448.0f * od);
        t.reset();
        t.moveTo(320.0f,341.33f);
        t.lineTo(298.67f,341.33f);
        t.lineTo(298.67f,362.67f);
        t.lineTo(320.0f,362.67f);
        t.moveTo(213.33f,341.33f);
        t.lineTo(192.0f,341.33f);
        t.lineTo(192.0f,362.67f);
        t.lineTo(213.33f,362.67f);
        t.moveTo(331.31f,401.92f);
        t.lineTo(359.25f,429.87f);
        t.cubicTo(363.31f,433.92f,363.31f,440.75f,359.25f,445.01f);
        t.cubicTo(354.99f,449.07f,348.16f,449.07f,344.11f,445.01f);
        t.lineTo(312.53f,413.44f);
        t.cubicTo(295.47f,421.76f,276.27f,426.67f,256.0f,426.67f);
        t.cubicTo(235.52f,426.67f,216.32f,421.76f,199.25f,413.23f);
        t.lineTo(167.47f,445.01f);
        t.cubicTo(163.41f,449.07f,156.59f,449.07f,152.53f,445.01f);
        t.cubicTo(148.27f,440.75f,148.27f,433.92f,152.53f,429.87f);
        t.lineTo(180.48f,401.92f);
        t.cubicTo(148.69f,378.45f,128.0f,341.33f,128.0f,298.67f);
        t.lineTo(384.0f,298.67f);
        t.cubicTo(384.0f,341.33f,362.67f,378.67f,331.31f,401.92f);
        t.moveTo(437.33f,277.33f);
        t.cubicTo(419.63f,277.33f,405.33f,263.04f,405.33f,245.33f);
        t.lineTo(405.33f,96.0f);
        t.cubicTo(405.33f,78.29f,419.63f,64.0f,437.33f,64.0f);
        t.cubicTo(455.04f,64.0f,469.33f,78.29f,469.33f,96.0f);
        t.lineTo(469.33f,245.33f);
        t.cubicTo(469.33f,263.04f,455.04f,277.33f,437.33f,277.33f);
        t.moveTo(74.67f,277.33f);
        t.cubicTo(56.96f,277.33f,42.67f,263.04f,42.67f,245.33f);
        t.lineTo(42.67f,96.0f);
        t.cubicTo(42.67f,78.29f,56.96f,64.0f,74.67f,64.0f);
        t.cubicTo(92.37f,64.0f,106.67f,78.29f,106.67f,96.0f);
        t.lineTo(106.67f,245.33f);
        t.cubicTo(106.67f,263.04f,92.37f,277.33f,74.67f,277.33f);
        t.moveTo(128.0f,64.0f);
        t.cubicTo(128.0f,52.27f,137.6f,42.67f,149.33f,42.67f);
        t.lineTo(170.67f,42.67f);
        t.lineTo(170.67f,-32.0f);
        t.cubicTo(170.67f,-49.71f,184.96f,-64.0f,202.67f,-64.0f);
        t.cubicTo(220.37f,-64.0f,234.67f,-49.71f,234.67f,-32.0f);
        t.lineTo(234.67f,42.67f);
        t.lineTo(277.33f,42.67f);
        t.lineTo(277.33f,-32.0f);
        t.cubicTo(277.33f,-49.71f,291.63f,-64.0f,309.33f,-64.0f);
        t.cubicTo(327.04f,-64.0f,341.33f,-49.71f,341.33f,-32.0f);
        t.lineTo(341.33f,42.67f);
        t.lineTo(362.67f,42.67f);
        t.cubicTo(374.4f,42.67f,384.0f,52.27f,384.0f,64.0f);
        t.lineTo(384.0f,277.33f);
        t.lineTo(128.0f,277.33f);
        t.lineTo(128.0f,64.0f);
        
        t.transform(m);
        c.drawPath(t, p);
        c.drawPath(t, ps);
        c.restore();
        r(2,1,0,3);
        p.setColor(Color.parseColor("#FFFFFF"));
        c.restore();
        r();

        c.restore();
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
                case 2: ps.setColor(Color.argb(0,0,0,0)); break;
                case 3: ps.setStrokeMiter(4.0f*od); break;
            }
        }
    }
};