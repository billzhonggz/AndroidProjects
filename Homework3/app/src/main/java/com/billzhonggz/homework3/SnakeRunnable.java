package com.billzhonggz.homework3;


/**
 * Homework Assignment 3
 * Mobile Application Development
 * 1430003045 Junru Zhong (Bill)
 * May 2nd, 2017
 */

public class SnakeRunnable implements Runnable {
    private Model m;
    private int sid;
    private long delay;
    private int w;
    private int h;

    public SnakeRunnable(Model m, int sid, int w, int h) {
        this.m = m;
        this.sid = sid;
        this.w = w;
        this.h = h;
        delay = 100 + (long) (Math.random() * 2000);
        m.addSnake(sid, (int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
        for (int i = 0; i < 5; i++) {
            m.addPoint(sid, (int) (Math.random() * w), (int) (Math.random() * h));
        }
    }

    @Override
    public void run() {
        while (true) {
            // Delete oldest point.
            m.deleteLastPoint(sid);
            // Add new point.
            m.addPoint(sid, (int) (Math.random() * w), (int) (Math.random() * h));
            // Sleep.
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}