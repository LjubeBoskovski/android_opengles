package com.ljubeboskovski.drmario.game.entity.block;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.game.entity.TexturedEntity;

public abstract class Block extends TexturedEntity {

    private Global.BLOCK_COLOR color;

    Block(float x, float y, float r, Global.BLOCK_COLOR color) {
        super(x, y, r);
        this.color = color;
    }

    @Override
    public String toStringType() {
        return "B";
    }

    public Global.BLOCK_COLOR getColor() {
        return color;
    }

}
