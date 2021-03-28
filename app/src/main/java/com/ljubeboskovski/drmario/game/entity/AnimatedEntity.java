package com.ljubeboskovski.drmario.game.entity;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.gfx.model.TexturedModel;

import java.util.ArrayList;

public class AnimatedEntity extends TexturedEntity {

    protected int numberOfAnimations;
    protected int[] texMapCoordinates;
    protected float frequency;
    protected int counter;

    protected ArrayList<TexturedModel> models = new ArrayList<TexturedModel>();

    public AnimatedEntity(float x, float y, float r) {
        super(x, y, r);
        counter = 0;
    }

    public void tick() {
        super.tick();
        counter++;
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

