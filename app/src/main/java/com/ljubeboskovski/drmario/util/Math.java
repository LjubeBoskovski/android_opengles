package com.ljubeboskovski.drmario.util;

public class Math {
    public static float sigmoid(float x){
        return (float)(1 / (1 + java.lang.Math.exp(-x)));
    }

//    public static float toRad(float deg) {
//        return deg/360.0f * 2.0f * (float)java.lang.Math.PI;
//    }
//
//    public static float toDeg(float rad) {
//        return rad/(2.0f * (float)java.lang.Math.PI) * 360.0f ;
//    }

}
