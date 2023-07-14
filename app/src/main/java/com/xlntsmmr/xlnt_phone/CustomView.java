package com.xlntsmmr.xlnt_phone;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class CustomView extends View {

    private CustomViewListener customViewListener;

    public interface CustomViewListener {
        void onCoordinatesChanged(float x, float y);
    }

    public void setCustomViewListener(CustomViewListener listener) {
        this.customViewListener = listener;
    }

    String TAG = "CustomView";

    private Paint paint;
    private Path path;

    public float x;
    public float y;

    public CustomView(Context context) {
        super(context);
        init();
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);

        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = event.getX();
        y = event.getY();

        Log.d(TAG,"x위치 : " + x + " y 위치" + y);

        if (customViewListener != null) {
            Log.d(TAG,"값 변경");
            customViewListener.onCoordinatesChanged(x, y);
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return true;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }
}