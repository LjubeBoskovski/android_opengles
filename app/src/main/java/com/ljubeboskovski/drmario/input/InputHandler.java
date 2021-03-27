package com.ljubeboskovski.drmario.input;

import android.view.MotionEvent;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.game.Game;

public class InputHandler {
    private final Game game;

    public InputHandler(Game game) {
        this.game = game;
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
}
