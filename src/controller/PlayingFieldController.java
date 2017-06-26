package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import model.Bomb;
import model.Fruit;
import model.GameObject;
import model.GameObjectType;
import model.Player;
import model.PlayingField;
import model.SlashTrailSection;
import model.SpawnSide;
import view.GameView;

/**
 * @author Bas Jansen
 */
public class PlayingFieldController extends MouseAdapter {
	private PlayingField playingField;
	private GameObject gameObject;
	private SlashTrailSection slash;
	private Player player;
	private GameView gameView;
	private SoundController soundControl;
	private MainController mainController;
	
	private Thread thread;
	private Runnable runnable;
	private int x, y;
	private MouseEvent draggedEvent;

	public PlayingFieldController(GameView gameView, MainController mainController) {
		this.gameView = gameView;
		this.mainController = mainController;
		this.soundControl = new SoundController();
		
		playingField = new PlayingField();
		slash = new SlashTrailSection();
		player = new Player();
		
		runnable = new Animate();
		thread = new Thread(runnable);
		
		gameView.addMouseListener(this);
		gameView.addMouseMotionListener(this);
	}
	
	public void startGame() {
		thread.start();
	}

	@Override
	public void mousePressed(MouseEvent me) {
		slash.setStartPoint((int) me.getLocationOnScreen().getX(), (int) me.getLocationOnScreen().getY());
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		slash.setEndPoint((int) me.getLocationOnScreen().getX(), (int) me.getLocationOnScreen().getY());
	}
	
	@Override
	public void mouseDragged(MouseEvent me) {
		this.draggedEvent = me;
	}

	private class Animate implements Runnable {
		@Override
		public void run() {
			while(player.getLives() > 0) {
				GameObjectType gameObjectType = GameObjectType.getRandomFruitType();
				SpawnSide spawnSide = SpawnSide.getRandomSide();
				
				if (gameObjectType == GameObjectType.BOMB) {
					gameObject = new Bomb();
				} else {
					gameObject = new Fruit();
				}
				
				Random r = new Random();
				
				if (spawnSide == SpawnSide.BOTTOM || spawnSide == SpawnSide.TOP) {
					// TODO-> Make it 500 and subtract the size of it
					x = r.nextInt(460);
				} else {
					y = r.nextInt(460);
				}
				
				boolean firstTime = true;
				
				// Animation loop
				for(;;) {
					switch(spawnSide) {
						case BOTTOM:
							if (firstTime) {
								y = 500;
								firstTime = false;
							}
							y--;
							break;
						case TOP:	
							y++;
							break;
						case LEFT:
							x++;
							break;
						case RIGHT:
							if (firstTime) {
								x = 500;
								firstTime = false;
							}
							x--;
							break;
					}
	
					gameObject.setX(x);
					gameObject.setY(y);
					gameObject.setObjectType(gameObjectType);
					// TODO->Set the size of the fruit if its a fruit and not a bomb
					
					gameView.animateGameObject(gameObject);
					
					if (intersection()) {
						resetPositions();
						break;
					}
					
					if (objectOutOfScreen()) {
						resetPositions();
						break;
					}
				
					/** 
					 * I'm letting the thread sleep for 5ms because otherwise the animation will go way too fast.
					 * You could see the use of Thread.sleep() as the speed of the game object.
					*/
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
					}
				}
			}
			
			mainController.setGameOverView(player.getScore());
		}
	}
	
	/**
	 * Checks if the gameobejct(fruit or bomb) is out of screen.
	 * @return
	 */
	private boolean objectOutOfScreen() {
		if (x > 500 || x < -40 
				|| y > 500 || y < -40) {
			return true;
		}
		return false;
	}
	
	/**
	 * Method to reset the x and y positions where the gameObject will get printed on the screen.
	 */
	private void resetPositions() {
		x = 0;
		y = 0;
		
		gameObject.setX(0);
		gameObject.setY(0);
	}
	
	/**
	 * @returns true when your cursor went through a fruit or bomb gameObject
	 * @returns false when there wasn't an collision
	 */
	private boolean intersection() {
		if (draggedEvent != null) {
			if (slash.getStartX() != slash.getEndX() 
					&& slash.getStartY() != slash.getEndY()) {
				if (slicedThrough(draggedEvent.getX(), draggedEvent.getY())) {		
					//You slashed so play the slashingSound.
					soundControl.startSlashSound();
					
					if (gameObject.getObjectType() == GameObjectType.BOMB) {
						player.subtractLife();
					}
					
					// Reset the slash variables
					slash.setEndPoint(0, 0);
					slash.setStartPoint(0, 0);
					
					return true;
				}
			}
		}
		return false;
	}
	
	/*
	 * @param: x: x position of the dragged mouse
	 * @param: y: y position of the dragged mouse
	 */
	private boolean slicedThrough(int x, int y) {
		if (
				(x >= gameObject.getX())
				&& (x <= (gameObject.getX() + 40)) 
				&& (y >= gameObject.getY())
				&& (y <= (gameObject.getY() + 40))) {
			return true;
		} else {
			return false;
		}
	}
}