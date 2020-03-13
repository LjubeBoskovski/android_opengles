package com.ljubeboskovski.drmario.game.entity.block;

import com.ljubeboskovski.drmario.Global;

public class DoubleBlock extends Block {

    public DoubleBlock(int x, int y, Global.BLOCK_COLOR color) {
        super(x, y, color);
        super.model = Global.MODEL.getModel(color, Global.ENTITY_TYPE.DOUBLE);
    }

}
