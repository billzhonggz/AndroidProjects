package com.billzhonggz.homework2;

/**
 * Homework Assignment 2
 * Mobile Application Development
 * 1430003045 Junru Zhong (Bill)
 */

public class ControllerBubbles {
    private Model model;

    public ControllerBubbles(Model model) {
        this.model = model;
    }

    public void moveUp(int w, int h) {
        model.moveAll(0, -1);
        model.clearInvisibles(w, h);
        model.addBubble(w, h);
    }

    public void clickBubbles(float x, float y) {
        model.deleteBubblesAtPoint(x, y);
    }
}
