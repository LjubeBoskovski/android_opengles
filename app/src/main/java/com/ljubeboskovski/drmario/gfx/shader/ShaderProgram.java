package com.ljubeboskovski.drmario.gfx.shader;

import java.util.LinkedList;

import android.content.Context;
import android.opengl.GLES30;
import android.util.Log;

import com.ljubeboskovski.drmario.gfx.Camera;
import com.ljubeboskovski.drmario.util.RawResourceReader;

public abstract class ShaderProgram {

    private Context context;
    public int programID; //FixMe

    private static LinkedList<Attribute> attributes = new LinkedList<Attribute>();
    private static LinkedList<Uniform> uniforms = new LinkedList<Uniform>();

    private int mvMatrixHandle;
    private int mvpMatrixHandle;
    public int textureHandle; //FixMe: private


    ShaderProgram(Context context, int vertexResourceID, int fragmentResourceID) {
        this.context = context;
        int vertexShaderID = compileShader(GLES30.GL_VERTEX_SHADER, vertexResourceID);
        int fragmentShaderID = compileShader(GLES30.GL_FRAGMENT_SHADER, fragmentResourceID);
        programID = GLES30.glCreateProgram();
        GLES30.glAttachShader(programID, vertexShaderID);
        GLES30.glAttachShader(programID, fragmentShaderID);
        GLES30.glLinkProgram(programID);
        //TODO: get program information (glGetProgramiv()) and log errors
        GLES30.glValidateProgram(programID);
//        bindAttributes();

        mvpMatrixHandle = GLES30.glGetUniformLocation(programID, "u_MVPMatrix");
        mvMatrixHandle = GLES30.glGetUniformLocation(programID, "u_MVMatrix");
        textureHandle = GLES30.glGetUniformLocation(programID, "uTexture");

    }

    void addAttribute(String name, int size, int type, int typeSize, boolean normalized) {
        int handle = GLES30.glGetAttribLocation(programID, name);
        int newOffset = 0;
        int newStride = size * typeSize;
        if (!attributes.isEmpty()) {
            Attribute lastAttribute = attributes.getLast();
            newOffset = lastAttribute.offset + lastAttribute.size * typeSize;
            newStride = lastAttribute.stride + size * typeSize;
            for (Attribute attr : attributes) {
                attr.stride = newStride;
            }
        }

        Attribute newAttribute = new Attribute(programID, name, handle, size, type, typeSize,
                normalized, newStride, newOffset);
        attributes.add(newAttribute);
    }

//    public Uniform addUniform(String name,)


    public void start() {
        GLES30.glUseProgram(programID);
    }

    public void stop() {
        GLES30.glUseProgram(0);
    }

//	public void cleanUp() {
//		stop();
//		glDetachShader(programID, vertexShaderID);
//		glDetachShader(programID, fragmentShaderID);
//		glDeleteShader(vertexShaderID);
//		glDeleteShader(fragmentShaderID);
//		glDeleteProgram(programID);
//	}


    public void bindAttributes(Camera camera) {
        for (Attribute attr : attributes) {
            GLES30.glVertexAttribPointer(attr.handle, attr.size, attr.type,
                    attr.normalized, attr.stride, attr.offset);
            GLES30.glEnableVertexAttribArray(attr.handle);
        }
        GLES30.glUniformMatrix4fv(mvMatrixHandle, 1, false, camera.mvMatrix, 0);
        GLES30.glUniformMatrix4fv(mvpMatrixHandle, 1, false, camera.mvpMatrix, 0);
//        GLES30.glUniform1i(textureHandle, 0);
    }

    public void unbindAttributes() {
        for (Attribute attr : attributes) {
            GLES30.glDisableVertexAttribArray(attr.handle);
        }
    }

    private int compileShader(int type, int shaderResource) {
        String shaderCode = RawResourceReader.readTextFileFromRawResource(context,
                shaderResource);

        int shader = GLES30.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES30.glShaderSource(shader, shaderCode);
        GLES30.glCompileShader(shader);

        // Get the compilation status.
        final int[] compileStatus = new int[1];
        GLES30.glGetShaderiv(shader, GLES30.GL_COMPILE_STATUS, compileStatus, 0);

        // If the compilation failed, delete the shader.
        if (compileStatus[0] == 0) {
            Log.e("ShaderProgram",
                    "Error compiling shader: " + GLES30.glGetShaderInfoLog(shader));
            GLES30.glDeleteShader(shader);
            shader = 0;
        }
        return shader;
    }

}
