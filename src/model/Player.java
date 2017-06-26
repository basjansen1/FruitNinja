package model;

/**
 * @author Bas Jansen
 */
public class Player {
	private int score;
	private int lives;
	
	public Player() {
		score = 0;
		lives = 3;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void setLives(int lives) {
		this.lives = lives;
	}
	
	public int getLives() {
		return lives;
	}
	
	public int getScore() {
		return score;
	}
}
