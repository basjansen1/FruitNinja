package model;

/**
 * @author Bas Jansen
 * Represents a fruit object in the game
 * Possible Fruits:
 * - Strawberry
 * - Apple
 * - Orange
 */
public class Fruit extends GameObject {
	private GameObjectType objectType;
	private int score;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public void setObjectType(GameObjectType objectType) {
		this.objectType = objectType;		
	}

	@Override
	public GameObjectType getObjectType() {
		return objectType;
	}
}
