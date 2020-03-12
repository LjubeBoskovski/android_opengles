package com.ljubeboskovski.drmario.game;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.game.entity.Pill;
import com.ljubeboskovski.drmario.game.entity.Virus;
import com.ljubeboskovski.drmario.game.entity.block.Block;
import com.ljubeboskovski.drmario.game.world.World;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Game {

    public static Random random;
    private Timer timer;
    private int tickCounter = -1;
    private float fallingFrequency = 1.0f;
    private int lastFall = 0;

    private World world;
    private Block selectedBlock = null;
    private Pill controlledPill = null;

    public Game() {
        this.random = new Random();
        this.world = new World(9, 16);
        this.timer = new Timer("count");

        Pill startingPill = new Pill(4, 15);
        world.addPill(startingPill);
        controlledPill = startingPill;
    }

    public void start() {
        TimerTask tick = new tickTask();
        timer.scheduleAtFixedRate(tick, 0, 1000 / Global.FRAMES_PER_SECOND);
    }

    public class tickTask extends TimerTask {
        @Override
        public void run() {
            tickCounter++;

            int ticksPerFall = (int) (Global.FRAMES_PER_SECOND / fallingFrequency);
            if (tickCounter - lastFall > ticksPerFall) {
                fall();
                lastFall = tickCounter;
            }

            for (Virus virus : world.getViruses()) {
                virus.tick();
            }
        }
    }

    private void fall() {
        if (controlledPill != null) {
            controlledPill.moveDown();
        }
    }

    public void update() {
        for (Block block : world.getBlocks()) {
            block.update();
        }
        for (Virus virus : world.getViruses()) {
            virus.update();
        }
        for (Pill pill : world.getPills()) {
            pill.update();
        }
    }

    public void touch(float x, float y) {
        if (controlledPill != null) {
            if (y > 0.5) {
                if (x < 0.4) {
                    controlledPill.moveLeft();
                } else if (x > 0.6) {
                    controlledPill.moveRight();
                } else {
                    controlledPill.moveDown();
                }
            } else {
                if(x < 0.5) {
                    controlledPill.rotateCounterClockwise();
                } else {
                    controlledPill.rotateClockwise();
                }

            }
        }
    }
//        int xBlock = (int) (x % 9);
//        int yBlock = (int) (y % 16);
//
//        selectedBlock = getSelectedBlock(xBlock, yBlock);

            private Block getSelectedBlock ( int x, int y){
                for (Block block : world.getBlocks()) {
                    if (block.getX() == x && block.getY() == y) {
                        return block;
                    }
                }
                return null;
            }


            public World getWorld () {
                return world;
            }
        }
