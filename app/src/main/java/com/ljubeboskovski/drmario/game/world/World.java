package com.ljubeboskovski.drmario.game.world;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.game.Game;
import com.ljubeboskovski.drmario.game.entity.Virus;
import com.ljubeboskovski.drmario.game.entity.block.Block;
import com.ljubeboskovski.drmario.game.entity.block.SingleBlock;

import java.util.ArrayList;

public class World {

    private int sizeX, sizeY;

    private ArrayList<Block> blocks = new ArrayList<Block>();
    private ArrayList<Virus> viruses = new ArrayList<Virus>();

    public World(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        generateBlocks();
    }

    private void generateBlocks() {
        for (int yi = 0; yi < sizeY; yi++) {
            for (int xi = 0; xi < sizeX; xi++) {
                int randomInteger = Game.random.nextInt(4);
                Global.BLOCK_COLOR newColor;
                switch(randomInteger) {
                    case 0:
                        newColor = Global.BLOCK_COLOR.RED;
                        break;
                    case 1:
                        newColor = Global.BLOCK_COLOR.YELLOW;
                        break;
                    case 2:
                        newColor = Global.BLOCK_COLOR.BLUE;
                        break;
                    default:
                        newColor = Global.BLOCK_COLOR.GREEN;
                        break;
                }
                Block newBlock = new SingleBlock(xi, yi, newColor);
                blocks.add(newBlock);
            }
        }
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public ArrayList<Virus> getViruses() {
        return viruses;
    }

    public void addVirus(Virus virus) {
        viruses.add(virus);

    }
}
