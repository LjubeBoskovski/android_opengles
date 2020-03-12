package com.ljubeboskovski.drmario.game.entity;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.game.entity.block.DoubleBlock;

public class Pill extends Entity {


    boolean isFloating;


    private DoubleBlock blockNorth;
    private DoubleBlock blockSouth;


    public Pill(int x, int y, Global.BLOCK_COLOR colorNorth, Global.BLOCK_COLOR colorSouth) {
        this.blockNorth = new DoubleBlock(x, y, colorNorth);
        this.blockSouth = new DoubleBlock(x, y - 1, colorSouth);
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
    public void update() {
//        z = isFloating ? s + 1.0f : 1.0f;
        if (0 <= r && r < 90) {
            float xd = (float) Math.sin(Math.toRadians(r));
            float yd = (float) Math.cos(Math.toRadians(r));

            blockNorth.setPosRotScale(x + xd * s, y + yd * s, z, -r - 90, s);
            blockSouth.setPosRotScale(x, y, z, -r + 90, s);
        }
        if (180 <= r && r < 270) {
            float xd = (float) Math.sin(Math.toRadians(r + 180));
            float yd = (float) Math.cos(Math.toRadians(r + 180));

            blockSouth.setPosRotScale(x + xd * s, y + yd * s, z, -r + 90, s);
            blockNorth.setPosRotScale(x, y, z, -r - 90, s);
        }
        if(90 <= r && r < 180) {
            float alpha = 180 - r;
            float opposite = (float) Math.sin(Math.toRadians(alpha));
            float adjacent = (float) Math.cos(Math.toRadians(alpha));
            blockSouth.setPosRotScale(x, y + adjacent * s, z, -r + 90, s);
            blockNorth.setPosRotScale(x + opposite * s, y, z, -r - 90, s);
        }
        if(270 <= r && r < 360) {
            float alpha = - r;
            float opposite = (float) Math.sin(Math.toRadians(alpha));
            float adjacent = (float) Math.cos(Math.toRadians(alpha));
            blockNorth.setPosRotScale(x, y + adjacent * s, z, -r - 90, s);
            blockSouth.setPosRotScale(x + opposite * s, y, z, -r + 90, s);
        }

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
