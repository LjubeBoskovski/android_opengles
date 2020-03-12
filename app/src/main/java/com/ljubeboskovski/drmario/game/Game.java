package com.ljubeboskovski.drmario.game;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.game.entity.Virus;
import com.ljubeboskovski.drmario.game.entity.block.Block;
import com.ljubeboskovski.drmario.game.entity.block.ColoredBlock;
import com.ljubeboskovski.drmario.game.world.World;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Game {

    public static Random random;
    private Timer timer;
    private int tickCounter = -1;

    private World world;
    private Block selectedBlock = null;

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
            for(Virus virus : world.getViruses()){
                virus.incCounter();
            }
        }
    }

    public void update() {
        for (Block block : world.getBlocks()) {
            block.update();
        }
        for(Virus virus : world.getViruses()){
            virus.update();
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
}
