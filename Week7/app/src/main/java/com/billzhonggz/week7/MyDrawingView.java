package com.billzhonggz.week7;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ZHONG on 2017/3/20.
 */
public class MyDrawingView extends View {
    //    private Paint p1 = new Paint();
//    private Paint p2 = new Paint();
    private Paint p3 = new Paint();
    private ArrayList<PointF> pointList = new ArrayList<PointF>();

    public MyDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // Draw cube.
//        p1.setColor(Color.RED);
//        p1.setStyle(Paint.Style.STROKE);
        // Draw cylinder.
//        p2.setColor(Color.BLUE);
//        p2.setStyle(Paint.Style.STROKE);
        p3.setColor(Color.RED);
        p3.setStyle(Paint.Style.STROKE);
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    // Get touch position.
                    PointF point = new PointF();
                    point.set(motionEvent.getX(), motionEvent.getY());
                    pointList.add(point);
                    //Toast.makeText(view.getContext(), "x=" + x + "y=" + y, Toast.LENGTH_SHORT).show();
                    invalidate();
                }
                return true;
            }
        });
    }

    protected void onDraw(Canvas c) {
        // Draw cube.
//        c.drawRect(200, 200, 50, 50, p1);
//        c.drawRect(100, 30, 250, 180, p1);
//        c.drawLine(50, 50, 100, 30, p1);
//        c.drawLine(200, 50, 250, 30, p1);
//        c.drawLine(50, 200, 100, 180, p1);
//        c.drawLine(200, 200, 250, 180, p1);
        // Draw cylinder.
//        c.drawOval(50,250,200,280,p2);
//        c.drawOval(50,450,200,480,p2);
//        c.drawLine(50,265,50,465,p2);
//        c.drawLine(200,265,200,465,p2);
        // Draw circle.
        if (pointList.size() < 2) {
            for (PointF p : pointList) {
                c.drawCircle(p.x, p.y, 1, p3);
            }
        } else {
            for (int i = 0; i < pointList.size() - 1; i++) {
                c.drawLine(pointList.get(i).x, pointList.get(i).y, pointList.get(i + 1).x, pointList.get(i + 1).y, p3);
            }
        }
    }

    public void clearAllPoints() {
        pointList.clear();
        invalidate();
    }

    public void undoPoint() {
        pointList.remove(pointList.size() - 1);
        invalidate();
    }
}
