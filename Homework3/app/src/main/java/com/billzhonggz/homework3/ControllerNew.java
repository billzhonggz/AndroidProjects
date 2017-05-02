package com.billzhonggz.homework3;

import java.util.ArrayList;

/**
 * Homework Assignment 3
 * Mobile Application Development
 * 1430003045 Junru Zhong (Bill)
 * May 2nd, 2017
 */

public class ControllerNew {
    private Model m;
    private ArrayList<Thread> threads = new ArrayList<Thread>();

    public ControllerNew(Model m) {
        this.m = m;
    }

    public void newSnake(int w, int h) {
        // Initialize threads.
        SnakeRunnable snakeRunnable = new SnakeRunnable(m, threads.size(), w, h);
        Thread t1 = new Thread(snakeRunnable, "t1");
        threads.add(t1);
        t1.start();
    }
}