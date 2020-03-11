package com.ljubeboskovski.drmario.gfx;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.os.SystemClock;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.R;
import com.ljubeboskovski.drmario.game.entity.Pill;
import com.ljubeboskovski.drmario.game.entity.block.Block;
import com.ljubeboskovski.drmario.gfx.model.RawModel;
import com.ljubeboskovski.drmario.gfx.model.TexturedModel;
import com.ljubeboskovski.drmario.gfx.shader.ShaderProgram;
import com.ljubeboskovski.drmario.gfx.shader.TextureShader;
import com.ljubeboskovski.drmario.gfx.texture.ModelTexture;
import com.ljubeboskovski.drmario.game.Game;
import com.ljubeboskovski.drmario.gfx.texture.TextureMap;


public class Renderer implements GLSurfaceView.Renderer {

    private Context context;

    private TextureShader textureShader;
    private Loader loader;
    private Camera camera;
    private Game game;

    private TextureMap textureMap;

    private Pill pill;

    public Renderer(Context context) {
        this.context = context;
    }

    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        initGL();

        textureShader = new TextureShader(context);
        loader = new Loader(context);
        camera = new Camera();

        ModelTexture texture = new ModelTexture(loader.loadTexture(R.drawable.blocks_spritemap));
        textureMap = new TextureMap(8, texture);

        for (Block block : game.getWorld().getBlocks()) {
            loader.loadToVAO(block, textureMap);
        }

        pill = new Pill(3, 3, Global.BLOCK_COLOR.RED, Global.BLOCK_COLOR.YELLOW);
        loader.loadToVAO(pill.getBlockNorth(), textureMap);
        loader.loadToVAO(pill.getBlockSouth(), textureMap);
    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES30.glViewport(0, 0, width, height);
    }

    public void onDrawFrame(GL10 unused) {
        // Redraw background color
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT | GLES30.GL_DEPTH_BUFFER_BIT);
        game.update();

        long time = SystemClock.uptimeMillis() % 1000L;
        float angleInDegrees = (360.0f / 1000.0f) * ((int) time);
        float scale = (float) (0.3f * Math.sin((float)time/1000.0f * 2.0f * Math.PI) + 1.3f);

        pill.update(pill.getX(), pill.getY(), 2.0f, angleInDegrees, scale);
        draw();
    }


    private void draw() {
        textureShader.start();
        for(Block block : game.getWorld().getBlocks()) {
            draw(textureShader, loader, camera, block.getmMatrix(), block.getModel());
        }
        draw(textureShader, loader, camera, pill.getBlockNorth().getmMatrix(),
                pill.getBlockNorth().getModel());
        draw(textureShader, loader, camera, pill.getBlockSouth().getmMatrix(),
                pill.getBlockSouth().getModel());
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


//        GLES30.glGenerateMipmap(GLES30.GL_TEXTURE_2D);

//        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, textureDataHandle);
//        GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MAG_FILTER, GLES30.GL_LINEAR);
//
//        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, textureDataHandle);
//        GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MIN_FILTER, GLES30.GL_LINEAR_MIPMAP_LINEAR);
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
