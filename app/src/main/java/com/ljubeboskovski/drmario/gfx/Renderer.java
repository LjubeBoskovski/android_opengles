package com.ljubeboskovski.drmario.gfx;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.R;
import com.ljubeboskovski.drmario.game.Game;
import com.ljubeboskovski.drmario.game.entity.Block;
//<<<<<<< Updated upstream
import com.ljubeboskovski.drmario.gfx.shader.ColorShader;
//=======
import com.ljubeboskovski.drmario.gfx.model.RawModel;
import com.ljubeboskovski.drmario.gfx.model.TexturedModel;
import com.ljubeboskovski.drmario.gfx.shader.ColorShader;
import com.ljubeboskovski.drmario.gfx.shader.TextureShader;
import com.ljubeboskovski.drmario.gfx.texture.ModelTexture;
//>>>>>>> Stashed changes

public class Renderer implements GLSurfaceView.Renderer {

    private Context context;
//<<<<<<< Updated upstream
    private ColorShader shader;
//=======
//    private TextureShader textureShader;
//    private ColorShader colorShader;
    private Loader loader;
//>>>>>>> Stashed changes
    private Camera camera;
    private Game game;


    private Block colorBlock;
    private Block textureBlock;
    private TexturedModel texturedModel;

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


//<<<<<<< Updated upstream
        shader = new ColorShader(context);
//=======
//        textureShader = new TextureShader(context);
//        colorShader = new ColorShader(context);
        loader = new Loader(context);
//>>>>>>> Stashed changes
        camera = new Camera();


//        for (Block block : game.getWorld().getBlocks()) {
//            block.setModel(loader.loadToVAO(block.getVertices(), block.getIndices()));
//        }

        textureBlock = new Block(0, 0, Global.BlockColor.BLUE);
        RawModel model = loader.loadToVAO(textureBlock.getVertices(), textureBlock.getIndices());
//        ModelTexture texture = new ModelTexture(loader.loadTexture(R.drawable.blocks_spritemap));
//        texturedModel = new TexturedModel(model, texture);
//        textureBlock.setModel(texturedModel);

        textureBlock.setModel(model);

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

        drawColor();
    }

    private void drawColor(){
        shader.start();

//        for (Block block : game.getWorld().getBlocks()) {
        loader.bindBuffers(textureBlock.getModel());
        camera.projectModel(textureBlock.getmMatrix());

        shader.bindAttributes(camera);
        GLES30.glDrawElements(GLES30.GL_TRIANGLES,
                textureBlock.getModel().getIndexSize(),
                GLES30.GL_UNSIGNED_SHORT, 0);

        shader.unbindAttributes();
        loader.unbindBuffers();
//        }

        shader.stop();
    }

//    private void drawTexture(){
//        textureShader.start();
////        for (Block block : game.getWorld().getBlocks()) {
//        loader.bindBuffers(textureBlock.getModel());
//        camera.projectModel(textureBlock.getmMatrix());
//
//        textureShader.bindAttributes(camera);
//        GLES30.glDrawElements(GLES30.GL_TRIANGLES,
//                textureBlock.getModel().getRawModel().getIndexSize(),
//                GLES30.GL_UNSIGNED_SHORT, 0);
//
//        textureShader.unbindAttributes();
//        loader.unbindBuffers();
////        }
//
//        textureShader.stop();
//    }

    public void setGame(Game game) {
        this.game = game;
    }

}