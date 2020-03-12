package com.ljubeboskovski.drmario.game.entity.block;

import android.opengl.Matrix;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.game.entity.Entity;
import com.ljubeboskovski.drmario.gfx.model.TexturedModel;

public abstract class Block extends Entity {

    private int worldX, worldY;
    private boolean isFloating;
    private Global.BLOCK_COLOR color;
    private float[] colorVector;

    protected int textureMapX = -1;
    protected int textureMapY = -1;
    private TexturedModel model;

    Block(int x, int y, Global.BLOCK_COLOR color) {
        super(x, y);
        worldX = x;
        worldY = y;
        isFloating = true;
        this.color = color;
        switch (color) {
            case RED:
                colorVector = Global.BlockColor.RED;
                textureMapY = 0;
                break;
            case YELLOW:
                colorVector = Global.BlockColor.YELLOW;
                textureMapY = 1;
                break;
            case BLUE:
                colorVector = Global.BlockColor.BLUE;
                textureMapY = 2;
                break;
            default:
                colorVector = Global.BlockColor.GREEN;
                textureMapY = 3;
                break;
        }
    }

    @Override
    public void update() {
        Matrix.setIdentityM(mMatrix, 0);
        Matrix.translateM(mMatrix, 0, x + 0.5f, y + 0.5f, z);
        Matrix.scaleM(mMatrix, 0, s, s, 0);
        Matrix.rotateM(mMatrix, 0, r, 0.0f, 0.0f, 1.0f);
    }

    public TexturedModel getModel() {
        return model;
    }

    public float[] getColorVector() {
        return colorVector;
    }

    public int getTextureMapX() {
        return textureMapX;
    }

    public int getTextureMapY() {
        return textureMapY;
    }

    public void setModel(TexturedModel model) {
        this.model = model;
    }

}
