package model;

/**
 * @author Bas Jansen
 * Represents a bomb in the game
 */
public class Bomb extends GameObject {
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
