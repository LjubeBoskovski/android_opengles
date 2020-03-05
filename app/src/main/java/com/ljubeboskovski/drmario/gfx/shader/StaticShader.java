package com.ljubeboskovski.drmario.gfx.shader;

public class StaticShader extends ShaderProgram {

//	private static final String VERTEX_FILE = "resources/shaders/testVert.txt";
//	private static final String FRAGMENT_FILE = "resources/shaders/testFrag.txt";


    private static final String vertexShaderCode = "attribute vec4 aPosition;" +
            "attribute vec4 aColor;" +
            "varying vec4 vColor;" +
            "void main() {" +
            "  vColor = aColor;" +
            "  gl_Position = aPosition;" +
            "}";

    private static final String fragmentShaderCode = "precision mediump float;" +
            "varying vec4 vColor;" +
            "void main() {" +
            "  gl_FragColor = vColor;" +
            "}";


    public StaticShader() {
        super(vertexShaderCode, fragmentShaderCode);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
    }

}
