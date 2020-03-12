package com.ljubeboskovski.drmario.gfx.texture;

import com.ljubeboskovski.drmario.R;

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
        float[] texCoordinates = {
                ((float) x) / size, ((float) y + 1) / size,
                ((float) x + 1) / size, ((float) y + 1) / size,
                ((float) x + 1) / size, ((float) y) / size,
                ((float) x) / size, ((float) y) / size
        };
        return texCoordinates;
    }

}
