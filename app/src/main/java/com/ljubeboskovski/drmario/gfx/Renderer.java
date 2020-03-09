package com.ljubeboskovski.drmario.gfx;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
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
import com.ljubeboskovski.drmario.game.entity.Block;
import com.ljubeboskovski.drmario.game.Game;


public class Renderer implements GLSurfaceView.Renderer {

    private Context context;
    private ColorShader colorShader;
    private TextureShader textureShader;
    private Loader loader;
    private Camera camera;
    private Game game;


    private Block colorBlock;
    private TexturedBlock textureBlock;

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


//        colorShader = new ColorShader(context);
        textureShader = new TextureShader(context);
        loader = new Loader(context);
        camera = new Camera();


//        for (Block block : game.getWorld().getBlocks()) {
//            block.setModel(loader.loadToVAO(block.getVertices(), block.getIndices()));
//        }

        colorBlock = new Block(0, 0, Global.BlockColor.BLUE);
        RawModel model = loader.loadToVAO(colorBlock.getVertices(), colorBlock.getIndices());
        colorBlock.setModel(model);

        textureBlock = new TexturedBlock(3, 3, Global.BlockColor.BLUE);
        ModelTexture texture = new ModelTexture(loader.loadTexture(R.drawable.blocks_spritemap));
        TexturedModel texturedModel = new TexturedModel(model, texture);
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

//        drawColor();
        drawTexture();
    }

    private void drawColor(){
        colorBlock.update(1.0f);
        colorShader.start();

//        for (Block block : game.getWorld().getBlocks()) {
        loader.bindBuffers(colorBlock.getModel());
        camera.projectModel(colorBlock.getmMatrix());

        colorShader.bindAttributes(camera);
        GLES30.glDrawElements(GLES30.GL_TRIANGLES,
                colorBlock.getModel().getIndexSize(),
                GLES30.GL_UNSIGNED_SHORT, 0);

        colorShader.unbindAttributes();
        loader.unbindBuffers();
//        }

        colorShader.stop();
    }

    private void drawTexture(){
        textureBlock.update(1.0f);
        textureShader.start();

//        for (Block block : game.getWorld().getBlocks()) {
        loader.bindBuffers(textureBlock.getModel());
        camera.projectModel(textureBlock.getmMatrix());

        textureShader.bindAttributes(camera);
        GLES30.glDrawElements(GLES30.GL_TRIANGLES,
                textureBlock.getModel().getRawModel().getIndexSize(),
                GLES30.GL_UNSIGNED_SHORT, 0);

        textureShader.unbindAttributes();
        loader.unbindBuffers();
//        }

        textureShader.stop();
    }

    public void setGame(Game game) {
        this.game = game;
    }

}