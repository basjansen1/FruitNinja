package model;

/**
 * @author Bas Jansen
 */
public class Bomb extends GameObject {
	private int size;
	private GameObjectType objectType;
	private int score;

	public void setSize(int size) {
		this.size = size;
	}

	public int getScore() {
		if (size == 1) {
			return score + 50;
		} else {
			return score;
		}
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
