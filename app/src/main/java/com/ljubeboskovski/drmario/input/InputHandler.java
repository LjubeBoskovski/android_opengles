package com.ljubeboskovski.drmario.input;

import android.opengl.Matrix;
import android.util.Log;
import android.view.MotionEvent;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.game.Game;
import com.ljubeboskovski.drmario.gfx.Renderer;

import java.util.ArrayList;
import java.util.concurrent.locks.ReadWriteLock;

public class InputHandler {

    private final Game game;
    private final Renderer renderer;
    private final ReadWriteLock lock;

    private ArrayList<Button> buttonsControl = new ArrayList<Button>();

    public InputHandler(Game game, Renderer renderer, ReadWriteLock lock) {
        this.game = game;
        this.renderer = renderer;
        this.lock = lock;
    }

    public void createButtons () {
        buttonsControl.add(new Button(1, 1, 0, Global.Model.BUTTON_MOVE_LEFT, game::controlLeft));
        buttonsControl.add(new Button(3.5f, 1, 0, Global.Model.BUTTON_MOVE_DOWN,
                game::controlDown));
        buttonsControl.add(new Button(6, 1, 0, Global.Model.BUTTON_MOVE_RIGHT, game::controlRight));
        buttonsControl.add(new Button(1.5f, 3, 0, Global.Model.BUTTON_ROTATE_COUNTERCLOCKWISE,
                game::controlCounterClockwise));
        buttonsControl.add(new Button(5.5f, 3, 0, Global.Model.BUTTON_ROTATE_CLOCKWISE,
                game::controlClockwise));
    }

    public void onTouchEvent(MotionEvent event) {
        lock.writeLock().lock();
        try {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touchOnControls(event);
                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_UP:
                    for (Button button : buttonsControl) {
                        button.unpress();
                    }
                    break;
                default:
                    break;
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void update() {
        for (Button button : buttonsControl) {
            button.update();
        }
    }

    public void tick() {
        for (Button button : buttonsControl) {
            button.tick();
        }
    }

    public void touchOnControls(MotionEvent event) {
        // Get coordinates on button camera view
        float x = (2.0f * event.getX())/ Global.DISPLAY_WIDTH - 1f;
        float y = (-2.0f * event.getY())/Global.DISPLAY_HEIGHT + 1f;
        float[] eyeCoordinates = getEyeCoordinates(x, y);

        // Search for a button in that location, check "hitbox"
        for(Button button : buttonsControl) {
            if (eyeCoordinates[0] >= button.getX() - 0.5f &&
                    eyeCoordinates[0] < button.getX() + 1.5f &&
                    eyeCoordinates[1] >= button.getY() - 1.5f &&
                    eyeCoordinates[1] < button.getY() - 0.5f) {
                button.press();
                return;
            }
        }
    }

    float[] getEyeCoordinates(float displayX, float displayY) {
        // Display coordinates -> normalized device coordinates
        float[] clipCoordinates = {displayX, displayY, -1.0f, 1.0f};
//        Log.println(Log.DEBUG, "clipCoordinates",
//                "[" + clipCoordinates[0] + ", " + clipCoordinates[1] + ", " + clipCoordinates[2] + ", " + clipCoordinates[3] + "]");

        // Normalized device coordinates -> eye coordinates
        float[] invertedProjection = new float[16];
        Matrix.invertM(invertedProjection, 0, renderer.getCameraButtons().pMatrix, 0);
        float[] eyeCoordinates = new float[4];
        Matrix.multiplyMV(eyeCoordinates, 0, invertedProjection, 0, clipCoordinates, 0);
//        eyeCoordinates[2] = -1.0f;
//        eyeCoordinates[3] = 0.0f;
//        Log.println(Log.DEBUG, "eyeCoordinates",
//                "[" + eyeCoordinates[0] + ", " + eyeCoordinates[1] + ", " + eyeCoordinates[2] + ", " + eyeCoordinates[3] + "]");

//        // Eye coordinates -> world coordinates
//        float[] invertedView = new float[16];
//        Matrix.invertM(invertedView, 0, renderer.getCameraButtons().vMatrix, 0);
//        float[] rayWorld = new float[4];
//        Matrix.multiplyMV(rayWorld, 0, invertedView, 0, eyeCoordinates, 0);
//
//        Log.println(Log.DEBUG, "rayWorld",
//                "[" + rayWorld[0] + ", " + rayWorld[1] + ", " + rayWorld[2] + ", " + rayWorld[3] + "]");
        return eyeCoordinates;
    }

    public ArrayList<Button> getButtonsControl() {
        return buttonsControl;
    }
}
