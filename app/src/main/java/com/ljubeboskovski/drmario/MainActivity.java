package com.ljubeboskovski.drmario;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;

import com.ljubeboskovski.drmario.game.Game;
import com.ljubeboskovski.drmario.gfx.Renderer;
import com.ljubeboskovski.drmario.gfx.SurfaceView;
import com.ljubeboskovski.drmario.input.InputHandler;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MainActivity extends Activity {

    private SurfaceView surfaceView;
    private Game game;
    private ReadWriteLock lock;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        surfaceView = new SurfaceView(this);
//        setViewParameters();
        setContentView(surfaceView);

        // Check if the system supports OpenGL ES 3.0.
        final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
        final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x30000;

        if (supportsEs2) {
            // Request an OpenGL ES 3.0 compatible context.
            surfaceView.setEGLContextClientVersion(3);

            // Try to not loose the context after pause/resume
            surfaceView.setPreserveEGLContextOnPause(true);

            final DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

            // Create the read write lock for thread safety
            lock = new ReentrantReadWriteLock();

            // Set the Renderer for drawing on the GLSurfaceView
            com.ljubeboskovski.drmario.gfx.Renderer renderer =
                    new com.ljubeboskovski.drmario.gfx.Renderer(this, lock);
            surfaceView.setRenderer(renderer);

            // Create game
            game = new Game(lock);
            renderer.setGame(game);

            // Set the Input Handler
            InputHandler inputHandler = new InputHandler(game, displayMetrics.density,
                    displayMetrics.widthPixels,
                    displayMetrics.heightPixels);
            surfaceView.setInputHandler(inputHandler);

        } else {
            Log.println(Log.ERROR, "MainActivity", "OpenGLES 3.0 not supported");
            return;
        }
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

    @RequiresApi(api = Build.VERSION_CODES.P)
    private void setViewParameters() {
        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity.
        surfaceView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setNavigationBarColor(Color.TRANSPARENT);


        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        getWindow().setAttributes(lp);
    }

}
