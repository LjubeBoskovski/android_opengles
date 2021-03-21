package com.ljubeboskovski.drmario.game.entity.wall;

import com.ljubeboskovski.drmario.Global;

public class WallEndLeft extends Wall {

    public WallEndLeft(float x, float y, float r) {
        super(x, y, r, Global.BLOCK_COLOR.TRANSPARENT);
        super.model = Global.Model.WALL_END_LEFT;
    }

    @Override
    public String toStringType() { return "W"; }

}
