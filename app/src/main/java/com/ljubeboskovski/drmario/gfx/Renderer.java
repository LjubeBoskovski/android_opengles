package com.ljubeboskovski.drmario.gfx;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.gfx.model.RawModel;
import com.ljubeboskovski.drmario.gfx.shader.StaticShader;

public class Renderer implements GLSurfaceView.Renderer {

    private Context context;

    private Loader loader;
    private StaticShader shader;

    private RawModel model;

    Renderer(Context context) {
        this.context = context;
    }

    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        // Set the background frame color
        GLES30.glClearColor(1.0f, 0.0f, 1.0f, 1.0f);

        loader = new Loader();
        shader = new StaticShader();


        float[] vertices = {
                -0.9f, -0.9f, 0f,
                0.0f, 0.0f, 1.0f, 1.0f,

                0.9f, -0.9f, 0f,
                1.0f, 0.0f, 1.0f, 1.0f,

                0.9f, 0.9f, 0f,
                1.0f, 1.0f, 1.0f, 1.0f,

                -0.9f, 0.9f, 0f,
                0.0f, 1.0f, 1.0f, 1.0f

        };

        short[] indices = {
                0, 1, 3,
                3, 1, 2
        };

        int smx = 2;
        int smy = 1;
        int smsize = 8;

        float[] textureCoords = {
                ((float) smx) / smsize, ((float) smy + 1) / smsize,
                ((float) smx + 1) / smsize, ((float) smy + 1) / smsize,
                ((float) smx + 1) / smsize, ((float) smy) / smsize,
                ((float) smx) / smsize, ((float) smy) / smsize

//			0, 1,
//			1, 1,
//			1, 0,
//			0, 0
        };

        model = loader.loadColorModelToVAO(vertices, indices);

        //ModelTexture texture = new ModelTexture(loader.loadTexture("sprites" +
        //		"/blocks_spritemap"));
        //TexturedModel texturedModel = new TexturedModel(model, texture);

    }

    public void onDrawFrame(GL10 unused) {
        // Redraw background color
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);
        draw();
    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES30.glViewport(0, 0, width, height);
    }

    private void draw() {
        shader.start();

        prepare();
        GLES30.glDrawElements(GLES30.GL_TRIANGLES, model.getIndexSize(), GLES30.GL_UNSIGNED_SHORT, 0);
        cleanUp();

        shader.stop();
    }

    private void prepare() {
        // Get attribute and uniform IDs
        int positionHandle = GLES30.glGetAttribLocation(shader.programID, "aPosition");
        int colorHandle = GLES30.glGetAttribLocation(shader.programID, "aColor");

        // Bind the VBO and IBO
        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, model.getVaoID());
        GLES30.glBindBuffer(GLES30.GL_ELEMENT_ARRAY_BUFFER, model.getIaoID());

        // Set the attributes and uniforms
        GLES30.glVertexAttribPointer(positionHandle, Global.COORDS_PER_VERTEX, GLES30.GL_FLOAT,
                false, Global.STRIDE, 0);
        GLES30.glVertexAttribPointer(colorHandle, Global.COLOR_LENGTH, GLES30.GL_FLOAT,
                false, Global.STRIDE,
                Global.COORDS_PER_VERTEX * Global.BYTES_PER_FLOAT);

        // Enable the attributes and uniforms
        GLES30.glEnableVertexAttribArray(positionHandle);
        GLES30.glEnableVertexAttribArray(colorHandle);
    }

    private void cleanUp() {
        // Get attribute and uniform IDs
        int positionHandle = GLES30.glGetAttribLocation(shader.programID, "aPosition");
        int colorHandle = GLES30.glGetAttribLocation(shader.programID, "aColor");

        // Disable vertex array
        GLES30.glDisableVertexAttribArray(positionHandle);
        GLES30.glDisableVertexAttribArray(colorHandle);

        // Unbind the VBO and IBO
        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, 0);
        GLES30.glBindBuffer(GLES30.GL_ELEMENT_ARRAY_BUFFER, 0);
    }

}