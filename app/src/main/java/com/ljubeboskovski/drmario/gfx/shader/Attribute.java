package com.ljubeboskovski.drmario.gfx.shader;

public class Attribute {
    int programID;
    String name;
    int handle;
    int size;
    int type;
    int typeSize;
    boolean normalized;
    int stride;
    int offset;

    public Attribute(int programID,
                     String name,
                     int handle,
                     int size,
                     int type,
                     int typeSize,
                     boolean normalized,
                     int stride,
                     int offset){
        this.programID = programID;
        this.name = name;
        this.handle = handle;
        this.size = size;
        this.type = type;
        this.typeSize = typeSize;
        this.normalized = normalized;
        this.stride = stride;
        this.offset = offset;
    }
}
