package com.ljubeboskovski.drmario.game.entity;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.game.entity.block.DoubleBlock;

import java.util.Arrays;

public class Pill extends Entity {


    boolean isFloating;


    private DoubleBlock blockNorth;
    private DoubleBlock blockSouth;


    public Pill(int x, int y, Global.BLOCK_COLOR colorNorth, Global.BLOCK_COLOR colorSouth) {
        this.blockNorth = new DoubleBlock(x, y + 1, 270, colorNorth);
        this.blockSouth = new DoubleBlock(x, y, 90, colorSouth);
        this.x = x;
        this.y = y;
        this.r = 0.0f;
        this.s = 1.0f;
        this.z = s;
        isFloating = true;
    }

    public Pill(int x, int y) {
        this(x, y, Global.BlockColor.getRandomColor(), Global.BlockColor.getRandomColor());
    }

    @Override
    public void tick() {
        super.tick();

//        if (0 <= rr && rr < 90) {
//            float xd = (float) Math.sin(Math.toRadians(rr));
//            float yd = (float) Math.cos(Math.toRadians(rr));
//
//            blockNorth.setRenderPosRotScale(rx + xd * rs, ry + yd * rs, rz, -rr - 90, rs);
//            blockSouth.setRenderPosRotScale(rx, ry, rz, -rr + 90, rs);
//        }
//        if (180 <= rr && rr < 270) {
//            float xd = (float) Math.sin(Math.toRadians(rr + 180));
//            float yd = (float) Math.cos(Math.toRadians(rr+ 180));
//
//            blockSouth.setRenderPosRotScale(rx + xd * rs, ry + yd * rs, rz, -rr + 90, rs);
//            blockNorth.setRenderPosRotScale(rx, ry, rz, -rr - 90, rs);
//        }
//        if(90 <= rr && rr < 180) {
//            float alpha = 180 - rr;
//            float opposite = (float) Math.sin(Math.toRadians(alpha));
//            float adjacent = (float) Math.cos(Math.toRadians(alpha));
//            blockSouth.setRenderPosRotScale(rx, ry + adjacent * rs, rz, -rr + 90, rs);
//            blockNorth.setRenderPosRotScale(rx + opposite * rs, ry, rz, -rr - 90, rs);
//        }
//        if(270 <= rr && rr < 360) {
//            float alpha = - rr;
//            float opposite = (float) Math.sin(Math.toRadians(alpha));
//            float adjacent = (float) Math.cos(Math.toRadians(alpha));
//            blockNorth.setRenderPosRotScale(rx, ry + adjacent * rs, rz, -rr - 90, rs);
//            blockSouth.setRenderPosRotScale(rx + opposite * rs, ry, rz, -rr + 90, rs);
//        }

        blockNorth.tick();
        blockSouth.tick();
    }

    @Override
    public void update() {
        super.update();
        z = isFloating ? s + 1.0f : 1.0f;

        blockNorth.update();
        blockSouth.update();
    }

    @Override
    public void setPosRotScale(float x, float y, float z, float r, float s) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.r = r;
        this.s = s;
    }

    @Override
    public void moveUp() {
        blockNorth.moveUp();
        blockSouth.moveUp();
        super.moveUp();
    }

    @Override
    public void moveDown() {
        blockNorth.moveDown();
        blockSouth.moveDown();
        super.moveDown();
    }

    @Override
    public void moveLeft() {
        blockNorth.moveLeft();
        blockSouth.moveLeft();
        super.moveLeft();
    }

    @Override
    public void moveRight() {
        blockNorth.moveRight();
        blockSouth.moveRight();
        super.moveRight();
    }

    @Override
    public void rotateClockwise() {
        switch((int)(this.r)){
            case 0:
                blockNorth.moveRight();
                blockNorth.moveDown();
                blockNorth.rotateClockwise();
                blockSouth.rotateClockwise();
                break;
            case 90:
                blockNorth.moveUp();
                blockNorth.rotateClockwise();
                blockSouth.moveLeft();
                blockSouth.rotateClockwise();
                break;
            case 180:
                blockSouth.moveRight();
                blockSouth.moveDown();
                blockSouth.rotateClockwise();
                blockNorth.rotateClockwise();
                break;
            case 270:
                blockSouth.moveUp();
                blockSouth.rotateClockwise();
                blockNorth.moveLeft();
                blockNorth.rotateClockwise();
                break;
        }
        super.rotateClockwise();
    }

    @Override
    public void rotateCounterClockwise() {
        switch((int)(this.r)){
            case 0:
                blockNorth.moveDown();
                blockNorth.rotateCounterClockwise();
                blockSouth.moveRight();
                blockSouth.rotateCounterClockwise();
                break;
            case 90:
                blockNorth.rotateCounterClockwise();
                blockSouth.moveUp();
                blockSouth.moveLeft();
                blockSouth.rotateCounterClockwise();
                break;
            case 180:
                blockSouth.moveDown();
                blockSouth.rotateCounterClockwise();
                blockNorth.moveRight();
                blockNorth.rotateCounterClockwise();
                break;
            case 270:
                blockSouth.rotateCounterClockwise();
                blockNorth.moveUp();
                blockNorth.moveLeft();
                blockNorth.rotateCounterClockwise();
                break;
        }
        super.rotateCounterClockwise();
    }

    @Override
    public String toString() {
        return "Pill{" +
                "isFloating=" + isFloating +
                ", blockNorth=" + blockNorth +
                ", blockSouth=" + blockSouth +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", r=" + r +
                ", s=" + s +
                ", rx=" + rx +
                ", ry=" + ry +
                ", rz=" + rz +
                ", rr=" + rr +
                ", rs=" + rs +
                ", mMatrix=" + Arrays.toString(mMatrix) +
                '}';
    }

    @Override
    public String toStringType() {
        return "P";
    }

    public boolean isFloating() {
        return isFloating;
    }

    public DoubleBlock getBlockNorth() {
        return blockNorth;
    }

    public DoubleBlock getBlockSouth() {
        return blockSouth;
    }

    public void setFloating(boolean floating) {
        isFloating = floating;
    }

    public void setBlockNorth(DoubleBlock blockNorth) {
        this.blockNorth = blockNorth;
    }

    public void setBlockSouth(DoubleBlock blockSouth) {
        this.blockSouth = blockSouth;
    }

    @Override
    public void setY(float y) {
        setPosRotScale(x, y, z, r, s);
    }
}
