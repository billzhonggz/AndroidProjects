package com.billzhonggz.homework2;

import android.graphics.Canvas;

/**
 * Homework Assignment 2
 * Mobile Application Development
 * 1430003045 Junru Zhong (Bill)
 */

public interface IShape {
    public float getX();
    public float getY();
    public void setX(float x);
    public void setY(float y);
    public boolean isVisible(int w, int h);
    public boolean isIn(float x, float y);
    public void draw(Canvas c);
}
