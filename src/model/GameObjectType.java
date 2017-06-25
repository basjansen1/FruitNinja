package model;

import java.util.Random;

public enum GameObjectType {
	STRAWBERRY, ORANGE, APPLE, BOMB;
	
	public static GameObjectType getRandomFruitType() {
		return values()[new Random().nextInt(values().length)];
	}
}