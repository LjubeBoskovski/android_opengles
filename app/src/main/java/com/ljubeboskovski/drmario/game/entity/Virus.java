package com.ljubeboskovski.drmario.game.entity;

import com.ljubeboskovski.drmario.Global;

public class Virus extends AnimatedEntity {

    private Global.BLOCK_COLOR color;
    private float[] colorVector;

    public Virus(int x, int y, Global.BLOCK_COLOR color){
        super(x, y);
        this.color = color;
        numberOfAnimations = 2;
        switch (color) {
            case RED:
                texMapCoordinates = new int[] {2, 0, 3, 0};
                colorVector = Global.BlockColor.RED;
                break;
            case YELLOW:
                texMapCoordinates = new int[] {2, 1, 3, 1};
                colorVector = Global.BlockColor.YELLOW;
                break;
            case BLUE:
                texMapCoordinates = new int[] {2, 2, 3, 2};
                colorVector = Global.BlockColor.BLUE;
                break;
            default:
                texMapCoordinates = new int[] {2, 3, 3, 3};
                colorVector = Global.BlockColor.GREEN;
                break;
        }
        frequency = 2.0f;
    }

    public float[] getColorVector() {
        return colorVector;
    }
}

