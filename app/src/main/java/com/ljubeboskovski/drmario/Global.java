package com.ljubeboskovski.drmario;

import android.util.Log;

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
    public static final float RENDER_ELASTICITY_SCALE = 0.07f;
    public static final float RENDER_ELASTICITY_ROTATE = 0.05f;

    public static final int WORLD_SIZE_X = 9;
    public static final int WORLD_SIZE_Y = 16;

    public static final int BUTTONS_SIZE_X = 8;
    public static final int BUTTONS_SIZE_Y = 3;

    public static int DISPLAY_WIDTH = -1;
    public static int DISPLAY_HEIGHT = -1;
    public static float DISPLAY_DENSITY = -1f;


    public static enum BLOCK_COLOR {
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
        public final static float[] RED = new float[]{1.0f, 0.0f, 0.0f, 1.0f};
        public final static float[] YELLOW = new float[]{1.0f, 1.0f, 0.0f, 1.0f};
        public final static float[] BLUE = new float[]{0.0f, 0.0f, 1.0f, 1.0f};
        public final static float[] GREEN = new float[]{0.0f, 1.0f, 0.0f, 1.0f};
        public final static float[] NO_COLOR = new float[]{1.0f, 0.0f, 1.0f, 1.0f};


        public final static BLOCK_COLOR getColorFromInt(int i) {
            switch (i) {
                case 0:
                    return BLOCK_COLOR.RED;
                case 1:
                    return BLOCK_COLOR.YELLOW;
                case 2:
                    return BLOCK_COLOR.BLUE;
                default:
                    return BLOCK_COLOR.GREEN;
            }
        }

        public final static BLOCK_COLOR getRandomColor() {
            int i = Game.random.nextInt(3);
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

        public static TexturedModel BUTTON_MOVE_LEFT;
        public static TexturedModel BUTTON_MOVE_DOWN;
        public static TexturedModel BUTTON_MOVE_RIGHT;
        public static TexturedModel BUTTON_ROTATE_COUNTERCLOCKWISE;
        public static TexturedModel BUTTON_ROTATE_CLOCKWISE;


        public static void initWorldTextures(Loader loader, TextureMap textureMap) {
            WALL_STRAIGHT = loader.loadToVAO(FormQuad.coordinates, BlockColor.NO_COLOR,
                    textureMap.getTexCoordinates(0, 7), FormQuad.indices, textureMap.getTexture());
            WALL_EDGE_OUTER = loader.loadToVAO(FormQuad.coordinates, BlockColor.NO_COLOR,
                    textureMap.getTexCoordinates(1, 7), FormQuad.indices, textureMap.getTexture());
            WALL_EDGE_INNER = loader.loadToVAO(FormQuad.coordinates, BlockColor.NO_COLOR,
                    textureMap.getTexCoordinates(2, 7), FormQuad.indices, textureMap.getTexture());
            WALL_END_LEFT = loader.loadToVAO(FormQuad.coordinates, BlockColor.NO_COLOR,
                    textureMap.getTexCoordinates(3, 7), FormQuad.indices, textureMap.getTexture());
            WALL_END_RIGHT = loader.loadToVAO(FormQuad.coordinates, BlockColor.NO_COLOR,
                    textureMap.getTexCoordinates(4, 7), FormQuad.indices, textureMap.getTexture());

            RED_BLOCK_SINGLE = loader.loadToVAO(FormQuad.coordinates, BlockColor.RED,
                    textureMap.getTexCoordinates(0, 0), FormQuad.indices, textureMap.getTexture());
            RED_BLOCK_DOUBLE = loader.loadToVAO(FormQuad.coordinates, BlockColor.RED,
                    textureMap.getTexCoordinates(1, 0), FormQuad.indices, textureMap.getTexture());
            RED_VIRUS_0 = loader.loadToVAO(FormQuad.coordinates, BlockColor.RED,
                    textureMap.getTexCoordinates(2, 0), FormQuad.indices, textureMap.getTexture());
            RED_VIRUS_1 = loader.loadToVAO(FormQuad.coordinates, BlockColor.RED,
                    textureMap.getTexCoordinates(3, 0), FormQuad.indices, textureMap.getTexture());

            YELLOW_BLOCK_SINGLE = loader.loadToVAO(FormQuad.coordinates, BlockColor.YELLOW,
                    textureMap.getTexCoordinates(0, 1), FormQuad.indices, textureMap.getTexture());
            YELLOW_BLOCK_DOUBLE = loader.loadToVAO(FormQuad.coordinates, BlockColor.YELLOW,
                    textureMap.getTexCoordinates(1, 1), FormQuad.indices, textureMap.getTexture());
            YELLOW_VIRUS_0 = loader.loadToVAO(FormQuad.coordinates, BlockColor.YELLOW,
                    textureMap.getTexCoordinates(2, 1), FormQuad.indices, textureMap.getTexture());
            YELLOW_VIRUS_1 = loader.loadToVAO(FormQuad.coordinates, BlockColor.YELLOW,
                    textureMap.getTexCoordinates(3, 1), FormQuad.indices, textureMap.getTexture());

            BLUE_BLOCK_SINGLE = loader.loadToVAO(FormQuad.coordinates, BlockColor.BLUE,
                    textureMap.getTexCoordinates(0, 2), FormQuad.indices, textureMap.getTexture());
            BLUE_BLOCK_DOUBLE = loader.loadToVAO(FormQuad.coordinates, BlockColor.BLUE,
                    textureMap.getTexCoordinates(1, 2), FormQuad.indices, textureMap.getTexture());
            BLUE_VIRUS_0 = loader.loadToVAO(FormQuad.coordinates, BlockColor.BLUE,
                    textureMap.getTexCoordinates(2, 2), FormQuad.indices, textureMap.getTexture());
            BLUE_VIRUS_1 = loader.loadToVAO(FormQuad.coordinates, BlockColor.BLUE,
                    textureMap.getTexCoordinates(3, 2), FormQuad.indices, textureMap.getTexture());

            GREEN_BLOCK_SINGLE = loader.loadToVAO(FormQuad.coordinates, BlockColor.GREEN,
                    textureMap.getTexCoordinates(0, 3), FormQuad.indices, textureMap.getTexture());
            GREEN_BLOCK_DOUBLE = loader.loadToVAO(FormQuad.coordinates, BlockColor.GREEN,
                    textureMap.getTexCoordinates(1, 3), FormQuad.indices, textureMap.getTexture());
            GREEN_VIRUS_0 = loader.loadToVAO(FormQuad.coordinates, BlockColor.GREEN,
                    textureMap.getTexCoordinates(2, 3), FormQuad.indices, textureMap.getTexture());
            GREEN_VIRUS_1 = loader.loadToVAO(FormQuad.coordinates, BlockColor.GREEN,
                    textureMap.getTexCoordinates(3, 3), FormQuad.indices, textureMap.getTexture());

            Log.println(Log.INFO, "button load to vao", "loading");

            BUTTON_MOVE_LEFT = loader.loadToVAO(FormRectangle2x1.coordinates, BlockColor.NO_COLOR,
                    textureMap.getTexCoordinates(0, 6, 2, 7), FormRectangle2x1.indices,
                    textureMap.getTexture());
            BUTTON_MOVE_DOWN = loader.loadToVAO(FormRectangle2x1.coordinates, BlockColor.NO_COLOR,
                    textureMap.getTexCoordinates(2, 6, 4, 7), FormRectangle2x1.indices,
                    textureMap.getTexture());
            BUTTON_MOVE_RIGHT = loader.loadToVAO(FormRectangle2x1.coordinates, BlockColor.NO_COLOR,
                    textureMap.getTexCoordinates(4, 6, 6, 7), FormRectangle2x1.indices,
                    textureMap.getTexture());
            BUTTON_ROTATE_COUNTERCLOCKWISE = loader.loadToVAO(FormRectangle3x1.coordinates, BlockColor.NO_COLOR,
                    textureMap.getTexCoordinates(0, 5, 3, 6), FormRectangle3x1.indices,
                    textureMap.getTexture());
            BUTTON_ROTATE_CLOCKWISE = loader.loadToVAO(FormRectangle3x1.coordinates, BlockColor.NO_COLOR,
                    textureMap.getTexCoordinates(3, 5, 6, 6), FormRectangle3x1.indices,
                    textureMap.getTexture());

            Log.println(Log.INFO, "button load to vao", "loaded");
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

    public static final class FormQuad {
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

    public static final class FormRectangle2x1 {
        public static final float[] coordinates = {
                -1f, -0.5f, 0f,
                1f, -0.5f, 0f,
                1f, 0.5f, 0f,
                -1f, 0.5f, 0f,
        };

        public static final short[] indices = {
                0, 1, 3,
                3, 1, 2
        };
    }

    public static final class FormRectangle3x1 {
        public static final float[] coordinates = {
                -1.5f, -0.5f, 0f,
                1.5f, -0.5f, 0f,
                1.5f, 0.5f, 0f,
                -1.5f, 0.5f, 0f,
        };

        public static final short[] indices = {
                0, 1, 3,
                3, 1, 2
        };
    }
}
