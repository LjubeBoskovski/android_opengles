package com.ljubeboskovski.drmario.game.entity;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.gfx.model.TexturedModel;

public class TexturedEntity extends Entity {

    protected TexturedModel model;
    private Global.BLOCK_COLOR color;

    public TexturedEntity(float x, float y, Global.BLOCK_COLOR color) {
        super(x, y);
        this.color = color;
    }

    public TexturedModel getModel() {
        return model;
    }

    public Global.BLOCK_COLOR getColor() {
        return color;
    }
}
