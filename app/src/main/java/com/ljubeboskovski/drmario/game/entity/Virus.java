package com.ljubeboskovski.drmario.game.entity;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.gfx.model.TexturedModel;

import java.util.Arrays;

public class Virus extends AnimatedEntity {

    private Global.BLOCK_COLOR color;

    public Virus(int x, int y, Global.BLOCK_COLOR color){
        super(x, y, 0);
        this.color = color;
        TexturedModel texturedModel0 = Global.Model.getModel(color, Global.ENTITY_TYPE.VIRUS_0);
        TexturedModel texturedModel1 = Global.Model.getModel(color, Global.ENTITY_TYPE.VIRUS_1);
        super.addModel(texturedModel0);
        super.addModel(texturedModel1);
        numberOfAnimations = 2;
        frequency = 2.0f;
    }

    @Override
    public String toString() {
        return "Virus{" +
                "numberOfAnimations=" + numberOfAnimations +
                ", texMapCoordinates=" + Arrays.toString(texMapCoordinates) +
                ", frequency=" + frequency +
                ", counter=" + counter +
                ", models=" + models +
                ", model=" + model +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", r=" + r +
                ", s=" + s +
                ", rx=" + rx +
                ", ry=" + ry +
                ", rz=" + rz +
                ", rr=" + rr +
                ", rs=" + rs +
                ", mMatrix=" + Arrays.toString(mMatrix) +
                '}';
    }

    @Override
    public String toStringType() {
        return "V";
    }

    public Global.BLOCK_COLOR getColor() {
        return color;
    }

}

