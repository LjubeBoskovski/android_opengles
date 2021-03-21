package com.ljubeboskovski.drmario;

import com.ljubeboskovski.drmario.game.Game;
import com.ljubeboskovski.drmario.gfx.Loader;
import com.ljubeboskovski.drmario.gfx.model.TexturedModel;
import com.ljubeboskovski.drmario.gfx.texture.TextureMap;

public class Global {
    public static final int BYTES_PER_FLOAT = 4;
    public static final int BYTES_PER_SHORT = 2;

    public static final int FRAMES_PER_SECOND = 60;

    public static final int SIZE_POSITION = 3;
    public static final int SIZE_COLOR = 4;
    public static final int SIZE_TEXTURE_COORDS = 2;
//    public static final int STRIDE = (SIZE_POSITION + SIZE_COLOR + SIZE_TEXTURE_COORDS) * BYTES_PER_FLOAT;

    public static final float RENDER_ELASTICITY_TRANSLATE = 0.25f;
    public static final float RENDER_ELASTICITY_SCALE = 0.1f;
    public static final float RENDER_ELASTICITY_ROTATE = 0.05f;

    public static final int WORLD_SIZE_X = 9;
    public static final int WORLD_SIZE_Y = 16;

    public static enum BLOCK_COLOR {
        TRANSPARENT,
        RED,
        YELLOW,
        BLUE,
        GREEN
    }

    public static enum ENTITY_TYPE {
        SINGLE,
        DOUBLE,
        VIRUS_0,
        VIRUS_1
    }

    public final static class BlockColor {
        public final static float[] TRANSPARENT = new float[]{0.0f, 0.0f, 0.0f, 1.0f};

        public final static float[] RED = new float[]{1.0f, 0.0f, 0.0f, 1.0f};
        public final static float[] YELLOW = new float[]{1.0f, 1.0f, 0.0f, 1.0f};
        public final static float[] BLUE = new float[]{0.0f, 0.0f, 1.0f, 1.0f};
        public final static float[] GREEN = new float[]{0.0f, 1.0f, 0.0f, 1.0f};


        public final static BLOCK_COLOR getColorFromInt(int i) {
            switch (i) {
                case 0:
                    return BLOCK_COLOR.TRANSPARENT;
                case 1:
                    return BLOCK_COLOR.RED;
                case 2:
                    return BLOCK_COLOR.YELLOW;
                case 3:
                    return BLOCK_COLOR.BLUE;
                default:
                    return BLOCK_COLOR.GREEN;
            }
        }

        public final static BLOCK_COLOR getRandomColor() {
            int i = Game.random.nextInt(3) + 1;
            return getColorFromInt(i);
        }
    }

    public static final class Model {

        public static TexturedModel WALL_STRAIGHT;
        public static TexturedModel WALL_EDGE_INNER;
        public static TexturedModel WALL_EDGE_OUTER;
        public static TexturedModel WALL_END_LEFT;
        public static TexturedModel WALL_END_RIGHT;

        public static TexturedModel RED_BLOCK_SINGLE;
        public static TexturedModel RED_BLOCK_DOUBLE;
        public static TexturedModel RED_VIRUS_0;
        public static TexturedModel RED_VIRUS_1;

        public static TexturedModel YELLOW_BLOCK_SINGLE;
        public static TexturedModel YELLOW_BLOCK_DOUBLE;
        public static TexturedModel YELLOW_VIRUS_0;
        public static TexturedModel YELLOW_VIRUS_1;

        public static TexturedModel BLUE_BLOCK_SINGLE;
        public static TexturedModel BLUE_BLOCK_DOUBLE;
        public static TexturedModel BLUE_VIRUS_0;
        public static TexturedModel BLUE_VIRUS_1;

        public static TexturedModel GREEN_BLOCK_SINGLE;
        public static TexturedModel GREEN_BLOCK_DOUBLE;
        public static TexturedModel GREEN_VIRUS_0;
        public static TexturedModel GREEN_VIRUS_1;


