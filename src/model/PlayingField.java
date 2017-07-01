package model;

/**
 * @author Bas Jansen
 */
public class PlayingField {
	private GameObject gameObject;
	private SlashTrailSection slashTrailSection;
	
	public PlayingField() {
		slashTrailSection = new SlashTrailSection();
	}
	
	public void spawn(GameObjectType gameObjectType) {
		/**
		 * Check what kind of object got generated and then instaniate the correct GameObject.
		 */
		if (gameObjectType == GameObjectType.BOMB) {
			gameObject = new Bomb();
			
			gameObject.setObjectType(gameObjectType);
			gameObject.setSize(50);
		} else {
			gameObject = new Fruit();
			
			gameObject.setObjectType(gameObjectType);
			if (gameObject.getObjectType() == GameObjectType.STRAWBERRY) {
				((Fruit)gameObject).setScore(100);
				gameObject.setSize(30);
			} else {
				((Fruit)gameObject).setScore(50);
				gameObject.setSize(50);
			}
		}

	}
	
	public void removeObject() {
		this.gameObject = null;
	}
	
	public GameObject getGameObject() {
		return gameObject;
	}

	public void setGameObject(GameObject gameObject) {
		this.gameObject = gameObject;
	}
	
	public SlashTrailSection getSlashTrail() {
		return slashTrailSection;
	}
}
