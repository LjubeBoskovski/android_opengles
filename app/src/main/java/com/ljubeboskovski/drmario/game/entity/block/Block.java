package com.ljubeboskovski.drmario.game.entity.block;

import android.opengl.Matrix;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.game.entity.TexturedEntity;

public abstract class Block extends TexturedEntity {

    Block(float x, float y, float r, Global.BLOCK_COLOR color) {
        super(x, y, r, color);
    }

    @Override
    public String toStringType() {
        return "B";
    }

}
