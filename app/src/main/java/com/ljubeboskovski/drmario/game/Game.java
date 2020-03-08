package com.ljubeboskovski.drmario.game;

import com.ljubeboskovski.drmario.game.world.World;

import java.util.Random;

public class Game {

	public static Random random;
	public World world;
	
	public Game() {
	    this.random = new Random();
	    this.world = new World(9 ,16);
	}

	public void tick(){

	}
}
