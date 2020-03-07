package com.ljubeboskovski.drmario.gfx;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.SystemClock;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.gfx.model.RawModel;
import com.ljubeboskovski.drmario.gfx.shader.StaticShader;

public class Renderer implements GLSurfaceView.Renderer {

    private Context context;

    private Loader loader;
    private StaticShader shader;

    private RawModel model;


    /**
     * Store the model matrix. This matrix is used to move models from object space (where each model can be thought
     * of being located at the center of the universe) to world space.
     */
    private float[] mModelMatrix = new float[16];

    /**
     * Store the view matrix. This can be thought of as our camera. This matrix transforms world space to eye space;
     * it positions things relative to our eye.
     */
    private float[] mViewMatrix = new float[16];

    /** Store the projection matrix. This is used to project the scene onto a 2D viewport. */
    private float[] mProjectionMatrix = new float[16];

    /** Allocate storage for the final combined matrix. This will be passed into the shader program. */
    private float[] mMVPMatrix = new float[16];

    /** This will be used to pass in the transformation matrix. */
    private int mMVPMatrixHandle;

    /** This will be used to pass in the modelview matrix. */
    private int mMVMatrixHandle;



    public Renderer(Context context) {
        this.context = context;
    }

    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        // Set the background frame color
        GLES30.glClearColor(1.0f, 0.0f, 1.0f, 1.0f);

//        // Use culling to remove back faces.
//        GLES30.glEnable(GLES30.GL_CULL_FACE);

//        // TODO: Enable depth testing
//        GLES30.glEnable(GLES30.GL_DEPTH_TEST);


        loader = new Loader();
        shader = new StaticShader(context);


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

        // Position the eye in front of the origin.
        final float eyeX = 0.0f;
        final float eyeY = 0.0f;
        final float eyeZ = 10.0f;

        // We are looking toward the distance
        final float lookX = 0.0f;
        final float lookY = 0.0f;
        final float lookZ = -1.0f;

        // Set our up vector. This is where our head would be pointing were we holding the camera.
        final float upX = 0.0f;
        final float upY = 1.0f;
        final float upZ = 0.0f;

        Matrix.setLookAtM(mViewMatrix, 0, eyeX, eyeY, eyeZ, lookX, lookY, lookZ, upX, upY, upZ);
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

        // Create a new perspective projection matrix. The height will stay the same
        // while the width will vary as per aspect ratio.
        final float ratio = (float) width / height;
        final float left = -4.5f;
        final float right = 4.5f;
        final float bottom = -8.0f;
        final float top = 8.0f;
        final float near = 1.0f;
        final float far = 20.0f;

        //Matrix.frustumM(mProjectionMatrix, 0, left, right, bottom, top, near, far);
        Matrix.orthoM(mProjectionMatrix, 0, left, right, bottom, top, near, far);
    }

    public void onDrawFrame(GL10 unused) {
        // Redraw background color
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT | GLES30.GL_DEPTH_BUFFER_BIT);
        draw();
    }


    private void draw() {
        shader.start();

        mMVPMatrixHandle = GLES30.glGetUniformLocation(shader.programID, "u_MVPMatrix");
        mMVMatrixHandle = GLES30.glGetUniformLocation(shader.programID, "u_MVMatrix");

        long time = SystemClock.uptimeMillis() % 1000L;
        float angleInDegrees = (360.0f / 1000.0f) * ((int) time);


        Matrix.setIdentityM(mModelMatrix, 0);
        Matrix.translateM(mModelMatrix, 0, angleInDegrees/100.0f, angleInDegrees/200.0f, 0.0f);
        Matrix.rotateM(mModelMatrix, 0, angleInDegrees, 0.0f, 0.0f, 1.0f);


        // Bind the VBO and IBO
        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, model.getVaoID());
        GLES30.glBindBuffer(GLES30.GL_ELEMENT_ARRAY_BUFFER, model.getIaoID());

        // This multiplies the view matrix by the model matrix, and stores the result in the MVP matrix
        // (which currently contains model * view).
        Matrix.multiplyMM(mMVPMatrix, 0, mViewMatrix, 0, mModelMatrix, 0);

        // Pass in the modelview matrix.
        GLES30.glUniformMatrix4fv(mMVMatrixHandle, 1, false, mMVPMatrix, 0);

        // This multiplies the modelview matrix by the projection matrix, and stores the result in the MVP matrix
        // (which now contains model * view * projection).
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mMVPMatrix, 0);

        // Pass in the combined matrix.
        GLES30.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mMVPMatrix, 0);


        shader.bindAttributes();
        GLES30.glDrawElements(GLES30.GL_TRIANGLES, model.getIndexSize(), GLES30.GL_UNSIGNED_SHORT, 0);
        shader.unbindAttributes();

        // Unbind the VBO and IBO
        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, 0);
        GLES30.glBindBuffer(GLES30.GL_ELEMENT_ARRAY_BUFFER, 0);

        shader.stop();
    }



}