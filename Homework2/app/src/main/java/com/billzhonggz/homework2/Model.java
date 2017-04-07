package com.billzhonggz.homework2;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Random;

/**
 * Homework Assignment 2
 * Mobile Application Development
 * 1430003045 Junru Zhong (Bill)
 */

public class Model {
    private int score = 0;
    private ArrayList<IShape> bubbles;
    private ArrayList<IModelListener> listeners;
    private Random random = new Random();

    public Model() {
        score = 0;
        bubbles = new ArrayList<IShape>();
        listeners = new ArrayList<IModelListener>();
    }

    public int getScore() {
        return score;
    }

    public void addBubble(int w, int h) {
        // Generate random number for the bubble.
        float x = random.nextFloat() * w;
        float y = random.nextFloat() * h;
        // Add the bubble to the list.
        bubbles.add(new Bubble(x, y));
        // Notify listeners.
        this.notifyAllModelListener();
    }

    public void addListener(IModelListener listener) {
        listeners.add(listener);
    }

    public void moveAll(float dx, float dy) {
        for (IShape s : bubbles) {
            s.setX(s.getX() + dx);
            s.setY(s.getY() + dy);
        }
        // Notify listeners.
        this.notifyAllModelListener();
    }

    public void clearInvisibles(int w, int h) {
        for (int i = bubbles.size() - 1; i >= 0; i--) {
            if (!(bubbles.get(i).isVisible(w, h))) {
                bubbles.remove(i);
                score = score - 1;
                // Notify listeners.
                this.notifyAllModelListener();
            }
        }
    }

    public void deleteBubblesAtPoint(float x, float y) {
        for (int i = bubbles.size() - 1; i >= 0; i--) {
            if (bubbles.get(i).isIn(x, y)) {
                bubbles.remove(i);
                score = score + 1;
                // Notify listeners.
                this.notifyAllModelListener();
            }
        }
    }

    public void drawAll(Canvas c) {
        for (IShape s : bubbles)
            s.draw(c);
    }

    public void clearAll() {
        bubbles.clear();
        this.notifyAllModelListener();
    }

    private void notifyAllModelListener() {
        for (IModelListener m : listeners) {
            m.notifyModelListener();
        }
    }
}
