package com.ljubeboskovski.drmario.game.entity.block;

import android.opengl.Matrix;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.Global.*;
import com.ljubeboskovski.drmario.game.entity.Entity;
import com.ljubeboskovski.drmario.gfx.model.RawModel;

public class ColoredBlock extends Entity {

    private float[] color;
    private RawModel model;

    public ColoredBlock(int x, int y, float[] color) {
        super(x, y);
        this.color = color;
    }

    public RawModel getModel() {
        return model;
    }

    public float[] getVertices() {
        //TODO: add loadToVAO function in loader for this
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
}
