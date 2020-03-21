package com.ljubeboskovski.drmario.game.world;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.game.Game;
import com.ljubeboskovski.drmario.game.entity.Entity;
import com.ljubeboskovski.drmario.game.entity.Pill;
import com.ljubeboskovski.drmario.game.entity.TexturedEntity;
import com.ljubeboskovski.drmario.game.entity.Virus;
import com.ljubeboskovski.drmario.game.entity.block.Block;
import com.ljubeboskovski.drmario.game.entity.block.DoubleBlock;
import com.ljubeboskovski.drmario.game.entity.block.SingleBlock;
import com.ljubeboskovski.drmario.gfx.texture.Texture;

import java.util.ArrayList;

public class World {

    private int sizeX, sizeY;
    private int numberOfViruses;

    private ArrayList<Virus> viruses = new ArrayList<Virus>();
    private ArrayList<Pill> pills = new ArrayList<Pill>();
    private ArrayList<SingleBlock> singleBlocks = new ArrayList<SingleBlock>();

    TexturedEntity[][] field;

    public World(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.numberOfViruses = 8;
        this.field = new TexturedEntity[sizeY][sizeX];
        generateBlocks();
    }

    private void generateBlocks() {
        for (int i = 0; i < numberOfViruses; i++) {
            int x = Game.random.nextInt(sizeX);
            int y = Game.random.nextInt(sizeY - 5);
            Global.BLOCK_COLOR color = Global.BlockColor.getRandomColor();
            Virus virus = new Virus(x, y, color);
            addVirus(virus);
        }
    }

    public void update() {
        for (Block block : singleBlocks) {
            block.update();
        }
        for (Virus virus : viruses) {
            virus.update();
        }
        for (Pill pill : pills) {
            pill.update();
        }
    }

    public boolean step() {
        boolean updated = false;
        updateField();
        updated = updateCleared();
        if (!updated) {
            updateFalling();
        }
        updateField();
        return updated;
    }

    private void updateField() {
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                field[y][x] = null;
            }
        }

        for (Virus virus : viruses) {
            field[(int) virus.getY()][(int) virus.getX()] = virus;
        }
        for (Pill pill : pills) {
            DoubleBlock block0 = pill.getBlockNorth();
            DoubleBlock block1 = pill.getBlockSouth();
            field[(int) block0.getY()][(int) block0.getX()] = block0;
            field[(int) block1.getY()][(int) block1.getX()] = block1;
        }
        for (Block block : singleBlocks) {
            field[(int) block.getY()][(int) block.getX()] = block;
        }
    }

    public boolean updateCleared() {
        boolean[][] fieldToBeRemoved = new boolean[sizeY][sizeX];
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                fieldToBeRemoved[y][x] = false;
            }
        }

//        ArrayList<Entity> toBeRemoved = new ArrayList<Entity>();
//        ArrayList<Entity> currentEntities = new ArrayList<Entity>();
        int sameInARow = 1;
        Global.BLOCK_COLOR currentColor = null;

        // Scan the rows first
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                // There is a row of more than 4 entities with the same color
                if (getFieldColor(x, y) != currentColor) {
                    if (sameInARow >= 4 && currentColor != null) {
//                        toBeRemoved.addAll(currentEntities);
                        for (int removedX = x - 1; removedX >= x - sameInARow; removedX--) {
                            fieldToBeRemoved[y][removedX] = true;
                        }
                    }
//                    currentEntities.clear();
//                    currentEntities.add(entityAt(x, y));
                    sameInARow = 1;
                    currentColor = getFieldColor(x, y);
                } else if (getFieldColor(x, y) == currentColor) {
                    if (currentColor != null) {
//                        currentEntities.add(entityAt(x, y));
                        sameInARow++;
                    }
                }
            }
            if (sameInARow >= 4 && currentColor != null) {
//                toBeRemoved.addAll(currentEntities);
                for (int removedX = sizeX - 2; removedX >= sizeX - sameInARow - 1; removedX--) {
                    fieldToBeRemoved[y][removedX] = true;
                }
            }
