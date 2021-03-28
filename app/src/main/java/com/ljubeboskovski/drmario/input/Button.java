package com.ljubeboskovski.drmario.input;

import com.ljubeboskovski.drmario.game.entity.TexturedEntity;
import com.ljubeboskovski.drmario.gfx.model.TexturedModel;

public class Button extends TexturedEntity {

    public Button(float x, float y, float r, TexturedModel model) {
        super(x, y, r);
        this.model = model;
    }
}
