package model;

/**
 * @author Bas Jansen
 */
public class Fruit extends GameObject {
	private GameObjectType objectType;
	private int score;

	public int getScore() {
		if (super.getSize() == 30) {
			return score;
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
