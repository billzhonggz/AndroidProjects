package com.billzhonggz.homework2;

/**
 * Created by ZHONG on 2017/4/7.
 */
public class ControllerClear {
    private Model model;

    public ControllerClear(Model model) {
        this.model = model;
    }

    public void clearAll() {
        model.clearAll();
    }
}
