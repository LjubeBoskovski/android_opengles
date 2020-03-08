package com.ljubeboskovski.drmario.game;

import android.util.Log;

import com.ljubeboskovski.drmario.game.entity.Block;
import com.ljubeboskovski.drmario.game.world.World;

import java.util.Random;

public class Game {

    public static Random random;

    private World world;
    private Block selectedBlock;

    public Game() {
        this.random = new Random();
        this.world = new World(9, 16);
    }

    public void tick() {

    }

    public void touch(float x, float y) {
        int xBlock = (int) (x % 9);
        int yBlock = (int) (y % 16);

        selectedBlock = getSelectedBlock(xBlock, yBlock);
    }

    private Block getSelectedBlock(int x, int y) {
        for(Block block : world.getBlocks()){
            if(block.getX() == x && block.getY() == y){
                return block;
            }
        }
        return null;
    }


    public World getWorld() {
        return world;
    }

    public Block getSelectedBlock() {
        return selectedBlock;
    }

}
