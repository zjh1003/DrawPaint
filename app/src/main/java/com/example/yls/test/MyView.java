package com.example.yls.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by yls on 2017/4/20.
 */

public class MyView extends View{
    private Paint mPaint;
    float preX;
    float preY;
    private Path path;
   final int VIEW_WIDTH =768;
    final int VIEW_HEIGHT = 1280;
    //定义一个内存中的图片,该图片将作为缓冲区
    Bitmap cacheBitmap = null;
    //定义cacheBitmap上的canvas对象
    private Canvas canvas;
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        cacheBitmap = Bitmap.createBitmap(VIEW_WIDTH,VIEW_HEIGHT, Bitmap.Config.ARGB_8888);
        canvas = new Canvas();
        path = new Path();
        canvas.setBitmap(cacheBitmap);
        mPaint = new Paint();
        //画笔颜色
        mPaint.setColor(Color.GREEN);
        //画笔风格
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        //反锯齿
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取拖动事件发生的位置
        float x = event.getX();
        float y = event.getY();
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x, y);
                preX = x;
                preY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                path.quadTo(preX, preY, x, y);
                preX = x;
                preY = y;
                break;
            case MotionEvent.ACTION_UP:
                canvas.drawPath(path, mPaint);
                path.reset();
                break;
        }
        invalidate();
        //返回true表明处理方法已经处理该事件
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
       //super.onDraw(canvas);
        Paint bmpPaint = new Paint();
        canvas.drawBitmap(cacheBitmap,0,0,bmpPaint);
        canvas.drawPath(path,mPaint);
    }
}
