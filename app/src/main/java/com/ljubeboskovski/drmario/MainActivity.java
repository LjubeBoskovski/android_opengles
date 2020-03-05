package com.ljubeboskovski.drmario;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.ljubeboskovski.drmario.gfx.SurfaceView;

public class MainActivity extends Activity {

    private GLSurfaceView surfaceView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity.
        surfaceView = new SurfaceView(this);
        setContentView(surfaceView);
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
