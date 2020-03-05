package com.ljubeboskovski.drmario.gfx.shader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import android.opengl.GLES30;

public abstract class ShaderProgram {

	public int programID;
	private int vertexShaderID;
	private int fragmentShaderID;

	public ShaderProgram(final String vertexCode, final String fragmentCode) {
        vertexShaderID = loadShader(GLES30.GL_VERTEX_SHADER, vertexCode);
        fragmentShaderID = loadShader(GLES30.GL_FRAGMENT_SHADER, fragmentCode);
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
	}

	public void stop() {
		GLES30.glUseProgram(0);
	}

	public void cleanUp() {
//		stop();
//		glDetachShader(programID, vertexShaderID);
//		glDetachShader(programID, fragmentShaderID);
//		glDeleteShader(vertexShaderID);
//		glDeleteShader(fragmentShaderID);
//		glDeleteProgram(programID);
	}

	protected void bindAttribute(int attribute, String variableName) {
		GLES30.glBindAttribLocation(programID, attribute, variableName);
	}

	protected abstract void bindAttributes();

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


    public static int loadShader(int type, String shaderCode) {

        int shader = GLES30.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES30.glShaderSource(shader, shaderCode);
        GLES30.glCompileShader(shader);

        return shader;
    }

}
