package com.ljubeboskovski.drmario.game.world;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.game.Game;
import com.ljubeboskovski.drmario.game.entity.ColoredBlock;

import java.util.ArrayList;

public class World {

    private int sizeX, sizeY;

    private ArrayList<ColoredBlock> blocks = new ArrayList<ColoredBlock>();

    public World(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        generateBlocks();
    }

    private void generateBlocks() {
        for (int yi = 0; yi < sizeY; yi++) {
            for (int xi = 0; xi < sizeX; xi++) {
                int randomInteger = Game.random.nextInt(4);
                float[] newColor;
                switch(randomInteger) {
                    case 0:
                        newColor = Global.BlockColor.RED;
                        break;
                    case 1:
                        newColor = Global.BlockColor.YELLOW;
                        break;
                    case 2:
                        newColor = Global.BlockColor.BLUE;
                        break;
                    default:
                        newColor = Global.BlockColor.GREEN;
                        break;
                }
                ColoredBlock newBlock = new ColoredBlock(xi, yi, newColor);
                blocks.add(newBlock);
            }
        }
    }

    public ArrayList<ColoredBlock> getBlocks() {
        return blocks;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

}