//            currentEntities.clear();
            sameInARow = 1;
            currentColor = null;
        }

        // Scan the columns second
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                if (getFieldColor(x, y) != currentColor) {
                    if (sameInARow >= 4 && currentColor != null) {
//                        toBeRemoved.addAll(currentEntities);
                        for (int removedY = y - 1; removedY >= y - sameInARow; removedY--) {
                            fieldToBeRemoved[removedY][x] = true;
                        }
                    }
//                    currentEntities.clear();
//                    currentEntities.add(entityAt(x, y));
                    sameInARow = 1;
                    currentColor = getFieldColor(x, y);
                } else if (getFieldColor(x, y) == currentColor) {
                    if (currentColor != null) {
//                        currentEntities.add(entityAt(x, y));
                        sameInARow++;
                    }
                }
            }
            if (sameInARow >= 4 && currentColor != null) {
//                toBeRemoved.addAll(currentEntities);
                for (int removedY = sizeY - 2; removedY >= sizeY - sameInARow - 1; removedY--) {
                    fieldToBeRemoved[removedY][x] = true;
                }
            }
//            currentEntities.clear();
//            sameInARow = 1;
//            currentColor = null;
        }

//        for (int x = 0; x < sizeX; x++) {
//            for (int y = 0; y < sizeY; y++) {
//                // There is a row of more than 4 entities with the same color
//                if (field[y][x] != currentColor) {
//                    if (sameInARow >= 4 && currentColor != null) {
//                        toBeRemoved.addAll(currentEntities);
//                    }
//                    currentEntities.clear();
//                    currentEntities.add(entityAt(x, y));
//                    sameInARow = 1;
//                    currentColor = field[y][x];
//                } else if (field[y][x] == currentColor) {
//                    if (currentColor != null) {
//                        currentEntities.add(entityAt(x, y));
//                        sameInARow++;
//                    }
//                }
//            }
//            if (sameInARow >= 4 && currentColor != null) {
//                toBeRemoved.addAll(currentEntities);
//            }
//            currentEntities.clear();
//            sameInARow = 1;
//            currentColor = null;
//        }

        boolean wasRemoved = false;
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                if (fieldToBeRemoved[y][x]) {
                    wasRemoved = true;
                    TexturedEntity entity = field[y][x];
                    if (entity instanceof Virus) {
                        viruses.remove(entity);
                    }
                    if (entity instanceof SingleBlock) {
                        singleBlocks.remove(entity);
                    }
                    ArrayList<Pill> pillsToBeRemoved = new ArrayList<Pill>();
                    if (entity instanceof DoubleBlock) {
                        for (Pill pill : pills) {
                            if (pill.getBlockNorth() == entity) {
                                DoubleBlock block = pill.getBlockSouth();
                                SingleBlock newBlock = new SingleBlock((int) block.getX(),
                                        (int) block.getY(), block.getColor());
                                singleBlocks.add(newBlock);
                                field[(int)newBlock.getY()][(int)newBlock.getX()] = newBlock;
                                pillsToBeRemoved.add(pill);
                            }
                            if (pill.getBlockSouth() == entity) {
                                DoubleBlock block = pill.getBlockNorth();
                                SingleBlock newBlock = new SingleBlock((int) block.getX(),
                                        (int) block.getY(), block.getColor());
                                singleBlocks.add(newBlock);
                                field[(int)newBlock.getY()][(int)newBlock.getX()] = newBlock;
                                pillsToBeRemoved.add(pill);
                            }
                        }
                        for (Pill pill : pillsToBeRemoved) {
                            pills.remove(pill);
                        }
                    }
                }
            }

