package com.ljubeboskovski.drmario;

public class Global {
    public static final int BYTES_PER_FLOAT = 4;
    public static final int BYTES_PER_SHORT = 2;

    public static final int COORDS_PER_VERTEX = 3;
    public static final int COLOR_LENGTH = 4;
    public static final int STRIDE = (COORDS_PER_VERTEX + COLOR_LENGTH) * BYTES_PER_FLOAT;
}
