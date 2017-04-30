package com.billzhonggz.homework3;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ZHONG on 2017/4/30.
 */
public class ViewSnakes extends View implements IModelListener{
    private Model m;
    private ControllerSnakes c;

    public ViewSnakes(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setMVC(Model m, ControllerSnakes c) {
        this.m = m;
        this.c = c;
        m.addListener(this);
    }

    @Override
    public void notifyModelListener() {
        this.post(new Runnable() {
            @Override
            public void run() {
                ViewSnakes viewSnakes = (ViewSnakes)findViewById(R.id.view_snakes);
                viewSnakes.invalidate();
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        m.drawAll(canvas);
    }
}