//        for (Entity entity : toBeRemoved) {
//            if (entity instanceof Virus) {
//                viruses.remove(entity);
//            }
//            if (entity instanceof SingleBlock) {
//                singleBlocks.remove(entity);
//            }
//            ArrayList<Pill> pillsToBeRemoved = new ArrayList<Pill>();
//            if (entity instanceof DoubleBlock) {
//                for (Pill pill : pills) {
//                    if (pill.getBlockNorth() == entity) {
//                        DoubleBlock block = pill.getBlockSouth();
//                        SingleBlock newBlock = new SingleBlock((int) block.getX(),
//                                (int) block.getY(), block.getColor());
//                        singleBlocks.add(newBlock);
//                        pillsToBeRemoved.add(pill);
//                    }
//                    if (pill.getBlockSouth() == entity) {
//                        DoubleBlock block = pill.getBlockNorth();
//                        SingleBlock newBlock = new SingleBlock((int) block.getX(),
//                                (int) block.getY(), block.getColor());
//                        singleBlocks.add(newBlock);
//                        pillsToBeRemoved.add(pill);
//                    }
//                }
//                for (Pill pill : pillsToBeRemoved) {
//                    pills.remove(pill);
//                }
//            }
//        }
        }

//        return !toBeRemoved.isEmpty();
        updateField();
        return wasRemoved;
    }

    public boolean updateFalling() {
        boolean isFalling = false;
        for (SingleBlock block : singleBlocks) {
            if (entityAt(block.getX(), block.getY() - 1) == null && block.getY() > 0) {
                block.moveDown();
                isFalling = true;
            }
        }
        for (Pill pill : pills) {
            int rotation = (int) pill.getR();

            // The pill is positioned vertically
            if (rotation == 0 || rotation == 180) {
                if (entityAt(pill.getX(), pill.getY() - 1) == null && pill.getY() > 0) {
                    pill.moveDown();
                    isFalling = true;
                }
            } else { // The pill is positioned horizontally
                int x = (int) pill.getX();
                int y = (int) pill.getY();
                if (entityAt(x, y - 1) == null && entityAt(x + 1, y - 1) == null && y > 0) {
                    pill.moveDown();
                    isFalling = true;
                }
            }
        }
        return isFalling;
    }

    public TexturedEntity entityAt(int x, int y) {
        if(x >= 0 && x < sizeX && y >= 0 && y < sizeY) {
            return field[y][x];
        } else {
            return null;
        }
//        for (Block block : singleBlocks) {
//            if (block.getX() == x && block.getY() == y) {
//                return block;
//            }
//        }
//        for (Virus virus : viruses) {
//            if (virus.getX() == x && virus.getY() == y) {
//                return virus;
//            }
//        }
//        for (Pill pill : pills) {
//            int rotation = (int) pill.getR();
//            // The pill is positioned vertically
//            if (rotation == 0 || rotation == 180) {
//                // Lower DoubleBlock
//                if (pill.getX() == x && pill.getY() == y) {
//                    return rotation == 0 ? pill.getBlockSouth() : pill.getBlockNorth();
//                }
//                // Upper DoubleBlock
//                if (pill.getX() == x && pill.getY() + 1 == y) {
//                    return rotation == 0 ? pill.getBlockNorth() : pill.getBlockSouth();
//                }
//            } else {    // The pill is positioned horizontally
//                // Left DoubleBlock
//                if (pill.getX() == x && pill.getY() == y) {
//                    return rotation == 90 ? pill.getBlockSouth() : pill.getBlockNorth();
//                }
//                // Right DoubleBlock
//                if (pill.getX() + 1 == x && pill.getY() == y) {
//                    return rotation == 90 ? pill.getBlockNorth() : pill.getBlockSouth();
//                }
//            }
//        }
//        return null;
    }

    public TexturedEntity entityAt(float x, float y) {
        return entityAt((int) x, (int) y);
    }

    private Global.BLOCK_COLOR getFieldColor(int x, int y) {
        if (field[y][x] != null) {
            return field[y][x].getColor();
        } else {
            return null;
        }
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public ArrayList<SingleBlock> getSingleBlocks() {
        return singleBlocks;
    }

    public ArrayList<Virus> getViruses() {
        return viruses;
    }

    public ArrayList<Pill> getPills() {
        return pills;
    }

    public void addVirus(Virus virus) {
        viruses.add(virus);
    }

    public void addPill(Pill pill) {
        pills.add(pill);
    }

}
