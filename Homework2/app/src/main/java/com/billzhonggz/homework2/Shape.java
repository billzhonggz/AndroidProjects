package com.billzhonggz.homework2;

import android.graphics.Canvas;

/**
 * Homework Assignment 2
 * Mobile Application Development
 * 1430003045 Junru Zhong (Bill)
 */

public abstract class Shape implements IShape {
    private float x;
    private float y;

    public Shape(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    @Override
    public abstract boolean isVisible(int w, int h);

    @Override
    public abstract boolean isIn(float x, float y);

    @Override
    public abstract void draw(Canvas c);
}