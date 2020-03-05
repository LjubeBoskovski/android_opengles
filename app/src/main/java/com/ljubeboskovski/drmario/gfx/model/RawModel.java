package com.ljubeboskovski.drmario.gfx.model;

public class RawModel {

	private int vaoID;
	private int iaoID;
	private int indexSize;

	public RawModel(int vaoID, int iaoID, int indexSize){
		this.vaoID = vaoID;
		this.iaoID = iaoID;
		this.indexSize = indexSize;
	}

	public int getVaoID(){
		return vaoID;
	}

	public int getIaoID(){
		return iaoID;
	}

	public int getIndexSize(){
		return indexSize;
	}
}
