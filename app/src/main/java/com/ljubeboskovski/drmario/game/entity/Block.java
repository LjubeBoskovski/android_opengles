package com.ljubeboskovski.drmario.game.entity;

import android.opengl.Matrix;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.Global.*;
import com.ljubeboskovski.drmario.gfx.Loader;
import com.ljubeboskovski.drmario.gfx.model.RawModel;
import com.ljubeboskovski.drmario.gfx.model.SingleColoredModel;

public class Block extends Entity {

    private float x;


    private float y;
    private BlockColor color;
    private float[] colorVector;

    private RawModel model;


    public float[] mMatrix = new float[16];


    float[] vertices;

    float[] coordinates = {
            -0.5f, -0.5f, 0f,
            0.5f, -0.5f, 0f,
            0.5f, 0.5f, 0f,
            -0.5f, 0.5f, 0f,
    };

    short[] indices = {
            0, 1, 3,
            3, 1, 2
    };


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
    public Block(int x, int y, BlockColor color) {
        this.x = x;
        this.y = y;
        this.color = color;
        switch (color){
            case RED:
                colorVector = new float[] {1.0f, 0.0f, 0.0f, 1.0f};
                break;
            case YELLOW:
                colorVector = new float[] {1.0f, 1.0f, 0.0f, 1.0f};
                break;
            case BLUE:
                colorVector = new float[] {0.0f, 0.0f, 1.0f, 1.0f};
                break;
            default:
                colorVector = new float[] {0.0f, 1.0f, 0.0f, 1.0f};
                break;
        }

        int verticesSize =
                coordinates.length + (coordinates.length / Global.COORDS_PER_VERTEX) * Global.COLOR_LENGTH;
        vertices = new float[verticesSize];

        int j = 0;
        for(int i = 0; i < coordinates.length / Global.COORDS_PER_VERTEX; i++){
            for(int k = 0; k < Global.COORDS_PER_VERTEX; k++) {
                vertices[j++] = coordinates[i * Global.COORDS_PER_VERTEX + k];
            }
            for(int k = 0; k < colorVector.length; k++) {
                vertices[j++] = colorVector[k];
            }
        }

        update(0.0f);
    }

    public void update(float scale){
        Matrix.setIdentityM(mMatrix, 0);
        Matrix.translateM(mMatrix, 0, x + 0.5f, y + 0.5f, scale);
        Matrix.scaleM(mMatrix, 0, scale, scale, 0);
        Matrix.rotateM(mMatrix, 0, 0f, 0.0f, 0.0f, 1.0f);
    }

    public RawModel getModel() {
        return model;
    }

    public float[] getmMatrix() {
        return mMatrix;
    }

    public float[] getVertices() {
        return vertices;
    }

    public short[] getIndices() {
        return indices;
    }

    public void setModel(RawModel model) {
        this.model = model;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
