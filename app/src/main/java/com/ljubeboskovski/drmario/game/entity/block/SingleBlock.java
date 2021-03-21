package com.ljubeboskovski.drmario.game.entity.block;

import com.ljubeboskovski.drmario.Global;

public class SingleBlock extends Block {

    public SingleBlock(float x, float y, float r, Global.BLOCK_COLOR color) {
        super(x, y, r, color);
        super.model = Global.Model.getModel(color, Global.ENTITY_TYPE.SINGLE);
    }

    @Override
    public String toStringType() {
        return "S";
    }
}
