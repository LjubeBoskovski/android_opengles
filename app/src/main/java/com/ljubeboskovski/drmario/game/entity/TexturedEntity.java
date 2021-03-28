package com.ljubeboskovski.drmario.game.entity;

import com.ljubeboskovski.drmario.gfx.model.TexturedModel;

public class TexturedEntity extends Entity {

    protected TexturedModel model;

    public TexturedEntity(float x, float y, float r) {
        super(x, y, r);
    }

    public TexturedModel getModel() {
        return model;
    }
}
