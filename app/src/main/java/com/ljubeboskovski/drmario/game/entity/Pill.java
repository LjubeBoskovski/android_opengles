package com.ljubeboskovski.drmario.game.entity;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.game.entity.block.DoubleBlock;

public class Pill extends Entity {


    int worldX, worldY, worldR;
    float x, y, r, s;
    boolean isFloating;


    private DoubleBlock blockNorth;
    private DoubleBlock blockSouth;

    public Pill(int x, int y, Global.BLOCK_COLOR colorNorth, Global.BLOCK_COLOR colorSouth) {
        this.blockNorth = new DoubleBlock(x, y, colorNorth);
        this.blockSouth = new DoubleBlock(x, y-1, colorSouth);
        this.worldX = x;
        this.worldY = y;
        this.worldR = 0;
        this.x = x;
        this.y = y;
        this.r = 0.0f;
        this.s = 1.0f;
        isFloating = true;
    }

    @Override
    public void update(){
        if(isFloating) {
            update(x, y, r, s);
        } else {
            update(worldX, worldY, worldR * 90, 1.0f);
        }
    }
    public void update(float x, float y, float r, float s) {
        float xd = (float)Math.sin(Math.toRadians(r)) * 0.5f;
        float yd = (float)Math.cos(Math.toRadians(r)) * 0.5f;
        blockNorth.update(x + xd * s, y + yd * s, -r - 90, s);
        blockSouth.update(x - xd * s, y - yd * s, -r + 90, s);
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getR() {
        return r;
    }

    public float getS() {
        return s;
    }

    public boolean isFloating() {
        return isFloating;
    }

    public DoubleBlock getBlockNorth() {
        return blockNorth;
    }

    public DoubleBlock getBlockSouth() {
        return blockSouth;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setR(float r) {
        this.r = r;
    }

    public void setS(float s) {
        this.s = s;
    }

    public void setFloating(boolean floating) {
        isFloating = floating;
    }

    public void setBlockNorth(DoubleBlock blockNorth) {
        this.blockNorth = blockNorth;
    }

    public void setBlockSouth(DoubleBlock blockSouth) {
        this.blockSouth = blockSouth;
    }
}
