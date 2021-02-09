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

public class MultiStar {
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
        float ow = 154f;
        float oh = 254f;

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
        t.moveTo(72.89f,166.81f);
        t.lineTo(49.61f,166.11f);
        t.lineTo(72.89f,165.41f);
        t.cubicTo(72.93f,165.18f,72.98f,164.96f,73.06f,164.74f);
        t.lineTo(61.42f,159.29f);
        t.lineTo(73.34f,164.08f);
        t.cubicTo(73.45f,163.88f,73.57f,163.69f,73.71f,163.52f);
        t.lineTo(60.69f,149.69f);
        t.lineTo(74.52f,162.71f);
        t.cubicTo(74.71f,162.56f,74.93f,162.42f,75.15f,162.31f);
        t.lineTo(70.56f,150.3f);
        t.lineTo(75.81f,162.03f);
        t.cubicTo(76.0f,161.97f,76.21f,161.92f,76.41f,161.89f);
        t.lineTo(77.11f,138.61f);
        t.lineTo(77.81f,161.89f);
        t.cubicTo(78.04f,161.93f,78.27f,161.98f,78.48f,162.06f);
        t.lineTo(83.93f,150.42f);
        t.lineTo(79.14f,162.34f);
        t.cubicTo(79.34f,162.45f,79.53f,162.57f,79.71f,162.71f);
        t.lineTo(93.53f,149.69f);
        t.lineTo(80.51f,163.52f);
        t.cubicTo(80.66f,163.71f,80.8f,163.93f,80.91f,164.15f);
        t.lineTo(92.92f,159.56f);
        t.lineTo(81.19f,164.81f);
        t.cubicTo(81.25f,165.0f,81.3f,165.21f,81.33f,165.41f);
        t.lineTo(104.61f,166.11f);
        t.lineTo(81.33f,166.81f);
        t.cubicTo(81.29f,167.04f,81.24f,167.27f,81.16f,167.48f);
        t.lineTo(92.8f,172.93f);
        t.lineTo(80.88f,168.14f);
        t.cubicTo(80.77f,168.34f,80.65f,168.53f,80.51f,168.71f);
        t.lineTo(93.53f,182.53f);
        t.lineTo(79.71f,169.51f);
        t.cubicTo(79.51f,169.66f,79.3f,169.8f,79.07f,169.91f);
        t.lineTo(83.66f,181.92f);
        t.lineTo(78.41f,170.19f);
        t.cubicTo(78.22f,170.25f,78.02f,170.3f,77.81f,170.33f);
        t.lineTo(77.11f,193.61f);
        t.lineTo(76.41f,170.33f);
        t.cubicTo(76.18f,170.29f,75.96f,170.24f,75.74f,170.16f);
        t.lineTo(70.29f,181.8f);
        t.lineTo(75.08f,169.88f);
        t.cubicTo(74.88f,169.77f,74.69f,169.65f,74.52f,169.51f);
        t.lineTo(60.69f,182.53f);
        t.lineTo(73.71f,168.71f);
        t.cubicTo(73.56f,168.51f,73.42f,168.3f,73.31f,168.07f);
        t.lineTo(61.3f,172.66f);
        t.lineTo(73.03f,167.41f);
        t.cubicTo(72.97f,167.22f,72.92f,167.02f,72.89f,166.81f);
        t.lineTo(72.89f,166.81f);
        t.moveTo(100.0f,19.4f);
        t.lineTo(88.66f,19.05f);
        t.lineTo(100.0f,18.71f);
        t.cubicTo(100.02f,18.6f,100.04f,18.49f,100.08f,18.39f);
        t.lineTo(94.41f,15.73f);
        t.lineTo(100.22f,18.07f);
        t.cubicTo(100.27f,17.97f,100.33f,17.88f,100.4f,17.79f);
        t.lineTo(94.05f,11.05f);
        t.lineTo(100.79f,17.4f);
        t.cubicTo(100.89f,17.32f,100.99f,17.26f,101.1f,17.2f);
        t.lineTo(98.86f,11.35f);
        t.lineTo(101.42f,17.07f);
        t.cubicTo(101.52f,17.04f,101.61f,17.01f,101.71f,17.0f);
        t.lineTo(102.05f,5.66f);
        t.lineTo(102.4f,17.0f);
        t.cubicTo(102.51f,17.02f,102.62f,17.04f,102.72f,17.08f);
        t.lineTo(105.38f,11.41f);
        t.lineTo(103.04f,17.22f);
        t.cubicTo(103.14f,17.27f,103.23f,17.33f,103.32f,17.4f);
        t.lineTo(110.05f,11.05f);
        t.lineTo(103.71f,17.79f);
        t.cubicTo(103.78f,17.89f,103.85f,17.99f,103.91f,18.1f);
        t.lineTo(109.76f,15.86f);
        t.lineTo(104.04f,18.42f);
        t.cubicTo(104.07f,18.52f,104.09f,18.61f,104.11f,18.71f);
        t.lineTo(115.45f,19.05f);
        t.lineTo(104.11f,19.4f);
        t.cubicTo(104.09f,19.51f,104.06f,19.62f,104.03f,19.72f);
        t.lineTo(109.7f,22.38f);
        t.lineTo(103.89f,20.04f);
        t.cubicTo(103.84f,20.14f,103.78f,20.23f,103.71f,20.32f);
        t.lineTo(110.05f,27.05f);
        t.lineTo(103.32f,20.71f);
        t.cubicTo(103.22f,20.78f,103.12f,20.85f,103.01f,20.91f);
        t.lineTo(105.24f,26.76f);
        t.lineTo(102.69f,21.04f);
        t.cubicTo(102.59f,21.07f,102.5f,21.09f,102.4f,21.11f);
        t.lineTo(102.05f,32.45f);
        t.lineTo(101.71f,21.11f);
        t.cubicTo(101.6f,21.09f,101.49f,21.06f,101.39f,21.03f);
        t.lineTo(98.73f,26.7f);
        t.lineTo(101.07f,20.89f);
        t.cubicTo(100.97f,20.84f,100.88f,20.78f,100.79f,20.71f);
        t.lineTo(94.05f,27.05f);
        t.lineTo(100.4f,20.32f);
        t.cubicTo(100.32f,20.22f,100.26f,20.12f,100.2f,20.01f);
        t.lineTo(94.35f,22.24f);
        t.lineTo(100.07f,19.69f);
        t.cubicTo(100.04f,19.59f,100.01f,19.5f,100.0f,19.4f);
        t.lineTo(100.0f,19.4f);
        t.moveTo(39.0f,235.4f);
        t.lineTo(27.66f,235.05f);
        t.lineTo(39.0f,234.71f);
        t.cubicTo(39.02f,234.6f,39.04f,234.49f,39.08f,234.39f);
        t.lineTo(33.41f,231.73f);
        t.lineTo(39.22f,234.07f);
        t.cubicTo(39.27f,233.97f,39.33f,233.88f,39.4f,233.79f);
        t.lineTo(33.05f,227.05f);
        t.lineTo(39.79f,233.4f);
        t.cubicTo(39.89f,233.32f,39.99f,233.26f,40.1f,233.2f);
        t.lineTo(37.86f,227.35f);
        t.lineTo(40.42f,233.07f);
        t.cubicTo(40.52f,233.04f,40.61f,233.01f,40.71f,233.0f);
        t.lineTo(41.05f,221.66f);
        t.lineTo(41.4f,233.0f);
        t.cubicTo(41.51f,233.02f,41.62f,233.04f,41.72f,233.08f);
        t.lineTo(44.38f,227.41f);
        t.lineTo(42.04f,233.22f);
        t.cubicTo(42.14f,233.27f,42.23f,233.33f,42.32f,233.4f);
        t.lineTo(49.05f,227.05f);
        t.lineTo(42.71f,233.79f);
        t.cubicTo(42.78f,233.89f,42.85f,233.99f,42.91f,234.1f);
        t.lineTo(48.76f,231.86f);
        t.lineTo(43.04f,234.42f);
        t.cubicTo(43.07f,234.52f,43.09f,234.61f,43.11f,234.71f);
        t.lineTo(54.45f,235.05f);
        t.lineTo(43.11f,235.4f);
        t.cubicTo(43.09f,235.51f,43.06f,235.62f,43.03f,235.72f);
        t.lineTo(48.7f,238.38f);
        t.lineTo(42.89f,236.04f);
        t.cubicTo(42.84f,236.14f,42.78f,236.23f,42.71f,236.32f);
        t.lineTo(49.05f,243.05f);
        t.lineTo(42.32f,236.71f);
        t.cubicTo(42.22f,236.78f,42.12f,236.85f,42.01f,236.91f);
        t.lineTo(44.24f,242.76f);
        t.lineTo(41.69f,237.04f);
        t.cubicTo(41.59f,237.07f,41.5f,237.09f,41.4f,237.11f);
        t.lineTo(41.05f,248.45f);
        t.lineTo(40.71f,237.11f);
        t.cubicTo(40.6f,237.09f,40.49f,237.06f,40.39f,237.03f);
        t.lineTo(37.73f,242.7f);
        t.lineTo(40.07f,236.89f);
        t.cubicTo(39.97f,236.84f,39.88f,236.78f,39.79f,236.71f);
        t.lineTo(33.05f,243.05f);
        t.lineTo(39.4f,236.32f);
        t.cubicTo(39.32f,236.22f,39.26f,236.12f,39.2f,236.01f);
        t.lineTo(33.35f,238.24f);
        t.lineTo(39.07f,235.69f);
        t.cubicTo(39.04f,235.59f,39.01f,235.5f,39.0f,235.4f);
        t.lineTo(39.0f,235.4f);
        t.moveTo(102.81f,127.03f);
        t.lineTo(87.59f,126.57f);
        t.lineTo(102.81f,126.12f);
        t.cubicTo(102.84f,125.96f,102.87f,125.82f,102.92f,125.68f);
        t.lineTo(95.31f,122.11f);
        t.lineTo(103.11f,125.25f);
        t.cubicTo(103.18f,125.12f,103.26f,124.99f,103.35f,124.88f);
        t.lineTo(94.84f,115.84f);
        t.lineTo(103.88f,124.35f);
        t.cubicTo(104.01f,124.25f,104.14f,124.16f,104.29f,124.09f);
        t.lineTo(101.29f,116.24f);
        t.lineTo(104.72f,123.91f);
        t.cubicTo(104.85f,123.87f,104.98f,123.84f,105.12f,123.81f);
        t.lineTo(105.57f,108.59f);
        t.lineTo(106.03f,123.81f);
        t.cubicTo(106.18f,123.84f,106.33f,123.87f,106.47f,123.92f);
        t.lineTo(110.03f,116.31f);
        t.lineTo(106.9f,124.11f);
        t.cubicTo(107.03f,124.18f,107.15f,124.26f,107.27f,124.35f);
        t.lineTo(116.31f,115.84f);
        t.lineTo(107.8f,124.88f);
        t.cubicTo(107.9f,125.01f,107.98f,125.14f,108.06f,125.29f);
        t.lineTo(115.91f,122.29f);
        t.lineTo(108.24f,125.72f);
        t.cubicTo(108.28f,125.85f,108.31f,125.98f,108.33f,126.12f);
        t.lineTo(123.55f,126.57f);
        t.lineTo(108.33f,127.03f);
        t.cubicTo(108.31f,127.18f,108.27f,127.33f,108.22f,127.47f);
        t.lineTo(115.83f,131.03f);
        t.lineTo(108.04f,127.9f);
        t.cubicTo(107.97f,128.03f,107.89f,128.15f,107.8f,128.27f);
        t.lineTo(116.31f,137.31f);
        t.lineTo(107.27f,128.8f);
        t.cubicTo(107.14f,128.9f,107.0f,128.98f,106.86f,129.06f);
        t.lineTo(109.85f,136.91f);
        t.lineTo(106.42f,129.24f);
        t.cubicTo(106.3f,129.28f,106.16f,129.31f,106.03f,129.33f);
        t.lineTo(105.57f,144.55f);
        t.lineTo(105.12f,129.33f);
        t.cubicTo(104.96f,129.31f,104.82f,129.27f,104.68f,129.22f);
        t.lineTo(101.11f,136.83f);
        t.lineTo(104.25f,129.04f);
        t.cubicTo(104.12f,128.97f,103.99f,128.89f,103.88f,128.8f);
        t.lineTo(94.84f,137.31f);
        t.lineTo(103.35f,128.27f);
        t.cubicTo(103.25f,128.14f,103.16f,128.0f,103.09f,127.86f);
        t.lineTo(95.24f,130.85f);
        t.lineTo(102.91f,127.42f);
        t.cubicTo(102.87f,127.3f,102.84f,127.16f,102.81f,127.03f);
        t.lineTo(102.81f,127.03f);
        t.moveTo(53.83f,80.41f);
        t.lineTo(13.25f,79.19f);
        t.lineTo(53.83f,77.97f);
        t.cubicTo(53.9f,77.57f,54.0f,77.18f,54.13f,76.8f);
        t.lineTo(33.83f,67.3f);
        t.lineTo(54.63f,75.66f);
        t.cubicTo(54.81f,75.31f,55.03f,74.98f,55.26f,74.67f);
        t.lineTo(32.56f,50.56f);
        t.lineTo(56.67f,73.26f);
        t.cubicTo(57.01f,73.0f,57.38f,72.77f,57.77f,72.56f);
        t.lineTo(49.78f,51.63f);
        t.lineTo(58.93f,72.09f);
        t.cubicTo(59.27f,71.98f,59.62f,71.89f,59.97f,71.83f);
        t.lineTo(61.19f,31.25f);
        t.lineTo(62.41f,71.83f);
        t.cubicTo(62.82f,71.9f,63.21f,72.0f,63.59f,72.13f);
        t.lineTo(73.09f,51.83f);
        t.lineTo(64.73f,72.63f);
        t.cubicTo(65.08f,72.81f,65.41f,73.03f,65.72f,73.26f);
        t.lineTo(89.82f,50.56f);
        t.lineTo(67.12f,74.67f);
        t.cubicTo(67.39f,75.01f,67.62f,75.38f,67.82f,75.77f);
        t.lineTo(88.76f,67.78f);
        t.lineTo(68.3f,76.93f);
        t.cubicTo(68.41f,77.27f,68.49f,77.62f,68.55f,77.97f);
        t.lineTo(109.14f,79.19f);
        t.lineTo(68.55f,80.41f);
        t.cubicTo(68.49f,80.82f,68.39f,81.21f,68.26f,81.59f);
        t.lineTo(88.55f,91.09f);
        t.lineTo(67.76f,82.73f);
        t.cubicTo(67.58f,83.08f,67.36f,83.41f,67.12f,83.72f);
        t.lineTo(89.82f,107.82f);
        t.lineTo(65.72f,85.12f);
        t.cubicTo(65.37f,85.39f,65.0f,85.62f,64.62f,85.82f);
        t.lineTo(72.61f,106.76f);
        t.lineTo(63.46f,86.3f);
        t.cubicTo(63.12f,86.41f,62.77f,86.49f,62.41f,86.55f);
        t.lineTo(61.19f,127.14f);
        t.lineTo(59.97f,86.55f);
        t.cubicTo(59.57f,86.49f,59.18f,86.39f,58.8f,86.26f);
        t.lineTo(49.3f,106.55f);
        t.lineTo(57.66f,85.76f);
        t.cubicTo(57.31f,85.58f,56.98f,85.36f,56.67f,85.12f);
        t.lineTo(32.56f,107.82f);
        t.lineTo(55.26f,83.72f);
        t.cubicTo(55.0f,83.37f,54.77f,83.0f,54.56f,82.62f);
        t.lineTo(33.63f,90.61f);
        t.lineTo(54.09f,81.46f);
        t.cubicTo(53.98f,81.12f,53.89f,80.77f,53.83f,80.41f);
        t.lineTo(53.83f,80.41f);
        
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
        return new star0Drawable(size);
    }

    public static Drawable getTintedDrawable(int size, int color){
        return new star0Drawable(size, color);
    }

    private static class star0Drawable extends Drawable {
        private int s = 0;
        private ColorFilter cf = null;

        public star0Drawable(int s) {
            this.s = s;
            setBounds(0, 0, s, s);
            invalidateSelf();
        }

        public star0Drawable(int s, int c) {
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
            MultiStar.cf = cf;
            MultiStar.draw(c, b.width(), b.height(), b.left, b.top);
            MultiStar.cf = null;
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