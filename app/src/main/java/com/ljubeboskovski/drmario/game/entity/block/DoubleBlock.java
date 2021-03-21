package com.ljubeboskovski.drmario.game.entity.block;

import com.ljubeboskovski.drmario.Global;

public class DoubleBlock extends Block {

    public DoubleBlock(float x, float y, float r, Global.BLOCK_COLOR color) {
        super(x, y, r, color);
        super.model = Global.Model.getModel(color, Global.ENTITY_TYPE.DOUBLE);
    }

    @Override
    public String toStringType() {
        return "D";
    }

}
