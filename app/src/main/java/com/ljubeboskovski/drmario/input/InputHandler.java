package com.ljubeboskovski.drmario.input;

import android.view.MotionEvent;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.game.Game;

import java.util.ArrayList;

public class InputHandler {

    private final Game game;
    private ArrayList<Button> buttonsControl = new ArrayList<Button>();

    public InputHandler(Game game) {
        this.game = game;
    }

    public void createButtons () {
        Button buttonLeft = new Button(2, 1, 0, Global.Model.BUTTON_MOVE_LEFT);
        buttonsControl.add(buttonLeft);
    }

    public void onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchOnWorld(event);
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
    }

    public void update() {
        for (Button button : buttonsControl) {
            button.update();
        }
    }

    public void touchOnWorld(MotionEvent event){
        float x = event.getX()/ Global.DISPLAY_WIDTH;
        float y = event.getY()/Global.DISPLAY_HEIGHT;

        if (y > 0.5) {
            if (x < 0.4) {
                game.controlLeft();
            } else if (x > 0.6) {
                game.controlRight();
            } else {
                game.controlDown();
            }
        } else {
            if (x < 0.5) {
                game.controlCounterClockwise();
            } else {
                game.controlClockwise();
            }

        }
    }

    public ArrayList<Button> getButtonsControl() {
        return buttonsControl;
    }
}
