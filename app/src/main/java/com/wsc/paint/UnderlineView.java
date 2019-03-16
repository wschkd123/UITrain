package com.wsc.paint;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
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

        init();
    }

    private void init() {
        density = getContext().getResources().getDisplayMetrics().density;

        mPaint.setTextSize(60f * density);
        mPaint.setTypeface(Typeface.create(mFamily, Typeface.NORMAL));

        mPaint.getTextBounds(mText, 0, mText.length(), mBounds);
        mPaint.getTextPath(mText, 0, mText.length(), 0.0f, 0.0f, mOutline);

        mStroke.setStyle(Paint.Style.FILL_AND_STROKE);
        mStroke.setStrokeWidth(UNDERLINE_CLEAR_GAP * density);
        mStroke.setStrokeCap(Paint.Cap.BUTT);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mUnderline.isEmpty()) {
            buildUnderline();
        }

        // Kind-of centered
        canvas.translate(
                (int) ((getWidth() - mBounds.width()) / 2.0f),
                (int) ((getHeight() - mBounds.height()) / 2.0f));

        canvas.drawText(mText, 0.0f, 0.0f, mPaint);
        canvas.drawPath(mUnderline, mPaint);
    }

    private void buildUnderline() {
        Path strokedOutline = new Path();

        // 将下划线矩形添加到路径
        mUnderline.addRect(
                (float) mBounds.left, 3.0f * density,
                (float) mBounds.right, 3.8f * density,
                Path.Direction.CW);

        // 保留文本轮廓与下划线路径相交部分
        mOutline.op(mUnderline, Path.Op.INTERSECT);
        // 根据mStroke的设置，计算剪切的文本轮廓mOutline实际Path
        mStroke.getFillPath(mOutline, strokedOutline);
        // 从下划线中减去描边轮廓
        mUnderline.op(strokedOutline, Path.Op.DIFFERENCE);
    }


}
