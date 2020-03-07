package com.ljubeboskovski.drmario.gfx.model;

public class SingleColoredModel extends RawModel{

    private float[] color;


    public SingleColoredModel(int vaoID, int iaoID, int indexSize, float[] color) {
        super(vaoID, iaoID, indexSize);
        this.color = color;
    }
}
