package com.ljubeboskovski.drmario.game.entity;

import com.ljubeboskovski.drmario.gfx.model.TexturedModel;
import com.ljubeboskovski.drmario.gfx.texture.Texture;

public class TexturedEntity extends Entity {

    protected int textureMapX = -1;
    protected int textureMapY = -1;
    private TexturedModel model;

    public TexturedEntity(float x, float y) {
        super(x, y);
    }


    public TexturedModel getModel() {
        return model;
    }

    public void setModel(TexturedModel model) {
        this.model = model;
    }

    public int getTextureMapX() {
        return textureMapX;
    }

    public int getTextureMapY() {
        return textureMapY;
    }


}
