package com.ljubeboskovski.drmario.game.entity;

import com.ljubeboskovski.drmario.Global.*;

public class Block extends Entity {

    private int x, y;
    private BlockColor color;

    public Block(int x, int y, BlockColor color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }
}
