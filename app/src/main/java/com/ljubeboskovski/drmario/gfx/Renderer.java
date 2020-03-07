package com.ljubeboskovski.drmario.gfx;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.SystemClock;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.game.entity.Block;
import com.ljubeboskovski.drmario.gfx.model.RawModel;
import com.ljubeboskovski.drmario.gfx.shader.StaticShader;

public class Renderer implements GLSurfaceView.Renderer {


    private Context context;

    private Loader loader;
    private StaticShader shader;
    private Camera camera;

    private Block block;


    public Renderer(Context context) {
        this.context = context;
    }

    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        // Set the background frame color
        GLES30.glClearColor(1.0f, 0.0f, 1.0f, 1.0f);

        // Use culling to remove back faces.
        GLES30.glEnable(GLES30.GL_CULL_FACE);

        // Enable depth testing
        GLES30.glEnable(GLES30.GL_DEPTH_TEST);


        loader = new Loader();
        shader = new StaticShader(context);
        camera = new Camera();

        block = new Block(0, 2, Global.BlockColor.RED);

        Loader.loadToVAO(block);

        //ModelTexture texture = new ModelTexture(loader.loadTexture("sprites" +
        //		"/blocks_spritemap"));
        //TexturedModel texturedModel = new TexturedModel(model, texture);
    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES30.glViewport(0, 0, width, height);
    }

    public void onDrawFrame(GL10 unused) {
        // Redraw background color
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT | GLES30.GL_DEPTH_BUFFER_BIT);
        draw();
    }


    private void draw() {
        shader.start();

        long time = SystemClock.uptimeMillis() % 1000L;
        float angleInDegrees = (360.0f / 1000.0f) * ((int) time);

        block.update(angleInDegrees);
        camera.projectModel(block.getmMatrix());

        Loader.bindBuffers(block.getModel());
        shader.bindAttributes(camera);

        GLES30.glDrawElements(GLES30.GL_TRIANGLES, block.getModel().getIndexSize(),
                GLES30.GL_UNSIGNED_SHORT, 0);

        shader.unbindAttributes();
        Loader.unbindBuffers();

        shader.stop();
    }


}