package com.ljubeboskovski.drmario.gfx.model;

import com.ljubeboskovski.drmario.gfx.texture.ModelTexture;

public class TexturedModel {

	private RawModel rawModel;
	private ModelTexture texture;

	public TexturedModel(RawModel model, ModelTexture texture){
		this.rawModel = model;
		this.texture = texture;
	}

	public RawModel getRawModel(){
		return rawModel;
	}

	public ModelTexture getTexture(){
		return texture;
	}
}
