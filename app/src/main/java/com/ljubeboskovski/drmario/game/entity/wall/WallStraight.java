package com.ljubeboskovski.drmario.game.entity.wall;

import com.ljubeboskovski.drmario.Global;

public class WallStraight extends Wall {

    public WallStraight(float x, float y, float r) {
        super(x, y, r);
        super.model = Global.Model.WALL_STRAIGHT;
    }

    @Override
    public String toStringType() { return "W"; }

}
