package com.ljubeboskovski.drmario.game.entity;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.gfx.model.TexturedModel;

import java.util.ArrayList;
import java.util.Arrays;

public class AnimatedEntity extends TexturedEntity {

    protected int numberOfAnimations;
    protected int[] texMapCoordinates;
    protected float frequency;
    protected int counter;

    protected ArrayList<TexturedModel> models = new ArrayList<TexturedModel>();

    public AnimatedEntity(float x, float y, float r, Global.BLOCK_COLOR color) {
        super(x, y, r, color);
        counter = 0;
    }

    public void tick() {
        super.tick();
        counter++;
    }

    @Override
    public String toString() {
        return "AnimatedEntity{" +
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
    public TexturedModel getModel() {
        int ticksPerModel = (int) (Global.FRAMES_PER_SECOND / frequency);
        int ticksPerInterval = (int) ((numberOfAnimations * Global.FRAMES_PER_SECOND) / frequency);
        int textureModelIndex = ((counter % ticksPerInterval) / ticksPerModel) % numberOfAnimations;
        model = models.get(textureModelIndex);
        return super.getModel();
    }

    public int getNumberOfAnimations() {
        return numberOfAnimations;
    }

    public int[] getTexMapCoordinates() {
        return texMapCoordinates;
    }

    public void addModel(TexturedModel model) {
        models.add(model);
    }
}

