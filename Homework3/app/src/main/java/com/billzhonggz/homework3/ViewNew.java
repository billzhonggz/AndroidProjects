package com.billzhonggz.homework3;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/**
 * Created by ZHONG on 2017/4/30.
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
