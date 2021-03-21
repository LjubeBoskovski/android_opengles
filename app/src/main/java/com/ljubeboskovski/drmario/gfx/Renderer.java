package com.ljubeboskovski.drmario.gfx;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.R;
import com.ljubeboskovski.drmario.game.entity.Pill;
import com.ljubeboskovski.drmario.game.entity.Virus;
import com.ljubeboskovski.drmario.game.entity.block.Block;
import com.ljubeboskovski.drmario.gfx.model.TexturedModel;
import com.ljubeboskovski.drmario.gfx.shader.ShaderProgram;
import com.ljubeboskovski.drmario.gfx.shader.TextureShader;
import com.ljubeboskovski.drmario.game.Game;
import com.ljubeboskovski.drmario.gfx.texture.TextureMap;

import java.util.concurrent.locks.ReadWriteLock;


public class Renderer implements GLSurfaceView.Renderer {

    private final Context context;
    private final ReadWriteLock lock;

    private TextureShader textureShader;
    private Loader loader;
    private Camera camera;
    private Game game;

    private TextureMap textureMap;

    public Renderer(Context context, ReadWriteLock lock) {
        this.context = context;
        this.lock = lock;
    }

    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        initGL();

        textureShader = new TextureShader(context);
        loader = new Loader(context);
        game.setLoader(loader);
        camera = new Camera();

        textureMap = new TextureMap(8, loader.loadTexture(R.drawable.blocks_spritemap));

        lock.readLock().lock();
        try {
            Global.MODEL.initWorldTextures(loader, textureMap);
            game.createWorld();
        } finally {
            lock.readLock().unlock();
        }
        game.start();
    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES30.glViewport(0, 0, width, height);
    }

    public void onDrawFrame(GL10 unused) {
        // Redraw background color
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT | GLES30.GL_DEPTH_BUFFER_BIT);
        lock.writeLock().lock();
        try{
            game.update();
        } finally {
            lock.writeLock().unlock();
        }

        lock.readLock().lock();
        try {
            draw();
        } finally {
            lock.readLock().unlock();
        }
    }


    private void draw() {
        textureShader.start();

        for(Block block : game.getWorld().getSingleBlocks()) {
            draw(textureShader, loader, camera, block.getmMatrix(), block.getModel());
        }
        for(Virus virus : game.getWorld().getViruses()) {
            draw(textureShader, loader, camera, virus.getmMatrix(), virus.getModel());
        }
        for(Pill pill : game.getWorld().getPills()) {
            draw(textureShader, loader, camera, pill.getBlockNorth().getmMatrix(),
                    pill.getBlockNorth().getModel());
            draw(textureShader, loader, camera, pill.getBlockSouth().getmMatrix(),
                    pill.getBlockSouth().getModel());
        }
        Pill pill = game.getControlledPill();
        if(pill != null) {
            draw(textureShader, loader, camera, pill.getBlockNorth().getmMatrix(),
                    pill.getBlockNorth().getModel());
            draw(textureShader, loader, camera, pill.getBlockSouth().getmMatrix(),
                    pill.getBlockSouth().getModel());
        }

        textureShader.stop();
    }

    private void draw(ShaderProgram shader, Loader loader, Camera camera,
                      float[] mMatrix, TexturedModel model) {
        loader.bindBuffers(model.getRawModel());
        camera.projectModel(mMatrix);
        shader.bindAttributes(camera, model.getTexture());


        GLES30.glDrawElements(GLES30.GL_TRIANGLES, model.getRawModel().getIndexSize(),
                GLES30.GL_UNSIGNED_SHORT, 0);

        shader.unbindAttributes();
        loader.unbindBuffers();
    }

    private void initGL() {
        // Set the background frame color
        GLES30.glClearColor(0.2f, 0.0f, 0.1f, 1.0f);

        // Use culling to remove back faces.
        GLES30.glEnable(GLES30.GL_CULL_FACE);

        // Enable depth testing
        GLES30.glEnable(GLES30.GL_DEPTH_TEST);

        // Enable alpha blending
        GLES30.glEnable(GLES30.GL_BLEND);
        GLES30.glBlendFunc(GLES30.GL_SRC_ALPHA, GLES30.GL_ONE_MINUS_SRC_ALPHA);
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
