package com.ljubeboskovski.drmario;

public class Global {
    public static final int BYTES_PER_FLOAT = 4;
    public static final int BYTES_PER_SHORT = 2;

    public static final int FRAMES_PER_SECOND = 60;

    public static final int SIZE_POSITION = 3;
    public static final int SIZE_COLOR = 4;
    public static final int SIZE_TEXTURE_COORDS = 2;
    public static final int STRIDE = (SIZE_POSITION + SIZE_COLOR + SIZE_TEXTURE_COORDS) * BYTES_PER_FLOAT;

    public final static class BlockColor {
        public final static float[] RED = new float[] {1.0f, 0.0f, 0.0f, 1.0f};
        public final static float[] YELLOW = new float[] {1.0f, 1.0f, 0.0f, 1.0f};
        public final static float[] BLUE = new float[] {0.0f, 0.0f, 1.0f, 1.0f};
        public final static float[] GREEN = new float[] {0.0f, 1.0f, 0.0f, 1.0f};
    }

    public static final class QuadForm {
        public static final float[] coordinates = {
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                0.5f, 0.5f, 0f,
                -0.5f, 0.5f, 0f,
        };


        public static final short[] indices = {
                0, 1, 3,
                3, 1, 2
        };

    }
}
