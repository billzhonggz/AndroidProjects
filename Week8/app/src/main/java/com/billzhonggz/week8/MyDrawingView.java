package com.billzhonggz.week8;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

/**
 * Created by ZHONG on 2017/3/27.
 */
public class MyDrawingView extends View {
    private Paint p1 = new Paint();
    private Paint p2 = new Paint();
    private Random random = new Random();
    private float x = 100F;
    private float y = 100F;
    private float r = 10F;
    private double degree = random.nextDouble() * 2 * Math.PI;
    private float dx = (float) (15 * Math.cos(degree));
    private float dy = (float) (15 * Math.sin(degree));
    private Handler h;
    private long delay = 100;
    private boolean clicking;
    private float rx = 0F;
    private float ry = 0F;
    private float rw = 20F;
    private float rh = 50F;
    private float my;

    public MyDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        p1.setColor(Color.BLUE);
        p1.setStyle(Paint.Style.STROKE);
        p2.setColor(Color.RED);
        p2.setStyle(Paint.Style.FILL);

        // Handler here.
        h = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                // Toast.makeText(mdv.getContext(), "Call Back!", Toast.LENGTH_SHORT).show();
                // Determine whether the ball reach the edge.
                x += dx;
                y += dy;
                if (x + r >= getWidth() || x - r <= 0) {
                    dx *= -1;
                }
                if (y + r >= getHeight() || y - r <= 0) {
                    dy *= -1;
                }
                invalidate();
                Message m = h.obtainMessage(0);
                h.sendMessageDelayed(m, delay);
                return false;
            }
        });

        MyDrawingView mdv = (MyDrawingView) findViewById(R.id.drawing_area);
        mdv.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    clicking = true;
                    my = motionEvent.getY();
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    clicking = false;
                    if (motionEvent.getY() != my) {
                        ry += (motionEvent.getY() - my);
                        rh += (motionEvent.getY() - my);
                        invalidate();
                    }
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (clicking == true) {
                        x = motionEvent.getX();
                        y = motionEvent.getY();
                        invalidate();
                    }
                }
                return true;
            }
        });
    }

    @Override
    protected void onDraw(Canvas c) {
        c.drawCircle(x, y, r, p1);
        c.drawRect(rx, ry, rw, rh, p2);
    }

    protected void startTicker() {
        Message m = h.obtainMessage(0);
        h.sendMessageDelayed(m, delay);
    }

    protected void stopTicker() {
        h.removeMessages(0);
    }
}
