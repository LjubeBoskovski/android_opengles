package com.ljubeboskovski.drmario.gfx.texture;

public class TextureMap {

    int size;

    ModelTexture texture;


    public TextureMap(int size, ModelTexture texture) {
        this.size = size;
        this.texture = texture;
    }

    public TextureMap(int size, int resourceID){
        this.size = size;
        this.texture = texture = new ModelTexture(resourceID);
    }

    public ModelTexture getTexture() {
        return texture;
    }

    public float[] getTexCoordinates(int x, int y) {
        return getTexCoordinates(x, y, x + 1, y + 1);
    }

    public float[] getTexCoordinates(int x1, int y1, int x2, int y2) {
        float[] texCoordinates = {
                ((float) x1) / size, ((float) y2) / size,
                ((float) x2) / size, ((float) y2) / size,
                ((float) x2) / size, ((float) y1) / size,
                ((float) x1) / size, ((float) y1) / size
        };
        return texCoordinates;
    }

}
