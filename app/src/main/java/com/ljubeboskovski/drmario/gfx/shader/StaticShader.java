package com.ljubeboskovski.drmario.gfx.shader;

import android.content.Context;
import android.opengl.GLES30;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.R;

public class StaticShader extends ShaderProgram {

//    private static final String vertexShaderCode = "attribute vec4 aPosition;" +
//            "attribute vec4 aColor;" +
//            "varying vec4 vColor;" +
//            "void main() {" +
//            "  vColor = aColor;" +
//            "  gl_Position = aPosition;" +
//            "}";
//
//    private static final String fragmentShaderCode = "precision mediump float;" +
//            "varying vec4 vColor;" +
//            "void main() {" +
//            "  gl_FragColor = vColor;" +
//            "}";

    private static final int VERTEX_RESOURCE_ID = R.raw.static_vertex;
    private static final int FRAGMENT_RESOURCE_ID = R.raw.static_fragment;

    private int positionHandle;
    private int colorHandle;

    public StaticShader(Context context) {
        super(context, VERTEX_RESOURCE_ID, FRAGMENT_RESOURCE_ID);
        // Get attribute and uniform IDs
        positionHandle = GLES30.glGetAttribLocation(super.programID, "aPosition");
        colorHandle = GLES30.glGetAttribLocation(super.programID, "aColor");
    }

    @Override
    public void bindAttributes() {
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

    @Override
    public void unbindAttributes() {
        // Disable vertex array
        GLES30.glDisableVertexAttribArray(positionHandle);
        GLES30.glDisableVertexAttribArray(colorHandle);
    }

}
