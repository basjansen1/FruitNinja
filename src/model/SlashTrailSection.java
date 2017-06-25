package model;

public class SlashTrailSection {
	private int startX, startY;
	private int endX, endY;
	
	public void setStartPoint(int startX, int startY) {
		this.startX = startX;
		this.startY = startY;
	}
	
	public void setEndPoint(int endX, int endY) {
		this.endX = endX;
		this.endY = endY;
	}

	public int getStartX() {
		return startX;
	}
	
	public int getStartY() {
		return startY;
	}
	
	public int getEndX() {
		return endX;
	}
	
	public int getEndY() {
		return endY;
	}
}
