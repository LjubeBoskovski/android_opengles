package com.ljubeboskovski.drmario.game.entity.wall;

import com.ljubeboskovski.drmario.Global;

public class WallEdgeOuter extends Wall {

    public WallEdgeOuter(float x, float y, float r) {
        super(x, y, r);
        super.model = Global.Model.WALL_EDGE_OUTER;
    }

    @Override
    public String toStringType() { return "W"; }

}
