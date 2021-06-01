package com.ljubeboskovski.drmario.game.entity;

import android.opengl.Matrix;

import com.ljubeboskovski.drmario.Global;

import java.util.Arrays;

public abstract class Entity {
    protected float x, y, z, r, s;
    protected float rx, ry, rz, rr, rs;
    protected float[] mMatrix = new float[16];

    public Entity(float x, float y, float z, float r, float s) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.r = r;
        this.s = s;
        this.rx = x;
        this.ry = y;
        this.rz = z;
        this.rr = r;
        this.rs = s;
    }

    public Entity(float x, float y, float r){
         this(x, y, 1.0f, r, 1.0f);
    }

    public Entity() {
        this(0.0f, 0.0f, 0.0f);
    }

    public void update() {
        Matrix.setIdentityM(mMatrix, 0);
        Matrix.translateM(mMatrix, 0, rx + 0.5f, ry + 0.5f, rz);
        Matrix.scaleM(mMatrix, 0, rs, rs, 1.0f);
        Matrix.rotateM(mMatrix, 0, rr, 0.0f, 0.0f, 1.0f);
    }

    public void tick() {
        rx = getRenderVariable(x, rx, Global.RENDER_ELASTICITY_TRANSLATE);
        ry = getRenderVariable(y, ry, Global.RENDER_ELASTICITY_TRANSLATE);
        rz = getRenderVariable(z, rz, Global.RENDER_ELASTICITY_TRANSLATE);
        rs = getRenderVariable(s, rs, Global.RENDER_ELASTICITY_SCALE);
        rr = getRenderVariableRotation(r, rr);
    }

    private float getRenderVariable(float var, float renderVar, float elasticity) {
        float dVar = var - renderVar;
        if(Math.abs(dVar) < elasticity) {
            return var;
        } else {
            return renderVar + (Math.signum(dVar) * elasticity);
        }
    }

    private float getRenderVariableRotation(float var, float renderVar){
        float elasticity = Global.RENDER_ELASTICITY_ROTATE;
        float scope = 360.0f;

        float dVar = var - renderVar;
        if (Math.abs(dVar) > scope/2) {
            dVar = -1 * Math.signum(dVar) * (scope - Math.abs(dVar));
        }

        if(Math.abs(dVar) < elasticity * scope) {
            return var;
        } else {
            return (renderVar + (Math.signum(dVar) * elasticity * scope)) % scope;
        }
    }

    public void setPosRotScale(float x, float y, float z, float r, float s){
        this.x = x;
        this.y = y;
        this.z = z;
        this.r = r;
        this.s = s;
    }

    public void setRenderPosRotScale(float x, float y, float z, float r, float s){
        this.rx = x;
        this.ry = y;
        this.rz = z;
        this.rr = r;
        this.rs = s;
    }

    public void moveUp() {
        y++;
    }

    public void moveDown() { y--; }

    public void moveLeft() {
        x--;
    }

    public void moveRight() {
        x++;
    }

    public void rotateClockwise() {
        r += 270;
        r = r % 360;
    }

    public void rotateCounterClockwise() {
        r += 90;
        r = r % 360;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "x=" + x +
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

    public String toStringType() {
        return "E";
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
