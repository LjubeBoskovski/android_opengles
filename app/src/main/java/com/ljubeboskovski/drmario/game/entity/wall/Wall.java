package com.ljubeboskovski.drmario.game.entity.wall;

import android.opengl.Matrix;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.game.entity.TexturedEntity;

public abstract class Wall extends TexturedEntity {

    Wall(float x, float y, float r, Global.BLOCK_COLOR color) {
        super(x, y, r, color);
    }

    @Override
    public String toStringType() {
        return "W";
    }

}
