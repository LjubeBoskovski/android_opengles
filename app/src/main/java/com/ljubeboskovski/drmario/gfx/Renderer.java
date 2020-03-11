package com.ljubeboskovski.drmario.gfx;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Color;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.R;
import com.ljubeboskovski.drmario.game.entity.TexturedBlock;
import com.ljubeboskovski.drmario.gfx.shader.TextureShader;
import com.ljubeboskovski.drmario.gfx.shader.ColorShader;
import com.ljubeboskovski.drmario.gfx.model.RawModel;
import com.ljubeboskovski.drmario.gfx.texture.ModelTexture;
import com.ljubeboskovski.drmario.gfx.model.TexturedModel;
import com.ljubeboskovski.drmario.game.Game;
import com.ljubeboskovski.drmario.gfx.texture.TextureMap;


public class Renderer implements GLSurfaceView.Renderer {

    private Context context;

    private TextureShader textureShader;
    private Loader loader;
    private Camera camera;
    private Game game;

    private TextureMap textureMap;

    private TexturedBlock textureBlock;

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

//        for (Block block : game.getWorld().getBlocks()) {
//            block.setModel(loader.loadToVAO(block.getVertices(), block.getIndices()));
//        }

        textureBlock = new TexturedBlock(1, 1, Global.BLOCK_COLOR.BLUE, textureMap);
        RawModel model = loader.loadToVAO(textureBlock.getVertices(), Global.QuadForm.indices);
        TexturedModel texturedModel = new TexturedModel(model, textureMap.getTexture());
        textureBlock.setModel(texturedModel);
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
        game.update();

        drawTexture();
    }

    private void drawTexture(){
        textureBlock.update(1.0f);
        textureShader.start();

        loader.bindBuffers(textureBlock.getModel().getRawModel());
        camera.projectModel(textureBlock.getmMatrix());
        textureShader.bindAttributes(camera, textureBlock.getModel().getTexture());


        GLES30.glDrawElements(GLES30.GL_TRIANGLES,
                textureBlock.getModel().getRawModel().getIndexSize(),
                GLES30.GL_UNSIGNED_SHORT, 0);

        textureShader.unbindAttributes();
        loader.unbindBuffers();

        textureShader.stop();
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
