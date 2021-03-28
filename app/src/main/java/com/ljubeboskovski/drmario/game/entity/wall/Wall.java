package com.ljubeboskovski.drmario.game.entity.wall;

import android.opengl.Matrix;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.game.entity.TexturedEntity;

public abstract class Wall extends TexturedEntity {

    Wall(float x, float y, float r) {
        super(x, y, r);
    }

    @Override
    public String toStringType() {
        return "W";
    }

}
