package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import model.Bomb;
import model.Fruit;
import model.GameObject;
import model.GameObjectType;
import model.PlayingField;
import model.SlashTrailSection;
import model.SpawnSide;
import view.GameView;

/**
 * @author Bas Jansen
 */
public class PlayingFieldController extends MouseAdapter {
	private PlayingField playingField;
	private MainController mainController;

	private GameObject gameObject;
	
	private GameView gameView;
	private SlashTrailSection slash;
	private Thread thread;
	private Runnable runnable;
	private int x, y;
	private MouseEvent draggedEvent;
	private SoundController soundControl;

	public PlayingFieldController(GameView gameView, MainController mainController) {
		this.gameView = gameView;
		this.mainController = mainController;
		this.soundControl = new SoundController();
		
		playingField = new PlayingField();
		slash = new SlashTrailSection();
		
		runnable = new Animate();
		thread = new Thread(runnable);
		
		gameView.addMouseListener(this);
		gameView.addMouseMotionListener(this);
		
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
			// Game loop
			for (;;) {
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
					
					gameView.animateGameObject(gameObject);
					
					if (intersection()) {
						resetPositions();
						break;
					}
					
					if (objectOutOfScreen()) {
						resetPositions();
						break;
					}
				
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
					}
				}
			}
		}
	}
	
	private boolean objectOutOfScreen() {
		if (x > 500 || x < -80 
				|| y > 500 || y < -80) {
			return true;
		}
		return false;
	}
	
	/*
	 * Method to reset the x and y positions where the gameObject will get printed on the screen.
	 */
	private void resetPositions() {
		x = 0;
		y = 0;
		
		gameObject.setX(0);
		gameObject.setY(0);
	}
	
	private boolean intersection() {
		if (draggedEvent != null) {
			if (slash.getStartX() != slash.getEndX() 
					&& slash.getStartY() != slash.getEndY()) {
				if (slicedThrough(draggedEvent.getX(), draggedEvent.getY())) {		
					//You slashed so play the slashingSound.
					soundControl.startSlashSound();
					
					if (gameObject.getObjectType() == GameObjectType.BOMB) {
						// player live - 1
					}
					
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