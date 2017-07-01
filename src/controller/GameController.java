package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import model.Fruit;
import model.GameObjectType;
import model.Player;
import model.PlayingField;
import model.SpawnSide;
import view.GameView;
import view.PlayerView;

/**
 * @author Bas Jansen
 */
public class GameController extends MouseAdapter {
	/**
	 * Model instance variables
	 */
	private PlayingField playingField;
	private Player player;
	
	/**
	 * View instance variables
	 */
	private GameView gameView;
	private PlayerView playerView;
	
	/**
	 * Controller instance variables
	 */
	private SoundController soundControl;
	private MainController mainController;
	
	private Thread thread;
	private Runnable runnable;
	private MouseEvent draggedEvent;

	public GameController(GameView gameView, PlayerView playerView, MainController mainController) {
		this.gameView = gameView;
		this.playerView = playerView;
		this.mainController = mainController;
		this.soundControl = new SoundController();
		
		playingField = new PlayingField();
		player = new Player();
		
		runnable = new Animate();
		thread = new Thread(runnable);
		
		gameView.addMouseListener(this);
		gameView.addMouseMotionListener(this);
	}
	
	/**
	 * Method which starts the whole game.
	 */
	public void startGame() {
		thread.start();
	}

	@Override
	public void mousePressed(MouseEvent me) {
		playingField.getSlashTrail().setStartPoint((int) me.getLocationOnScreen().getX(), (int) me.getLocationOnScreen().getY());
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		playingField.getSlashTrail().setEndPoint((int) me.getLocationOnScreen().getX(), (int) me.getLocationOnScreen().getY());
	}
	
	@Override
	public void mouseDragged(MouseEvent me) {
		this.draggedEvent = me;
	}

	private class Animate implements Runnable {
		@Override
		public void run() {
			/**
			 * Game loop
			 * The game keeps running until you have zero lives left.
			 */
			while(player.getLives() > 0) {
				GameObjectType gameObjectType = GameObjectType.getRandomFruitType();
				SpawnSide spawnSide = SpawnSide.getRandomSide();
				
				/**
				 * Let the playingField generate a gameObject from the given objectType
				 */
				playingField.spawn(gameObjectType);
				
				boolean firstTime = true;
				
				/**
				 * int range
				 *  Calculates the range of spawning so the object doesnt spawn out of the screen.
				 *  So for the strawberry the range is 470 and the bomb and other fruits will be 450
				 */
				int range = 500 - playingField.getGameObject().getSize();
				
				Random r = new Random();
				
				if (spawnSide == SpawnSide.BOTTOM || spawnSide == SpawnSide.TOP) {
					playingField.getGameObject().setX(r.nextInt(range));
				} else {
					playingField.getGameObject().setY(r.nextInt(range));
				}
				
				/**
				 * Animation loop
				 * This loop is for animating the GameObject
				 * It keeps animating the GameObject until its either out of the screen or sliced.
				 */
				for(;;) {
					/**
					 * Switch to set the correct positions where the objects will spawn.
					 */
					switch(spawnSide) {
						case BOTTOM:
							if (firstTime) {
								playingField.getGameObject().setY(500);
								firstTime = false;
							}
							playingField.getGameObject().subtractY();
							break;
						case TOP:	
							playingField.getGameObject().addUpY();
							break;
						case LEFT:
							playingField.getGameObject().addUpX();
							break;
						case RIGHT:
							if (firstTime) {
								playingField.getGameObject().setX(500);
								firstTime = false;
							}
							playingField.getGameObject().subtractX();
							break;
					}
					
					gameView.animateGameObject(playingField.getGameObject());
					
					if (intersection()) {
						resetPositions();
						playingField.removeObject();
						
						break;
					}
					
					if (objectOutOfScreen()) {
						resetPositions();
						playingField.removeObject();
						
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
	 * Method to reset the x and y positions where the gameObject will get printed on the screen.
	 */
	private void resetPositions() {
		playingField.getGameObject().setX(0);
		playingField.getGameObject().setY(0);
	}
	
	/**
	 * Checks if the GameObject(fruit or bomb) is out of screen.
	 * @return
	 */
	private boolean objectOutOfScreen() {
		if (playingField.getGameObject().getX() > 500 || playingField.getGameObject().getX() < -40 
				|| playingField.getGameObject().getY() > 500 || playingField.getGameObject().getY() < -40) {
			return true;
		}
		return false;
	}
	
	/**
	 * @returns true when your cursor went through a fruit or bomb gameObject
	 * @returns false when there wasn't an collision
	 */
	private boolean intersection() {
		if (draggedEvent != null) {
			if (playingField.getSlashTrail().getStartX() != playingField.getSlashTrail().getEndX() 
					&& playingField.getSlashTrail().getStartY() != playingField.getSlashTrail().getEndY()) {
				if (slicedThrough(draggedEvent.getX(), draggedEvent.getY())) {		
					//You slashed so play the slashingSound.
					soundControl.startSlashSound();
					
					if (playingField.getGameObject().getObjectType() == GameObjectType.BOMB) {
						player.subtractLife();
						playerView.updateLives(player.getLives());
					} else {
						// Update the total score for the player
						player.setScore(((Fruit)playingField.getGameObject()).getScore());
						
						// Its Fruit so update the score
						playerView.updateScore(((Fruit) playingField.getGameObject()).getScore());
					}
					
					// Reset the slash variables
					playingField.getSlashTrail().setEndPoint(0, 0);
					playingField.getSlashTrail().setStartPoint(0, 0);
					
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * @param: x: x position of the dragged mouse
	 * @param: y: y position of the dragged mouse
	 */
	private boolean slicedThrough(int x, int y) {
		if (
				(x >= playingField.getGameObject().getX())
				&& (x <= (playingField.getGameObject().getX() + 40)) 
				&& (y >= playingField.getGameObject().getY())
				&& (y <= (playingField.getGameObject().getY() + 40))) {
			return true;
		} else {
			return false;
		}
	}
}