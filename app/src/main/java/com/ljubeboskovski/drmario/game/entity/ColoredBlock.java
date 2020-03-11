package com.ljubeboskovski.drmario.game.entity;

import android.opengl.Matrix;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.Global.*;
import com.ljubeboskovski.drmario.gfx.model.RawModel;

public class ColoredBlock extends Entity {

    private float x;
    private float y;
    private float[] color;

    private RawModel model;
    public float[] mMatrix = new float[16];

    public ColoredBlock(int x, int y, float[] color) {
        this.x = x;
        this.y = y;
        this.color = color;
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
        float[] coordinates = QuadForm.coordinates;

        int verticesSize =
                coordinates.length + (coordinates.length / Global.SIZE_POSITION) * (Global.SIZE_COLOR);
        float[] vertices = new float[verticesSize];

        int j = 0;
        for(int i = 0; i < coordinates.length / Global.SIZE_POSITION; i++){
            for(int k = 0; k < Global.SIZE_POSITION; k++) {
                vertices[j++] = coordinates[i * Global.SIZE_POSITION + k];
            }
            for(int k = 0; k < Global.SIZE_COLOR; k++) {
                vertices[j++] = color[k];
            }
        }
        return vertices;
    }

    public short[] getIndices() {
        return QuadForm.indices;
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
