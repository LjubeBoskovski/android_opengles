package com.ljubeboskovski.drmario.gfx;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.util.Log;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.R;
import com.ljubeboskovski.drmario.game.entity.Pill;
import com.ljubeboskovski.drmario.game.entity.Virus;
import com.ljubeboskovski.drmario.game.entity.block.Block;
import com.ljubeboskovski.drmario.game.entity.wall.Wall;
import com.ljubeboskovski.drmario.gfx.model.TexturedModel;
import com.ljubeboskovski.drmario.gfx.shader.ShaderProgram;
import com.ljubeboskovski.drmario.gfx.shader.TextureShader;
import com.ljubeboskovski.drmario.game.Game;
import com.ljubeboskovski.drmario.gfx.texture.TextureMap;
import com.ljubeboskovski.drmario.input.Button;
import com.ljubeboskovski.drmario.input.InputHandler;

import java.util.concurrent.locks.ReadWriteLock;


public class Renderer implements GLSurfaceView.Renderer {

    private final Context context;
    private final ReadWriteLock lock;

    private Game game;
    private InputHandler inputHandler;

    private TextureShader textureShader;
    private Loader loader;
    private Camera cameraWorld;
    private Camera cameraButtons;

    private TextureMap textureMap;

    public Renderer(Context context, ReadWriteLock lock) {
        this.context = context;
        this.lock = lock;
    }

    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        initGL();

        textureShader = new TextureShader(context);
        loader = new Loader(context);

        textureMap = new TextureMap(8, loader.loadTexture(R.drawable.blocks_spritemap));

        float worldLeft = -1f;
        float worldRight = -1 + (float)(Global.WORLD_SIZE_X + 2) * 3f / 2f;
        float worldPixelPerBlock = (float)Global.DISPLAY_WIDTH/(worldRight - worldLeft);
        float worldTop = (float)Global.WORLD_SIZE_Y + 4;
        float worldBottom = worldTop - (float)Global.DISPLAY_HEIGHT/worldPixelPerBlock;
        cameraWorld = new Camera(worldLeft, worldRight, worldBottom, worldTop);

        float buttonsLeft = 0;
        float buttonsRight = Global.BUTTONS_SIZE_X;
        float buttonsPixelPerBlock = (float)Global.DISPLAY_WIDTH/(buttonsRight - buttonsLeft);
        float buttonsBottom = 0f;
        float buttonsTop = (float)Global.DISPLAY_HEIGHT/buttonsPixelPerBlock;
        cameraButtons = new Camera(buttonsLeft, buttonsRight, buttonsBottom, buttonsTop);


        lock.readLock().lock();
        try {
            Global.Model.initWorldTextures(loader, textureMap);
            game.createWorld();
            inputHandler.createButtons();
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
            inputHandler.update();
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

        for(Wall wall : game.getWorld().getWalls()) {
            draw(textureShader, loader, cameraWorld, wall.getmMatrix(), wall.getModel());
        }

        for(Block block : game.getWorld().getSingleBlocks()) {
            draw(textureShader, loader, cameraWorld, block.getmMatrix(), block.getModel());
        }
        for(Virus virus : game.getWorld().getViruses()) {
            draw(textureShader, loader, cameraWorld, virus.getmMatrix(), virus.getModel());
        }
        for(Pill pill : game.getWorld().getPills()) {
            draw(textureShader, loader, cameraWorld, pill.getBlockNorth().getmMatrix(),
                    pill.getBlockNorth().getModel());
            draw(textureShader, loader, cameraWorld, pill.getBlockSouth().getmMatrix(),
                    pill.getBlockSouth().getModel());
        }
        Pill pill = game.getControlledPill();
        if(pill != null) {
            draw(textureShader, loader, cameraWorld, pill.getBlockNorth().getmMatrix(),
                    pill.getBlockNorth().getModel());
            draw(textureShader, loader, cameraWorld, pill.getBlockSouth().getmMatrix(),
                    pill.getBlockSouth().getModel());
        }

        for(Button button : inputHandler.getButtonsControl()) {
            draw(textureShader, loader, cameraButtons, button.getmMatrix(), button.getModel());
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
        GLES30.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

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

    public void setInputHandler(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }
}
