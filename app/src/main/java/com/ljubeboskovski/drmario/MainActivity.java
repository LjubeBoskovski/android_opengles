package com.ljubeboskovski.drmario;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.ljubeboskovski.drmario.gfx.SurfaceView;

public class MainActivity extends Activity {

    private SurfaceView surfaceView;
    private  com.ljubeboskovski.drmario.gfx.Renderer renderer;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity.
        surfaceView = new SurfaceView(this);
        setContentView(surfaceView);

        // Create an OpenGL ES 3.0 context
        surfaceView.setEGLContextClientVersion(3);

        renderer = new com.ljubeboskovski.drmario.gfx.Renderer(this);

        // Set the Renderer for drawing on the GLSurfaceView
        surfaceView.setRenderer(renderer, 1.0f);

        // Render the view only when there is a change in the drawing data
        // setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    @Override
    protected void onResume() {
        // The activity must call the GL surface view's onResume() on activity
        // onResume().
        super.onResume();
        surfaceView.onResume();
    }

    @Override
    protected void onPause() {
        // The activity must call the GL surface view's onPause() on activity
        // onPause().
        super.onPause();
        surfaceView.onPause();
    }

}
