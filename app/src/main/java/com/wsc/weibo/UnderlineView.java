package com.wsc.weibo;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.wsc.R;

@SuppressWarnings({"WeakerAccess", "unused"})
public class UnderlineView extends View {
    private static final float UNDERLINE_CLEAR_GAP = 5.5f;

    private final String mFamily;
    private final String mText;

    private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Rect mBounds = new Rect();
    private final Paint mStroke = new Paint();
    private final Path mUnderline = new Path();
    private final Path mOutline = new Path();
    private float density;

    public UnderlineView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UnderlineView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public UnderlineView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.UnderlineView,
                defStyleAttr, defStyleRes);
        try {
            int type = a.getInt(R.styleable.UnderlineView_type, 0);

            String family = a.getString(R.styleable.UnderlineView_family);
            if (family == null) family = "cursive";
            mFamily = family;

            String text = a.getString(R.styleable.UnderlineView_text);
            if (text == null) text = "Elegant, practical, high-quality";
            mText = text;
        } finally {
            a.recycle();
        }
        density = getContext().getResources().getDisplayMetrics().density;

        init();
    }

    private void init() {
        mPaint.setTextSize(60f * density);
        mPaint.setTypeface(Typeface.create(mFamily, Typeface.NORMAL));
        //1.获取文字的显示范围
        mPaint.getTextBounds(mText, 0, mText.length(), mBounds);
        //2.获取目标文字所对应的Path
        mPaint.getTextPath(mText, 0, mText.length(), 0.0f, 0.0f, mOutline);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mUnderline.isEmpty()) {
            Path strokedOutline = new Path();
            //3.将下划线矩形添加到路径
            mUnderline.addRect((float) mBounds.left, 3.0f * density,
                    (float) mBounds.right, 5.8f * density, Path.Direction.CW);
            //4.文本轮廓与下划线相交的部分
            mOutline.op(mUnderline, Path.Op.INTERSECT);
            //5.扩展相交部分的大小（根据paint的设置，计算出相交部分新的路径）
            mStroke.setStyle(Paint.Style.STROKE);
            mStroke.setStrokeWidth(UNDERLINE_CLEAR_GAP * density);
            mStroke.getFillPath(mOutline, strokedOutline);
            //6.从下划线中减去（扩展后）相交部分
            mUnderline.op(strokedOutline, Path.Op.DIFFERENCE);
        }
        canvas.translate(100,200);
        canvas.drawText(mText, 0.0f, 0.0f, mPaint);
        canvas.drawPath(mUnderline, mPaint);
    }
}
