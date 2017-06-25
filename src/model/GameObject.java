package model;

public abstract class GameObject {
	private int y,x;
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public abstract void setObjectType(GameObjectType objectType);
	public abstract GameObjectType getObjectType();
}
