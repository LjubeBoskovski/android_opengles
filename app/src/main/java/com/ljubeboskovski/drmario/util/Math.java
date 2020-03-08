package com.ljubeboskovski.drmario.util;

public class Math {
    public static float sigmoid(float x){
        return (float)(1 / (1 + java.lang.Math.exp(-x)));
    }


}
