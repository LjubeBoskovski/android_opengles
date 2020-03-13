package com.ljubeboskovski.drmario.input;

import android.view.MotionEvent;

import com.ljubeboskovski.drmario.game.Game;

public class InputHandler {
    private Game game;

    private static float density;
    private static int widthPixels, heightPixels;

//    private static float x, y, previousX, previousY, deltaX, deltaY;

    public InputHandler(Game game, float density, int widthPixels, int heightPixels) {
        this.game = game;
        this.density = density;
        this.widthPixels = widthPixels;
        this.heightPixels = heightPixels;
    }

    public void onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchOnWorld(event);
            case MotionEvent.ACTION_MOVE:
//                deltaX = (x - previousX) / density / 2f;
//                deltaY = (y - previousY) / density / 2f;
//                    renderer.mDeltaX += deltaX;
//                    renderer.mDeltaY += deltaY;
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
    }


    public void touchOnWorld(MotionEvent event){
        float x = event.getX()/widthPixels;
        float y = event.getY()/heightPixels;

//        float xOnWorld = x * (float)game.getWorld().getSizeX();
//        float yOnWorld = -1.0f * y * (float)game.getWorld().getSizeY() + (float)game.getWorld().getSizeY();

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
                game.controlCounterClockwise();
            }

        }
    }
}
