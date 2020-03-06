package com.ljubeboskovski.drmario.gfx;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class SurfaceView extends GLSurfaceView {

    private  com.ljubeboskovski.drmario.gfx.Renderer renderer;
    private float density;

    public SurfaceView(Context context){
        super(context);
    }

    public SurfaceView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    // Hides superclass method.
    public void setRenderer(com.ljubeboskovski.drmario.gfx.Renderer renderer, float density)
    {
        this.renderer = renderer;
        this.density = density;
        super.setRenderer(renderer);
    }
}