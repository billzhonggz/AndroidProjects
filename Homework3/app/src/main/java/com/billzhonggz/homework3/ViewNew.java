package com.billzhonggz.homework3;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/**
 * Homework Assignment 3
 * Mobile Application Development
 * 1430003045 Junru Zhong (Bill)
 * May 2nd, 2017
 */

public class ViewNew extends Button implements IModelListener {
    private Model m;
    private ControllerNew c;

    public ViewNew(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setMVC(final Model m, final ControllerNew c) {
        this.m = m;
        this.c = c;
        m.addListener(this);

        // Response to click.
        // Add new snake by calling method in controller.
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = (MainActivity)view.getContext();
                ViewSnakes viewSnakes = (ViewSnakes) mainActivity.findViewById(R.id.view_snakes);
                c.newSnake(viewSnakes.getWidth(), viewSnakes.getHeight());
            }
        });
    }

    @Override
    public void notifyModelListener() {
        // Do nothing.
    }
}