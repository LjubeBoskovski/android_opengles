package com.ljubeboskovski.drmario.game.entity.block;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.game.entity.block.Block;
import com.ljubeboskovski.drmario.gfx.texture.TextureMap;

public class SingleBlock extends Block {

    public SingleBlock(int x, int y, Global.BLOCK_COLOR color) {
        super(x, y, color);
        super.textureMapX = 0;
    }

}