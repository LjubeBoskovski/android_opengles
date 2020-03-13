package com.ljubeboskovski.drmario.game.entity;

import com.ljubeboskovski.drmario.gfx.model.TexturedModel;

public class TexturedEntity extends Entity {

    protected TexturedModel model;

    public TexturedEntity(float x, float y) {
        super(x, y);
    }

    public TexturedModel getModel() {
        return model;
    }
}
