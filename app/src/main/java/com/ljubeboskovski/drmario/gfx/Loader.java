package com.ljubeboskovski.drmario.gfx;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.gfx.model.RawModel;
import com.ljubeboskovski.drmario.gfx.model.TexturedModel;
import com.ljubeboskovski.drmario.gfx.texture.Texture;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;

import android.content.Context;
import android.opengl.GLES30;
import android.util.Log;

public class Loader {

    private ArrayList<Integer> vbos = new ArrayList<Integer>();
    private ArrayList<Integer> ibos = new ArrayList<Integer>();
    private ArrayList<Integer> textures = new ArrayList<Integer>();

    private Context context;

    public Loader(Context context){
        this.context = context;
    }

    public RawModel loadToVAO(float[] vertices, short[] indices) {

        FloatBuffer vertexBuffer = storeDataInFloatBuffer(vertices);
        ShortBuffer indexBuffer = storeDataInShortBuffer(indices);

        int vboID = createVBO();
        int iboID = createIBO();

        bindBuffers(vboID, iboID, vertexBuffer, indexBuffer);
        return new RawModel(vboID, iboID, indices.length);
    }

    private int createVBO() {
        final int[] bo = new int[1];
        GLES30.glGenBuffers(1, bo, 0);
        vbos.add(bo[0]);
        return bo[0];
    }

    private int createIBO() {
        final int[] bo = new int[1];
        GLES30.glGenBuffers(1, bo, 0);
        ibos.add(bo[0]);
        return bo[0];
    }

    private void bindBuffers(int vboID, int iboID, FloatBuffer vertexBuffer, ShortBuffer indexBuffer) {
        if (vboID > 0 && iboID > 0) {
            GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, vboID);
            GLES30.glBufferData(GLES30.GL_ARRAY_BUFFER, vertexBuffer.capacity()
                    * Global.BYTES_PER_FLOAT, vertexBuffer, GLES30.GL_STATIC_DRAW);

            GLES30.glBindBuffer(GLES30.GL_ELEMENT_ARRAY_BUFFER, iboID);
            GLES30.glBufferData(GLES30.GL_ELEMENT_ARRAY_BUFFER, indexBuffer.capacity()
                    * Global.BYTES_PER_SHORT, indexBuffer, GLES30.GL_STATIC_DRAW);

            GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, 0);
            GLES30.glBindBuffer(GLES30.GL_ELEMENT_ARRAY_BUFFER, 0);

        } else {
            //errorHandler.handleError(ErrorType.BUFFER_CREATION_ERROR, &quot;glGenBuffers&quot;);
            Log.println(Log.ERROR, "Renderer", "Buffer could not be created");
            return;
        }
    }

    public void bindBuffers(RawModel model){
        // Bind the VBO and IBO
        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, model.getVaoID());
        GLES30.glBindBuffer(GLES30.GL_ELEMENT_ARRAY_BUFFER, model.getIaoID());
    }

    public void bindBuffers(TexturedModel model) {
        GLES30.glActiveTexture(GLES30.GL_TEXTURE0);
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, model.getTexture().getID());
        GLES30.glUniform1i(model.getTexture().getID(), 0);
        bindBuffers(model.getRawModel());
    }

    public void unbindBuffers(){
        // Unbind the VBO and IBO
        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, 0);
        GLES30.glBindBuffer(GLES30.GL_ELEMENT_ARRAY_BUFFER, 0);
    }

        public int loadTexture(int resourceID) {
        Texture texture = new Texture(context, resourceID);
        int textureID = texture.getTextureID();
        textures.add(textureID);
        return textureID;
    }

    //    public void cleanUp() {
//        for (int vbo : vbos) {
//            GLES30.glDeleteVertexArrays(vbo);
//        }
//        for (int ibo : ibos) {
//            GLES30.glDeleteBuffers(ibo);
//        }
//        for (int texture : textures) {
//            GLES30.glDeleteTextures(texture);
//        }
//    }

    private static FloatBuffer storeDataInFloatBuffer(float[] vertices) {
        FloatBuffer vertexBuffer;

        // initialize vertex byte buffer for shape coordinates
        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (number of coordinate values * 4 bytes per float)
                vertices.length * Global.BYTES_PER_FLOAT);
        // use the device hardware's native byte order
        bb.order(ByteOrder.nativeOrder());

        // create a floating point buffer from the ByteBuffer
        vertexBuffer = bb.asFloatBuffer();
        // add the coordinates to the FloatBuffer
        vertexBuffer.put(vertices);
        // set the buffer to read the first coordinate
        vertexBuffer.position(0);

        return vertexBuffer;
    }

    private static ShortBuffer storeDataInShortBuffer(short[] indices) {
        ShortBuffer indexBuffer;

        ByteBuffer bb = ByteBuffer.allocateDirect(indices.length * Global.BYTES_PER_SHORT);
        bb.order(ByteOrder.nativeOrder());
        indexBuffer = bb.asShortBuffer();
        indexBuffer.put(indices);
        indexBuffer.position(0);
        return indexBuffer;
    }

}
