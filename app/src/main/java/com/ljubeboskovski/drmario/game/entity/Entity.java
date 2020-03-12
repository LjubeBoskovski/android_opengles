package com.ljubeboskovski.drmario.game.entity;

import android.opengl.Matrix;

public abstract class Entity {

    protected float x, y, z, r, s;
    protected float[] mMatrix = new float[16];

    public Entity(float x, float y, float z, float r, float s) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.r = r;
        this.s = s;
    }

    public Entity(float x, float y){
         this(x, y, 1.0f, 0.0f, 1.0f);
    }

    public Entity() {
        this(0.0f, 0.0f);
    }

    public void update() {
        Matrix.setIdentityM(mMatrix, 0);
        Matrix.translateM(mMatrix, 0, x + 0.5f, y + 0.5f, z);
        Matrix.scaleM(mMatrix, 0, s, s, 0);
        Matrix.rotateM(mMatrix, 0, r, 0.0f, 0.0f, 1.0f);
    }

    public void setPosRotScale(float x, float y, float z, float r, float s){
        this.x = x;
        this.y = y;
        this.z = z;
        this.r = r;
        this.s = s;
    }

    public void moveUp() {
        y++;
    }

    public void moveDown() {
        y--;
    }

    public void moveLeft() {
        x--;
    }

    public void moveRight() {
        x++;
    }

    public void rotateClockwise() {
        r -= 90;
    }

    public void rotateCounterClockwise() {
        r += 90;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getR() {
        return r;
    }

    public float getS() {
        return s;
    }

    public float[] getmMatrix() {
        return mMatrix;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public void setR(float r) {
        this.r = r;
    }

    public void setS(float s) {
        this.s = s;
    }

    public void setmMatrix(float[] mMatrix) {
        this.mMatrix = mMatrix;
    }

}
