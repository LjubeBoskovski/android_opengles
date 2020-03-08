package com.ljubeboskovski.drmario.gfx;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.os.SystemClock;
import android.util.Log;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.game.Game;
import com.ljubeboskovski.drmario.game.entity.Block;
import com.ljubeboskovski.drmario.game.world.World;
import com.ljubeboskovski.drmario.gfx.shader.ColorShader;

public class Renderer implements GLSurfaceView.Renderer {

    private Context context;
    private ColorShader shader;
    private Camera camera;
    private Game game;

    public Renderer(Context context) {
        this.context = context;
    }

    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        // Set the background frame color
        GLES30.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        // Use culling to remove back faces.
        GLES30.glEnable(GLES30.GL_CULL_FACE);

        // Enable depth testing
        GLES30.glEnable(GLES30.GL_DEPTH_TEST);


        shader = new ColorShader(context);
        camera = new Camera();

        Loader.loadToVAO(game.getWorld());

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

        game.update();

        for (Block block : game.getWorld().getBlocks()) {
            Loader.bindBuffers(block.getModel());
            camera.projectModel(block.getmMatrix());

            shader.bindAttributes(camera);
            GLES30.glDrawElements(GLES30.GL_TRIANGLES, block.getModel().getIndexSize(),
                    GLES30.GL_UNSIGNED_SHORT, 0);
        }

        shader.unbindAttributes();
        Loader.unbindBuffers();

        shader.stop();
    }

    public void setGame(Game game) {
        this.game = game;
    }

}