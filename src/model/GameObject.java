package model;

/**
 * @author Bas Jansen
 */
public abstract class GameObject {
	private int y,x;
	private int size;
	
	public void setSize(int size) {
		this.size = size;
	}
	
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
	
	public int getSize() {
		return size;
	}
	
	public void subtractX() {
		x--;
	}
	
	public void subtractY() {
		y--;
	}
	
	public void addUpX() {
		x++;
	}
	
	public void addUpY() {
		y++;
	}
	
	public abstract void setObjectType(GameObjectType objectType);
	public abstract GameObjectType getObjectType();
}
