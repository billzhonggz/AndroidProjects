package com.billzhonggz.week6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by ZHONG on 2017/3/16.
 */
public class MyDrawingView extends View {
    private Paint p = new Paint();
    private Random r = new Random();
    private float x = 0.0F;
    private float y = 0.0F;

    public MyDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        p.setColor(Color.RED);
        p.setStyle(Paint.Style.STROKE);
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    // Compute width and height at the last moment, while they change from time to time.
                    x = motionEvent.getX();
                    y = motionEvent.getY();
                    //Toast.makeText(view.getContext(), "x=" + x + "y=" + y, Toast.LENGTH_SHORT).show();
                    invalidate();
                }
                return true;
            }
        });
    }

    @Override
    protected void onDraw(Canvas c) {
        c.drawCircle(x, y, 20, p);
    }
}
