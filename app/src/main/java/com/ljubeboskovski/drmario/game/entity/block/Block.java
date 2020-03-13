package com.ljubeboskovski.drmario.game.entity.block;

import android.opengl.Matrix;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.game.entity.TexturedEntity;

public abstract class Block extends TexturedEntity {

    private Global.BLOCK_COLOR color;

    Block(int x, int y, Global.BLOCK_COLOR color) {
        super(x, y);
        this.color = color;
    }

    @Override
    public void update() {
        Matrix.setIdentityM(mMatrix, 0);
        Matrix.translateM(mMatrix, 0, x + 0.5f, y + 0.5f, z);
        Matrix.scaleM(mMatrix, 0, s, s, 0);
        Matrix.rotateM(mMatrix, 0, r, 0.0f, 0.0f, 1.0f);
    }

    public Global.BLOCK_COLOR getColor() {
        return color;
    }
}
