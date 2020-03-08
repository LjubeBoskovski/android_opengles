package com.ljubeboskovski.drmario;

public class Global {
    public static final int BYTES_PER_FLOAT = 4;
    public static final int BYTES_PER_SHORT = 2;

    public static final int FRAMES_PER_SECOND = 60;

    public static final int SIZE_POSITION = 3;
    public static final int SIZE_COLOR = 4;
    public static final int SIZE_TEXTURE_COORDS = 2;
    public static final int STRIDE = (SIZE_POSITION + SIZE_COLOR + SIZE_TEXTURE_COORDS) * BYTES_PER_FLOAT;

    public static enum BlockColor {
        RED,
        YELLOW,
        BLUE,
        GREEN
    }
}
