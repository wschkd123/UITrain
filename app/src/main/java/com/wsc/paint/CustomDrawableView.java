package com.wsc.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.view.View;

/**
 * @author shichao5
 * @date 2018/11/4
 * @describ
 */
public class CustomDrawableView extends View {
    private ShapeDrawable mDrawable;

    public CustomDrawableView(Context context) {
        super(context);
        int x = 10;
        int y = 10;
        int width = 300;
        int height = 50;
        mDrawable = new ShapeDrawable(new ArcShape(0,90));
        mDrawable.getPaint().setColor(0xff74AC23);
        mDrawable.setBounds(x, y, x + width, y + height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        mDrawable.draw(canvas);
    }
}
