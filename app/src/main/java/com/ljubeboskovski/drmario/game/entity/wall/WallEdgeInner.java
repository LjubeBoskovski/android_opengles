package com.ljubeboskovski.drmario.game.entity.wall;

import com.ljubeboskovski.drmario.Global;

public class WallEdgeInner extends Wall {

    public WallEdgeInner(float x, float y, float r) {
        super(x, y, r, Global.BLOCK_COLOR.TRANSPARENT);
        super.model = Global.Model.WALL_EDGE_INNER;
    }

    @Override
    public String toStringType() { return "W"; }

}
