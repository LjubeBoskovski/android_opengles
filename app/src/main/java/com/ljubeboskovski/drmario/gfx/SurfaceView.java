package com.ljubeboskovski.drmario.gfx;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.renderscript.ScriptGroup;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.ljubeboskovski.drmario.input.InputHandler;

public class SurfaceView extends GLSurfaceView {

    private InputHandler inputHandler;

    public SurfaceView(Context context) {
        super(context);
    }

    public SurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // Hides superclass method.
    public void setRenderer(com.ljubeboskovski.drmario.gfx.Renderer renderer) {
        super.setRenderer(renderer);
    }

    public void setInputHandler(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event != null && inputHandler != null) {
            inputHandler.onTouchEvent(event);
            return true;
        } else {
            return super.onTouchEvent(event);
        }
    }
}