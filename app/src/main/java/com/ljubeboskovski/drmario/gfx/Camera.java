package com.ljubeboskovski.drmario.gfx;

import android.opengl.GLES30;
import android.opengl.Matrix;

public class Camera {

    /**
     * Store the view matrix. This can be thought of as our camera. This matrix transforms world space to eye space;
     * it positions things relative to our eye.
     */
    public float[] vMatrix = new float[16];

    /**
     * Store the projection matrix. This is used to project the scene onto a 2D viewport.
     */
    public float[] pMatrix = new float[16];

    public float[] mvMatrix = new float[16];

    /**
     * Allocate storage for the final combined matrix. This will be passed into the shader program.
     */
    public float[] mvpMatrix = new float[16];

    public Camera() {

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

        Matrix.setLookAtM(vMatrix, 0, eyeX, eyeY, eyeZ, lookX, lookY, lookZ, upX, upY, upZ);


        // Create a new orthographic projection matrix.
        final float left = -4.5f;
        final float right = 4.5f;

        final float bottom = -8.0f;
        final float top = 8.0f;

        final float near = 1.0f;
        final float far = 20.0f;

        Matrix.orthoM(pMatrix, 0, left, right, bottom, top, near, far);
    }

    public void projectModel(float[] mMatrix) {
        // This multiplies the view matrix by the model matrix, and stores the result in the MV matrix
        // (which currently contains model * view).
        Matrix.multiplyMM(mvMatrix, 0, vMatrix, 0, mMatrix, 0);

        // This multiplies the model view matrix by the projection matrix, and stores the result
        // in the MVP matrix (which now contains model * view * projection).
        Matrix.multiplyMM(mvpMatrix, 0, pMatrix, 0, mvMatrix, 0);
    }


}