        public static void initWorldTextures(Loader loader, TextureMap textureMap) {
            WALL_STRAIGHT = loader.loadToVAO(QuadForm.coordinates, BlockColor.TRANSPARENT,
                    textureMap.getTexCoordinates(0, 7), QuadForm.indices, textureMap.getTexture());
            WALL_EDGE_OUTER = loader.loadToVAO(QuadForm.coordinates, BlockColor.TRANSPARENT,
                    textureMap.getTexCoordinates(1, 7), QuadForm.indices, textureMap.getTexture());
            WALL_EDGE_INNER = loader.loadToVAO(QuadForm.coordinates, BlockColor.TRANSPARENT,
                    textureMap.getTexCoordinates(2, 7), QuadForm.indices, textureMap.getTexture());
            WALL_END_LEFT = loader.loadToVAO(QuadForm.coordinates, BlockColor.TRANSPARENT,
                    textureMap.getTexCoordinates(3, 7), QuadForm.indices, textureMap.getTexture());
            WALL_END_RIGHT = loader.loadToVAO(QuadForm.coordinates, BlockColor.TRANSPARENT,
                    textureMap.getTexCoordinates(4, 7), QuadForm.indices, textureMap.getTexture());

            RED_BLOCK_SINGLE = loader.loadToVAO(QuadForm.coordinates, BlockColor.RED,
                    textureMap.getTexCoordinates(0, 0), QuadForm.indices, textureMap.getTexture());
            RED_BLOCK_DOUBLE = loader.loadToVAO(QuadForm.coordinates, BlockColor.RED,
                    textureMap.getTexCoordinates(1, 0), QuadForm.indices, textureMap.getTexture());
            RED_VIRUS_0 = loader.loadToVAO(QuadForm.coordinates, BlockColor.RED,
                    textureMap.getTexCoordinates(2, 0), QuadForm.indices, textureMap.getTexture());
            RED_VIRUS_1 = loader.loadToVAO(QuadForm.coordinates, BlockColor.RED,
                    textureMap.getTexCoordinates(3, 0), QuadForm.indices, textureMap.getTexture());

            YELLOW_BLOCK_SINGLE = loader.loadToVAO(QuadForm.coordinates, BlockColor.YELLOW,
                    textureMap.getTexCoordinates(0, 1), QuadForm.indices, textureMap.getTexture());
            YELLOW_BLOCK_DOUBLE = loader.loadToVAO(QuadForm.coordinates, BlockColor.YELLOW,
                    textureMap.getTexCoordinates(1, 1), QuadForm.indices, textureMap.getTexture());
            YELLOW_VIRUS_0 = loader.loadToVAO(QuadForm.coordinates, BlockColor.YELLOW,
                    textureMap.getTexCoordinates(2, 1), QuadForm.indices, textureMap.getTexture());
            YELLOW_VIRUS_1 = loader.loadToVAO(QuadForm.coordinates, BlockColor.YELLOW,
                    textureMap.getTexCoordinates(3, 1), QuadForm.indices, textureMap.getTexture());

            BLUE_BLOCK_SINGLE = loader.loadToVAO(QuadForm.coordinates, BlockColor.BLUE,
                    textureMap.getTexCoordinates(0, 2), QuadForm.indices, textureMap.getTexture());
            BLUE_BLOCK_DOUBLE = loader.loadToVAO(QuadForm.coordinates, BlockColor.BLUE,
                    textureMap.getTexCoordinates(1, 2), QuadForm.indices, textureMap.getTexture());
            BLUE_VIRUS_0 = loader.loadToVAO(QuadForm.coordinates, BlockColor.BLUE,
                    textureMap.getTexCoordinates(2, 2), QuadForm.indices, textureMap.getTexture());
            BLUE_VIRUS_1 = loader.loadToVAO(QuadForm.coordinates, BlockColor.BLUE,
                    textureMap.getTexCoordinates(3, 2), QuadForm.indices, textureMap.getTexture());

            GREEN_BLOCK_SINGLE = loader.loadToVAO(QuadForm.coordinates, BlockColor.GREEN,
                    textureMap.getTexCoordinates(0, 3), QuadForm.indices, textureMap.getTexture());
            GREEN_BLOCK_DOUBLE = loader.loadToVAO(QuadForm.coordinates, BlockColor.GREEN,
                    textureMap.getTexCoordinates(1, 3), QuadForm.indices, textureMap.getTexture());
            GREEN_VIRUS_0 = loader.loadToVAO(QuadForm.coordinates, BlockColor.GREEN,
                    textureMap.getTexCoordinates(2, 3), QuadForm.indices, textureMap.getTexture());
            GREEN_VIRUS_1 = loader.loadToVAO(QuadForm.coordinates, BlockColor.GREEN,
                    textureMap.getTexCoordinates(3, 3), QuadForm.indices, textureMap.getTexture());
        }

        public static TexturedModel getModel(BLOCK_COLOR color, ENTITY_TYPE entityType) {
            switch (color) {
                case RED:
                    switch (entityType){
                        case SINGLE:
                            return RED_BLOCK_SINGLE;
                        case DOUBLE:
                            return RED_BLOCK_DOUBLE;
                        case VIRUS_0:
                            return RED_VIRUS_0;
                        case VIRUS_1:
                            return RED_VIRUS_1;
                    }
                case YELLOW:
                    switch (entityType){
                        case SINGLE:
                            return YELLOW_BLOCK_SINGLE;
                        case DOUBLE:
                            return YELLOW_BLOCK_DOUBLE;
                        case VIRUS_0:
                            return YELLOW_VIRUS_0;
                        case VIRUS_1:
                            return YELLOW_VIRUS_1;
                    }
                case BLUE:
                    switch (entityType){
                        case SINGLE:
                            return BLUE_BLOCK_SINGLE;
                        case DOUBLE:
                            return BLUE_BLOCK_DOUBLE;
                        case VIRUS_0:
                            return BLUE_VIRUS_0;
                        case VIRUS_1:
                            return BLUE_VIRUS_1;
                    }
                case GREEN:
                    switch (entityType){
                        case SINGLE:
                            return GREEN_BLOCK_SINGLE;
                        case DOUBLE:
                            return GREEN_BLOCK_DOUBLE;
                        case VIRUS_0:
                            return GREEN_VIRUS_0;
                        case VIRUS_1:
                            return GREEN_VIRUS_1;
                    }
            }
            return null;
        }
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
