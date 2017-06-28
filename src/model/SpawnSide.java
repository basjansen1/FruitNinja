package model;

import java.util.Random;

/**
 * @author Bas Jansen
 */
public enum SpawnSide {
	LEFT, RIGHT, BOTTOM, TOP;

	/**
	 * Generates a random SpawnSide.
	 * @return
	 */
	public static SpawnSide getRandomSide() {
		Random random = new Random();
		return values()[random.nextInt(values().length)];
	}
}
