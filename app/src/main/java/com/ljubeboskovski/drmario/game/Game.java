package com.ljubeboskovski.drmario.game;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.game.entity.Block;
import com.ljubeboskovski.drmario.game.world.World;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Game {

    public static Random random;

    private World world;
    private Block selectedBlock = null;
    private Timer timer;
    private int tickCounter = -1;

    public Game() {
        this.random = new Random();
        this.world = new World(9, 16);
        this.timer = new Timer("update");
    }

    public void start() {
        TimerTask count = new countTask();
        timer.scheduleAtFixedRate(count, 0, 1000 / Global.FRAMES_PER_SECOND);
    }

    public class countTask extends TimerTask {
        @Override
        public void run() {
            tickCounter++;
        }
    }

    public void update(){
        float scale =
                0.3f * (float) Math.sin(((float) tickCounter / Global.FRAMES_PER_SECOND) * 2f * (float) Math.PI) + 1.0f;
        for (Block block : world.getBlocks()) {
            if (block == selectedBlock) {
                block.update(scale);
            } else {
                block.update(1.0f);
            }
        }
    }

    public void touch(float x, float y) {
        int xBlock = (int) (x % 9);
        int yBlock = (int) (y % 16);

        selectedBlock = getSelectedBlock(xBlock, yBlock);
    }

    private Block getSelectedBlock(int x, int y) {
        for (Block block : world.getBlocks()) {
            if (block.getX() == x && block.getY() == y) {
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
