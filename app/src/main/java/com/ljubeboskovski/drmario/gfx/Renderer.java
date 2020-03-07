package com.ljubeboskovski.drmario.gfx;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.SystemClock;

import com.ljubeboskovski.drmario.gfx.model.RawModel;
import com.ljubeboskovski.drmario.gfx.shader.StaticShader;

public class Renderer implements GLSurfaceView.Renderer {


    private Context context;

    private Loader loader;
    private StaticShader shader;
    private Camera camera;

    private RawModel model;





    /**
     * Store the model matrix. This matrix is used to move models from object space (where each model can be thought
     * of being located at the center of the universe) to world space.
     */
    public float[] mMatrix = new float[16];


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


        float[] vertices = {
                -0.5f, -0.5f, 0f,
                0.0f, 0.0f, 1.0f, 1.0f,

                0.5f, -0.5f, 0f,
                1.0f, 0.0f, 1.0f, 1.0f,

                0.5f, 0.5f, 0f,
                1.0f, 1.0f, 1.0f, 1.0f,

                -0.5f, 0.5f, 0f,
                0.0f, 1.0f, 1.0f, 1.0f

        };

        short[] indices = {
                0, 1, 3,
                3, 1, 2
        };
//
//        int smx = 2;
//        int smy = 1;
//        int smsize = 8;
//
//        float[] textureCoords = {
//                ((float) smx) / smsize, ((float) smy + 1) / smsize,
//                ((float) smx + 1) / smsize, ((float) smy + 1) / smsize,
//                ((float) smx + 1) / smsize, ((float) smy) / smsize,
//                ((float) smx) / smsize, ((float) smy) / smsize
//
////			0, 1,
////			1, 1,
////			1, 0,
////			0, 0
//        };

        model = loader.loadColorModelToVAO(vertices, indices);

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


        Matrix.setIdentityM(mMatrix, 0);
        Matrix.translateM(mMatrix, 0, angleInDegrees / 100.0f, angleInDegrees / 200.0f, 0.0f);
        Matrix.rotateM(mMatrix, 0, angleInDegrees, 0.0f, 0.0f, 1.0f);


        camera.projectModel(mMatrix);

        // Bind the VBO and IBO
        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, model.getVaoID());
        GLES30.glBindBuffer(GLES30.GL_ELEMENT_ARRAY_BUFFER, model.getIaoID());

        shader.bindAttributes(camera);
        GLES30.glDrawElements(GLES30.GL_TRIANGLES, model.getIndexSize(), GLES30.GL_UNSIGNED_SHORT, 0);
        shader.unbindAttributes();

        // Unbind the VBO and IBO
        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, 0);
        GLES30.glBindBuffer(GLES30.GL_ELEMENT_ARRAY_BUFFER, 0);

        shader.stop();
    }


}