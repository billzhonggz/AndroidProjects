package com.billzhonggz.homework2;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Homework Assignment 2
 * Mobile Application Development
 * 1430003045 Junru Zhong (Bill)
 */

public class ViewScore extends TextView implements IModelListener{
    private Model model;
    private ControllerScore controller;

    public ViewScore(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setMVC(Model md, ControllerScore ct) {
        this.model = md;
        this.controller = ct;
        model.addListener(this);
        notifyModelListener();
    }

    @Override
    public void notifyModelListener() {
        this.setText(Integer.toString(model.getScore()));
    }
}
