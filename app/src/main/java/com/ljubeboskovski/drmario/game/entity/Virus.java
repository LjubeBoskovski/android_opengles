package com.ljubeboskovski.drmario.game.entity;

import com.ljubeboskovski.drmario.Global;

public class Virus extends Entity {
    private float x, y, z;
    private Global.BLOCK_COLOR color;

    public Virus(int x, int y, Global.BLOCK_COLOR color){
        this.x = x;
        this.y = y;
        this.z = 1.0f;
        this.color = color;
    }

}
