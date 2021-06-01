package com.ljubeboskovski.drmario.input;

import com.ljubeboskovski.drmario.game.entity.TexturedEntity;
import com.ljubeboskovski.drmario.gfx.model.TexturedModel;

public class Button extends TexturedEntity {

    Runnable runnable;

    public Button(float x, float y, float r, TexturedModel model, Runnable runnable) {
        super(x, y, r);
        this.model = model;
        this.runnable = runnable;
    }

    public void press() {
        runnable.run();
        s = 1.2f;
    }

    public void unpress() {
        s = 1.0f;
    }
}
