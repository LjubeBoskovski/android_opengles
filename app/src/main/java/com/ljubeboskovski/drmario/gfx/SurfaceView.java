package com.ljubeboskovski.drmario.gfx;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class SurfaceView extends GLSurfaceView {

    private final com.ljubeboskovski.drmario.gfx.Renderer renderer;

    public SurfaceView(Context context){
        super(context);

        // Create an OpenGL ES 3.0 context
        setEGLContextClientVersion(3);

        renderer = new com.ljubeboskovski.drmario.gfx.Renderer(context);

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(renderer);

        // Render the view only when there is a change in the drawing data
        // setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
}