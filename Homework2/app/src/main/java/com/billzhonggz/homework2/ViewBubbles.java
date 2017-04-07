package com.billzhonggz.homework2;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Homework Assignment 2
 * Mobile Application Development
 * 1430003045 Junru Zhong (Bill)
 */

public class ViewBubbles extends View implements IModelListener{
    private Model model;
    private ControllerBubbles controller;
    private Handler h;
    private long delay = 500;

    public ViewBubbles(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setMVC(Model md, ControllerBubbles ct) {
        this.model = md;
        this.controller = ct;
        model.addListener(this);
        notifyModelListener();
        h = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                controller.moveUp(getWidth(), getHeight());
                Message m = h.obtainMessage(0);
                h.sendMessageDelayed(m, delay);
                return false;
            }
        });
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    controller.clickBubbles(motionEvent.getX(), motionEvent.getY());
                }
                return true;
            }
        });
    }

    @Override
    public void notifyModelListener() {
        invalidate();
    }

    @Override
    public void onDraw(Canvas c) {
        model.drawAll(c);
    }

    public void startTicker() {
        Message m = h.obtainMessage(0);
        h.sendMessageDelayed(m,delay);
    }

    public void stopTicker() {
        h.removeMessages(0);
    }
}
