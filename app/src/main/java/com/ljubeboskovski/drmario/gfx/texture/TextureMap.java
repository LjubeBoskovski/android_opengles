package com.ljubeboskovski.drmario.gfx.texture;

public class TextureMap {

    int size;

    ModelTexture texture;


    public TextureMap(int size, ModelTexture texture) {
        this.size = size;
        this.texture = texture;
    }

    public ModelTexture getTexture() {
        return texture;
    }

    public float[] getTexCoordinates(int x, int y) {
        float[] texCoordinates = {
                ((float) x) / size, ((float) y + 1) / size,
                ((float) x + 1) / size, ((float) y + 1) / size,
                ((float) x + 1) / size, ((float) y) / size,
                ((float) x) / size, ((float) y) / size
        };
        return texCoordinates;
    }

}
