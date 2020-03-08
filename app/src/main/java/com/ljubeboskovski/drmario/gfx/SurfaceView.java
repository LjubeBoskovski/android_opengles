package com.ljubeboskovski.drmario.gfx;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class SurfaceView extends GLSurfaceView {

    private com.ljubeboskovski.drmario.gfx.Renderer renderer;

    private float density;
    private int widthPixels, heightPixels;

    float x, y, previousX, previousY, deltaX, deltaY;

    public SurfaceView(Context context) {
        super(context);
    }

    public SurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // Hides superclass method.
    public void setRenderer(com.ljubeboskovski.drmario.gfx.Renderer renderer,
                            float density, int widthPixels,
                            int heightPixels) {
        this.renderer = renderer;
        this.density = density;
        this.widthPixels = widthPixels;
        this.heightPixels = heightPixels;
        super.setRenderer(renderer);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event != null) {
            float x = event.getX();
            float y = event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    if (renderer != null) {
                        deltaX = (x - previousX) / density / 2f;
                        deltaY = (y - previousY) / density / 2f;

                        renderer.mDeltaX += deltaX;
                        renderer.mDeltaY += deltaY;
                    }
                    break;
                case MotionEvent.ACTION_DOWN:
                    if (renderer != null) {
                        renderer.touch(x/widthPixels, y/heightPixels);
                        Log.println(Log.WARN, "Touch", Float.toString(y/heightPixels));
                    }
            }

            previousX = x;
            previousY = y;

            return true;
        } else {
            return super.onTouchEvent(event);
        }

    }
}