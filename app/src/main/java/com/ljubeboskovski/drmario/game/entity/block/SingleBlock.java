package com.ljubeboskovski.drmario.game.entity.block;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.game.entity.block.Block;
import com.ljubeboskovski.drmario.gfx.texture.TextureMap;

public class SingleBlock extends Block {

    public SingleBlock(float x, float y, float r, Global.BLOCK_COLOR color) {
        super(x, y, r, color);
        super.model = Global.MODEL.getModel(color, Global.ENTITY_TYPE.SINGLE);
    }

    @Override
    public String toStringType() {
        return "S";
    }

}
