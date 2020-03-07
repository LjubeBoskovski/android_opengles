package com.ljubeboskovski.drmario.gfx;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.game.entity.Block;
import com.ljubeboskovski.drmario.gfx.model.RawModel;
import com.ljubeboskovski.drmario.gfx.model.SingleColoredModel;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.List;

import android.opengl.GLES30;
import android.util.Log;

public class Loader {

    private List<Integer> vbos = new ArrayList<Integer>();
    private List<Integer> ibos = new ArrayList<Integer>();
    private List<Integer> textures = new ArrayList<Integer>();

    public static void loadToVAO(Block block) {

        FloatBuffer vertexBuffer = storeDataInFloatBuffer(block.getVertices());
        ShortBuffer indexBuffer = storeDataInShortBuffer(block.getIndices());

        int vboID = createBO();
        int iboID = createBO();

        bindBuffers(vboID, iboID, vertexBuffer, indexBuffer);
        block.setModel(new RawModel(vboID, iboID, block.getIndices().length));
    }


    private static int createBO() {
        final int[] bo = new int[1];
        GLES30.glGenBuffers(1, bo, 0);
        //vbos.add(bo[0]);
        return bo[0];
    }

    private static void bindBuffers(int vboID, int iboID, FloatBuffer vertexBuffer, ShortBuffer indexBuffer) {
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

    public static void bindBuffers(RawModel model){
        // Bind the VBO and IBO
        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, model.getVaoID());
        GLES30.glBindBuffer(GLES30.GL_ELEMENT_ARRAY_BUFFER, model.getIaoID());
    }

    public static void unbindBuffers(){
        // Unbind the VBO and IBO
        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, 0);
        GLES30.glBindBuffer(GLES30.GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    //    public int loadTexture(String fileName) {
//        Texture texture = new Texture("../../res/" + fileName + ".png");
//        int textureID = texture.getTextureID();
//        textures.add(textureID);
//        return textureID;
//    }

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
