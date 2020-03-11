package com.ljubeboskovski.drmario.game.entity.block;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.gfx.texture.TextureMap;

public class DoubleBlock extends Block {

    public DoubleBlock(int x, int y, Global.BLOCK_COLOR color) {
        super(x, y, color);
        super.textureMapX = 1;
    }

}
