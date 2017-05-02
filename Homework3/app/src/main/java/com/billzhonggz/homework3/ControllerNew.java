package com.billzhonggz.homework3;

import java.util.ArrayList;

/**
 * Created by ZHONG on 2017/4/30.
 */
public class ControllerNew {
    private Model m;
    private ArrayList<Thread> threads = new ArrayList<Thread>();

    public ControllerNew(Model m) {
        this.m = m;
    }

    public void newSnake(int w, int h) {
        SnakeRunnable snakeRunnable = new SnakeRunnable(m, threads.size(), w, h);
        Thread t1 = new Thread(snakeRunnable, "t1");
        threads.add(t1);
        t1.start();
    }
}
