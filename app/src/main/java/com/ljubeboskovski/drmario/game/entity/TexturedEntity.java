package com.ljubeboskovski.drmario.game.entity;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.gfx.model.TexturedModel;

import java.util.Arrays;

public class TexturedEntity extends Entity {

    protected TexturedModel model;
    private Global.BLOCK_COLOR color;

    public TexturedEntity(float x, float y, float r, Global.BLOCK_COLOR color) {
        super(x, y, r);
        this.color = color;
    }

    @Override
    public String toString() {
        return "TexturedEntity{" +
                "model=" + model +
                ", color=" + color +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", r=" + r +
                ", s=" + s +
                ", rx=" + rx +
                ", ry=" + ry +
                ", rz=" + rz +
                ", rr=" + rr +
                ", rs=" + rs +
                ", mMatrix=" + Arrays.toString(mMatrix) +
                '}';
    }

    public TexturedModel getModel() {
        return model;
    }

    public Global.BLOCK_COLOR getColor() {
        return color;
    }
}
