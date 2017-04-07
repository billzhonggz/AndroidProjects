package com.billzhonggz.homework2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

/**
 * Homework Assignment 2
 * Mobile Application Development
 * 1430003045 Junru Zhong (Bill)
 */

public class Bubble extends Shape {
    private float radius;
    private Paint p = new Paint();
    private Random random = new Random();

    public Bubble(float x, float y) {
        super(x, y);
        this.radius = 20.0F;
        // Generate and set color.
        this.p.setColor(Color.rgb(random.nextInt(), random.nextInt(), random.nextInt()));
        this.p.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    public boolean isVisible(int w, int h) {
        float x = this.getX();
        float y = this.getY();
        float wx = (x < 0 ? 0 : (x > w ? w : x));
        float wy = (y < 0 ? 0 : (x > h ? h : y));
        float dx = wx - x;
        float dy = wy - y;
        return dx * dx + dy * dy <= radius * radius;
    }

    @Override
    public boolean isIn(float x, float y) {
        // Calculate distance by distance formula between two points.
        float distance = (float) Math.sqrt(Math.pow((x - this.getX()), 2) + Math.pow((y - this.getY()), 2));
        // Determine whether the point is inside the bubble.
        if (distance <= radius)
            return true;
        else
            return false;
    }

    @Override
    public void draw(Canvas c) {
        c.drawCircle(this.getX(), this.getY(), radius, p);
    }
}
