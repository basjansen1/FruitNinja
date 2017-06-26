package model;

/**
 * @author Bas Jansen
 */
public class PlayingField {
	private GameObject gameObject;
	private SlashTrailSection slashTrailSection;
	
	public void removeObject() {
		this.gameObject = null;
	}
	
	public GameObject getGameObject() {
		return gameObject;
	}

	public void setGameObject(GameObject gameObject) {
		this.gameObject = gameObject;
	}
}
