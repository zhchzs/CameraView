package com.cjt2325.cameralibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;

/**
 * =====================================
 * 作    者: 陈嘉桐 445263848@qq.com
 * 版    本：1.0.4
 * 创建日期：2017/4/26
 * 描    述：拍照或录制完成后弹出的确认和返回按钮
 * =====================================
 */
public class TypeButton extends View {
    public static final int TYPE_CANCEL = 0x001;
    public static final int TYPE_CONFIRM = 0x002;
    private int button_type;
    private int button_size;

    private float center_X;
    private float center_Y;
    private float button_radius;

    private Paint mPaint;
    private Path path;
    private float strokeWidth;

    private float index;
    private RectF rectF;

    public TypeButton(Context context) {
        super(context);
    }

    public TypeButton(Context context, int type, int size) {
        super(context);
        this.button_type = type;
        button_size = size;
        button_radius = size / 2.5f;
        center_X = size / 2.0f;
        center_Y = size / 2.0f;

        mPaint = new Paint();
        path = new Path();
        strokeWidth = size / 50f;
        index = button_size / 12f;
        rectF = new RectF(center_X - button_radius / 2, center_Y - button_radius / 2, center_X + button_radius / 2, center_Y + button_radius / 2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(button_size, button_size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //如果类型为取消，则绘制内部为返回箭头
        if (button_type == TYPE_CANCEL) {
            mPaint.setAntiAlias(true);
            mPaint.setColor(0xFFFCA001);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(5);
            canvas.drawCircle(center_X, center_Y, button_radius, mPaint);

            mPaint.setColor(0xFFFCA001);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(strokeWidth);

            canvas.drawArc(rectF, 150, -320, false, mPaint);
            mPaint.setStyle(Paint.Style.FILL);
            path.reset();

            canvas.drawLine(
                    center_X + rectF.width() / 2 * (float) Math.sin(280 * Math.PI / 180) - 2,
                    center_Y - rectF.width() / 2 * (float) Math.cos(280 * Math.PI / 180),
                    center_X + rectF.width() / 2 * (float) Math.sin(280 * Math.PI / 180) - 2,
                    center_Y - rectF.width() / 2 * (float) Math.cos(280 * Math.PI / 180) - 34,
                    mPaint);
            canvas.drawLine(
                    center_X + rectF.width() / 2 * (float) Math.sin(280 * Math.PI / 180),
                    center_Y - rectF.width() / 2 * (float) Math.cos(280 * Math.PI / 180) - 2,
                    center_X + rectF.width() / 2 * (float) Math.sin(280 * Math.PI / 180) + 26,
                    center_Y - rectF.width() / 2 * (float) Math.cos(280 * Math.PI / 180) - 2,
                    mPaint);
        }
        //如果类型为确认，则绘制绿色勾
        if (button_type == TYPE_CONFIRM) {
            mPaint.setAntiAlias(true);
            mPaint.setColor(0xFFFCA001);
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(center_X, center_Y, button_radius, mPaint);
            mPaint.setAntiAlias(true);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(0xFFFFFFFF);
            mPaint.setStrokeWidth(strokeWidth);
            canvas.drawArc(rectF, 0, 360, false, mPaint);

            path.moveTo(center_X - rectF.width() / 4, center_Y);
            path.lineTo(center_X - 5, center_Y + rectF.width() / 5);
            path.lineTo(center_X + rectF.width() / 3 - 6, center_Y - rectF.width() / 5 + 6);
            canvas.drawPath(path, mPaint);
        }
    }
}