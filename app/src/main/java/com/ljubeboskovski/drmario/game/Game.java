package com.ljubeboskovski.drmario.game;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.game.entity.Entity;
import com.ljubeboskovski.drmario.game.entity.Pill;
import com.ljubeboskovski.drmario.game.entity.Virus;
import com.ljubeboskovski.drmario.game.entity.block.SingleBlock;
import com.ljubeboskovski.drmario.game.world.World;
import com.ljubeboskovski.drmario.input.InputHandler;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.ReadWriteLock;

public class Game {

    public static Random random;

    private InputHandler inputHandler;

    private final ReadWriteLock lock;
    private final Timer timer;
    private int tickCounter = -1;
    private float stepFrequency = 2.0f;
    private int lastFall = 0;

    private World world;
    private Pill controlledPill = null;

    public Game(ReadWriteLock lock) {
        this.lock = lock;
        random = new Random();
        this.timer = new Timer("count");

    }

    public void createWorld() {
        this.world = new World(Global.WORLD_SIZE_X, Global.WORLD_SIZE_Y);
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
                int ticksPerStep = (int) (Global.FRAMES_PER_SECOND / stepFrequency);
                if (tickCounter - lastFall > ticksPerStep) {
                    step();
                }
                if (controlledPill != null) {
                    controlledPill.tick();
                }

                for (Virus virus : world.getViruses()) {
                    virus.tick();
                }
                for (SingleBlock block : world.getSingleBlocks()) {
                    block.tick();
                }
                for (Pill pill : world.getPills()) {
                    pill.tick();
                }
                inputHandler.tick();
            } finally {
                lock.writeLock().unlock();
            }
        }
    }

    private void step() {
        lastFall = tickCounter;
        boolean isClearing = false;
        lock.writeLock().lock();
        try {
            isClearing = world.step();
        } finally {
            lock.writeLock().unlock();
            if (isClearing) {
                return;
            }
        }

        if (true) {//!isClearing) {//&& !isFalling) { //TODO
            if (controlledPill != null) {

                // The pill has reached the ground
                if (controlledPill.getY() == 0) {
                    land();
                    return;
                }

                int rotation = (int) controlledPill.getR();
                Entity belowPill = world.entityAt(controlledPill.getX(), controlledPill.getY() - 1);

                // The pill is falling vertically
                if (rotation == 0 || rotation == 180) {

                    // There is an entity below the pill
                    if (belowPill != null) {
                        land();
                        return;
                    }
                } else {    // The pill is falling horizontally
                    Entity belowRightPill = world.entityAt(controlledPill.getX() + 1,
                            controlledPill.getY() - 1);

                    // There is an entity directly below or below the right half of the pill
                    if (belowPill != null | belowRightPill != null) {
                        land();
                        return;
                    }
                }

                // If none of the above cases happen, then the pill falls down
                controlledPill.moveDown();
            } else {
                spawnControlledPill();
            }
        }
        world.step();
    }

    private void land() {
        lock.writeLock().lock();
        try {
            world.addPill(controlledPill);
            controlledPill = null;
        } finally {
            lock.writeLock().unlock();
        }
    }


    public void controlLeft() {
        if (controlledPill != null) {
            int rotation = (int) controlledPill.getR();
            Entity leftOfPill = world.entityAt(controlledPill.getX() - 1, controlledPill.getY());

            // The pill is positioned vertically
            if (rotation == 0 || rotation == 180) {
                Entity leftUpOfPill = world.entityAt(controlledPill.getX() - 1,
                        controlledPill.getY() + 1);
                // There is space to move
                if (leftOfPill == null && leftUpOfPill == null && controlledPill.getX() > 0) {
                    controlledPill.moveLeft();
                    return;
                }
            } else {    // The pill is positioned horizontally
                if (leftOfPill == null && controlledPill.getX() > 0) {
                    controlledPill.moveLeft();
                }
            }
        }
    }

    public void controlRight() {
        if (controlledPill != null) {
            int rotation = (int) controlledPill.getR();

            // The pill is positioned vertically
            if (rotation == 0 || rotation == 180) {
                Entity rightOfPill = world.entityAt(controlledPill.getX() + 1, controlledPill.getY());
                Entity rightUpOfPill = world.entityAt(controlledPill.getX() + 1,
                        controlledPill.getY() + 1);
                // There is space to move
                if (rightOfPill == null && rightUpOfPill == null && controlledPill.getX() < world.getSizeX() - 1) {
                    controlledPill.moveRight();
                    return;
                }
            } else {    // The pill is positioned horizontally
                Entity rightOfPill = world.entityAt(controlledPill.getX() + 2, controlledPill.getY());
                if (rightOfPill == null && controlledPill.getX() + 1 < world.getSizeX() - 1) {
                    controlledPill.moveRight();
                    return;
                }
            }
        }
    }

    public void controlDown() {
        step();
    }

    public void controlClockwise() {
        if (controlledPill != null) {
            int rotation = (int) controlledPill.getR();

            // The pill is positioned vertically
            if (rotation == 0 || rotation == 180) {
                Entity rightOfPill = world.entityAt(controlledPill.getX() + 1,
                        controlledPill.getY());
                // There is space to move
                if (rightOfPill == null && controlledPill.getX() < world.getSizeX() - 1) {
                    controlledPill.rotateClockwise();
                    return;
                }
            } else {    // The pill is positioned horizontally
                Entity upOfPill = world.entityAt(controlledPill.getX(), controlledPill.getY() + 1);
                if (upOfPill == null) {
                    controlledPill.rotateClockwise();
                    return;
                }
            }
        }
    }

    public void controlCounterClockwise() {
        if (controlledPill != null) {
            int rotation = (int) controlledPill.getR();

            // The pill is positioned vertically
            if (rotation == 0 || rotation == 180) {
                Entity rightOfPill = world.entityAt(controlledPill.getX() + 1,
                        controlledPill.getY());
                // There is space to move
                if (rightOfPill == null && controlledPill.getX() < world.getSizeX() - 1) {
                    controlledPill.rotateCounterClockwise();
                    return;
                }
            } else {    // The pill is positioned horizontally
                Entity upOfPill = world.entityAt(controlledPill.getX(), controlledPill.getY() + 1);
                if (upOfPill == null) {
                    controlledPill.rotateCounterClockwise();
                    return;
                }
            }
        }
    }

    private void spawnControlledPill() {
        Pill pill = new Pill(4, 14);
        controlledPill = pill;
    }


    public void update() {
        world.update();
        inputHandler.update();
        if (controlledPill != null) {
            controlledPill.update();
        }
    }

    public Pill getControlledPill() {
        return controlledPill;
    }

    public World getWorld() {
        return world;
    }

    public void setInputHandler(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

}
