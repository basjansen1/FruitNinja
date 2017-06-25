package model;

import java.util.Random;

public enum SpawnSide {
	LEFT, RIGHT, BOTTOM, TOP;

	public static SpawnSide getRandomSide() {
		Random random = new Random();
		return values()[random.nextInt(values().length)];
	}
}
