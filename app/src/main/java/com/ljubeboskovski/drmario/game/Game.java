package com.ljubeboskovski.drmario.game;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.game.entity.Entity;
import com.ljubeboskovski.drmario.game.entity.Pill;
import com.ljubeboskovski.drmario.game.entity.Virus;
import com.ljubeboskovski.drmario.game.entity.block.Block;
import com.ljubeboskovski.drmario.game.world.World;
import com.ljubeboskovski.drmario.gfx.Loader;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.ReadWriteLock;

public class Game {

    public static Random random;

    private Loader loader;
    private ReadWriteLock lock;
    private Timer timer;
    private int tickCounter = -1;
    private float fallingFrequency = 1.0f;
    private int lastFall = 0;

    private World world;
    private Block selectedBlock = null;
    private Pill controlledPill = null;

    public Game(ReadWriteLock lock) {
        this.lock = lock;
        this.random = new Random();
        this.timer = new Timer("count");

    }

    public void createWorld() {
        this.world = new World(9, 16);
        spawnControlledPill();
    }

    public void start() {
        TimerTask tick = new tickTask();
        timer.scheduleAtFixedRate(tick, 0, 1000 / Global.FRAMES_PER_SECOND);
    }

    public class tickTask extends TimerTask {
        @Override
        public void run() {
            tickCounter++;

            lock.writeLock().lock();
            try {
                int ticksPerFall = (int) (Global.FRAMES_PER_SECOND / fallingFrequency);
                if (tickCounter - lastFall > ticksPerFall) {
                    step();
                    lastFall = tickCounter;
                }

                for (Virus virus : world.getViruses()) {
                    virus.tick();
                }
            } finally {
                lock.writeLock().unlock();
            }
        }
    }

    private void step() {
        if (controlledPill != null) {
            int rotation = (int) controlledPill.getR();
            if (rotation == 0 || rotation == 180) {
                Entity belowPill = entityAt(controlledPill.getX(), controlledPill.getY() - 1);

                if (belowPill != null) {
                    world.addPill(controlledPill);
                    controlledPill = null;
                    return;
                }
            }
            controlledPill.moveDown();
        } else {
            spawnControlledPill();
        }
    }

    private void spawnControlledPill() {
        Pill pill = new Pill(4, 15);
        controlledPill = pill;
    }

    private Entity entityAt(float x, float y) {
        for (Block block : world.getBlocks()) {
            if (block.getX() == x && block.getY() == y) {
                return block;
            }
        }
        for (Pill pill : world.getPills()) {
            if (pill.getX() == x && pill.getY() == y) {
                return pill;
            }
        }
        for (Virus virus : world.getViruses()) {
            if (virus.getX() == x && virus.getY() == y) {
                return virus;
            }
        }
        return null;
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
        if (controlledPill != null) {
            controlledPill.update();
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
                if (x < 0.5) {
                    controlledPill.rotateCounterClockwise();
                } else {
                    controlledPill.rotateClockwise();
                }

            }
        }
    }

    private Block getSelectedBlock(int x, int y) {
        for (Block block : world.getBlocks()) {
            if (block.getX() == x && block.getY() == y) {
                return block;
            }
        }
        return null;
    }

    public Pill getControlledPill() {
        return controlledPill;
    }

    public World getWorld() {
        return world;
    }

    public void setLoader(Loader loader) {
        this.loader = loader;
    }
}
