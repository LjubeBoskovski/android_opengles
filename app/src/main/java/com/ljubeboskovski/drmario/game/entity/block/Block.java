package com.ljubeboskovski.drmario.game.entity.block;

import android.opengl.Matrix;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.game.entity.Entity;
import com.ljubeboskovski.drmario.gfx.model.TexturedModel;
import com.ljubeboskovski.drmario.gfx.texture.TextureMap;

public abstract class Block extends Entity {

    private int worldX, worldY;
    private float x, y, z, r, s;
    private boolean isFloating;
    private Global.BLOCK_COLOR color;

    private float[] colorVector;

    protected int textureMapX = -1;

    protected int textureMapY = -1;
    private TexturedModel model;
    public float[] mMatrix = new float[16];

    public Block(int x, int y, Global.BLOCK_COLOR color) {
        this.x = x;
        this.y = y;
        this.z = 1.0f;
        worldX = x;
        worldY  = y;
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
        update();
    }

    @Override
    public void update(){
        update(x, y, z, r, s);
    }


    public void update(float x, float y, float z, float r, float s) {
        Matrix.setIdentityM(mMatrix, 0);
        Matrix.translateM(mMatrix, 0, x + 0.5f, y + 0.5f, z);
        Matrix.scaleM(mMatrix, 0, s, s, 0);
        Matrix.rotateM(mMatrix, 0, r, 0.0f, 0.0f, 1.0f);
    }

    public TexturedModel getModel() {
        return model;
    }

    public float[] getmMatrix() {
        return mMatrix;
    }

    public void setModel(TexturedModel model) {
        this.model = model;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getR() {
        return r;
    }

    public float getS() {
        return s;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public void setR(float r) {
        this.r = r;
    }

    public void setS(float s) {
        this.s = s;
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

}
