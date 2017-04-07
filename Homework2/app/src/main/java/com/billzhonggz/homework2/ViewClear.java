package com.billzhonggz.homework2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/**
 * Created by ZHONG on 2017/4/7.
 */
public class ViewClear extends Button implements IModelListener {
    private Model model;
    private ControllerClear controller;

    public ViewClear(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setMVC(Model md, ControllerClear ct) {
        this.model = md;
        this.controller = ct;
        md.addListener(this);
        notifyModelListener();
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.clearAll();
            }
        });
    }

    @Override
    public void notifyModelListener() {
        // Nothing.
    }
}
