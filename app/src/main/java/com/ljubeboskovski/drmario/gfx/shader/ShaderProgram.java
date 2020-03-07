package com.ljubeboskovski.drmario.gfx.shader;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.opengl.GLES30;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.util.RawResourceReader;

public abstract class ShaderProgram {

    private Context context;
    int programID;
    int vertexShaderID;
    int fragmentShaderID;

    public static LinkedList<Attribute> attributes = new LinkedList<Attribute>();
    int stride;


    public ShaderProgram(Context context, int vertexResourceID, int fragmentResourceID) {
        this.context = context;
        vertexShaderID = loadShader(GLES30.GL_VERTEX_SHADER, vertexResourceID);
        fragmentShaderID = loadShader(GLES30.GL_FRAGMENT_SHADER, fragmentResourceID);
        programID = GLES30.glCreateProgram();
        GLES30.glAttachShader(programID, vertexShaderID);
        GLES30.glAttachShader(programID, fragmentShaderID);
        GLES30.glLinkProgram(programID);
        //TODO: get program information (glGetProgramiv()) and log errors
        GLES30.glValidateProgram(programID);
        bindAttributes();
    }

    protected void addAttribute(String name, int size, int type, int typeSize, boolean normalized) {
        int handle = GLES30.glGetAttribLocation(programID, name);
        Attribute lastAttribute = attributes.getLast();
        int newOffset = lastAttribute.offset + lastAttribute.size * typeSize;
        Attribute newAttribute = new Attribute(programID, name, handle, size, type, typeSize,
                normalized, stride, newOffset);
        attributes.add(newAttribute);
    }

    public void start() {
        GLES30.glUseProgram(programID);
        bindAttributes();
    }

    public void stop() {
        unbindAttributes();
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


    public void bindAttributes() {
        for (Attribute attr : attributes) {
            GLES30.glVertexAttribPointer(attr.handle, attr.size, attr.type,
                    attr.normalized, attr.stride, attr.offset);
            GLES30.glEnableVertexAttribArray(attr.handle);
        }
    }

    public void unbindAttributes() {
        for (Attribute attr : attributes) {
            GLES30.glDisableVertexAttribArray(attr.handle);
        }
    }

    private int loadShader(int type, int shaderResource) {
        String shaderCode = RawResourceReader.readTextFileFromRawResource(context,
                shaderResource);

        int shader = GLES30.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES30.glShaderSource(shader, shaderCode);
        GLES30.glCompileShader(shader);

        return shader;
    }

}
