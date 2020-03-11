package com.ljubeboskovski.drmario.game.entity;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.game.entity.block.DoubleBlock;

public class Pill extends Entity {

    int worldX, worldY;
    float x, y, r, s;
    boolean isFloating;

    private DoubleBlock blockNorth;
    private DoubleBlock blockSouth;

    public Pill(int x, int y, Global.BLOCK_COLOR colorNorth, Global.BLOCK_COLOR colorSouth) {
        this.blockNorth = new DoubleBlock(x, y, colorNorth);
        this.blockSouth = new DoubleBlock(x, y-1, colorSouth);
        this.worldX = x;
        this.worldY = y;
        this.x = x;
        this.y = y;
        this.r = 0.0f;
        this.s = 1.0f;
        isFloating = true;
    }

    @Override
    public void update() {
        blockNorth.update();
    }
}
