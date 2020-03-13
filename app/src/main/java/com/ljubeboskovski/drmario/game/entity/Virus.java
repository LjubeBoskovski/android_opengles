package com.ljubeboskovski.drmario.game.entity;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.gfx.model.TexturedModel;

public class Virus extends AnimatedEntity {

    private Global.BLOCK_COLOR color;

    public Virus(int x, int y, Global.BLOCK_COLOR color){
        super(x, y);
        this.color = color;
        TexturedModel texturedModel0 = Global.MODEL.getModel(color, Global.ENTITY_TYPE.VIRUS_0);
        TexturedModel texturedModel1 = Global.MODEL.getModel(color, Global.ENTITY_TYPE.VIRUS_1);
        super.addModel(texturedModel0);
        super.addModel(texturedModel1);
        numberOfAnimations = 2;
        frequency = 2.0f;
    }

    public Global.BLOCK_COLOR getColor() {
        return color;
    }
}

