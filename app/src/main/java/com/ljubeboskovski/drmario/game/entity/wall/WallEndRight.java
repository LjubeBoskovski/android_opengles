package com.ljubeboskovski.drmario.game.entity.wall;

import com.ljubeboskovski.drmario.Global;

public class WallEndRight extends Wall {

    public WallEndRight(float x, float y, float r) {
        super(x, y, r);
        super.model = Global.Model.WALL_END_RIGHT;
    }

    @Override
    public String toStringType() { return "W"; }

}
