package com.ljubeboskovski.drmario.game.entity;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.game.entity.block.DoubleBlock;

public class Pill extends Entity {


    boolean isFloating;


    private DoubleBlock blockNorth;
    private DoubleBlock blockSouth;


    public Pill(int x, int y, Global.BLOCK_COLOR colorNorth, Global.BLOCK_COLOR colorSouth) {
        this.blockNorth = new DoubleBlock(x, y, colorNorth);
        this.blockSouth = new DoubleBlock(x, y-1, colorSouth);
        this.x = x;
        this.y = y;
        this.r = 0.0f;
        this.s = 1.0f;
        this.z = s;
        isFloating = true;
    }

    public Pill(int x, int y) {
        this(x, y, Global.BlockColor.getRandomColor(), Global.BlockColor.getRandomColor());
    }

    @Override
    public void update(){
//        z = isFloating ? s + 1.0f : 1.0f;
        float xd = (float)Math.sin(Math.toRadians(r)) * 0.5f;
        float yd = (float)Math.cos(Math.toRadians(r)) * 0.5f;
        blockNorth.setPosRotScale(x + xd * s, y + yd * s + 0.5f, z, -r - 90, s);
        blockSouth.setPosRotScale(x - xd * s, y - yd * s + 0.5f, z, -r + 90, s);
        blockNorth.update();
        blockSouth.update();
    }

    @Override
    public void setPosRotScale(float x, float y, float z, float r, float s) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.r = r;
        this.s = s;

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

    public void setFloating(boolean floating) {
        isFloating = floating;
    }

    public void setBlockNorth(DoubleBlock blockNorth) {
        this.blockNorth = blockNorth;
    }

    public void setBlockSouth(DoubleBlock blockSouth) {
        this.blockSouth = blockSouth;
    }

    @Override
    public void setY(float y) {
        setPosRotScale(x, y, z, r, s);
    }
}
