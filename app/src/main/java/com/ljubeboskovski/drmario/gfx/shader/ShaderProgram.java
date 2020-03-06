package com.ljubeboskovski.drmario.gfx.shader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.opengl.GLES30;

import com.ljubeboskovski.drmario.util.RawResourceReader;

public abstract class ShaderProgram {

    private Context context;
	int programID;
	int vertexShaderID;
	int fragmentShaderID;

	// TODO:
//	private List<Integer> attributes = new ArrayList<Integer>();

	public ShaderProgram(Context context, int vertexResourceID, int fragmentResourceID) {
	    this.context = context;
        vertexShaderID = loadShader(GLES30.GL_VERTEX_SHADER, vertexResourceID);
        fragmentShaderID = loadShader(GLES30.GL_FRAGMENT_SHADER, fragmentResourceID);
        programID = GLES30.glCreateProgram();
        GLES30.glAttachShader(programID, vertexShaderID);
        GLES30.glAttachShader(programID, fragmentShaderID);
        GLES30.glLinkProgram(programID);
        GLES30.glValidateProgram(programID);
        bindAttributes();
    }

//	public ShaderProgram(String vertexFile, String fragmentFile){
//		vertexShaderID = loadShader(vertexFile, GL_VERTEX_SHADER);
//		fragmentShaderID = loadShader(fragmentFile, GL_FRAGMENT_SHADER);
//		programID = glCreateProgram();
//		glAttachShader(programID, vertexShaderID);
//		glAttachShader(programID, fragmentShaderID);
//		glLinkProgram(programID);
//		glValidateProgram(programID);
//		bindAttributes();
//	}

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


	protected abstract void bindAttributes();

	protected abstract void unbindAttributes();

	private static int loadShader(String file, int type) {
//		StringBuilder shaderSource = new StringBuilder();
//		try{
//			BufferedReader reader = new BufferedReader(new FileReader(file));
//			String line;
//			while((line = reader.readLine())!=null){
//				shaderSource.append(line).append("//\n");
//			}
//			reader.close();
//		}catch(IOException e){
//			e.printStackTrace();
//			System.exit(-1);
//		}
//		int shaderID = glCreateShader(type);
//		glShaderSource(shaderID, shaderSource);
//		glCompileShader(shaderID);
//		if(glGetShaderi(shaderID, GL_COMPILE_STATUS )== GL_FALSE){
//			System.out.println(glGetShaderInfoLog(shaderID, 500));
//			System.err.println("Could not compile shader!");
//			System.exit(-1);
//		}
//		return shaderID;
        return -1;
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
