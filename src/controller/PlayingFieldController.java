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

@SuppressWarnings("unused")
public class PlayingFieldController extends MouseAdapter {
	private PlayingField playingField;
	private MainController mainController;

	private GameObject gameObject;
	
	private GameView gameView;
	private SlashTrailSection slash;
	private Thread thread;
	private Runnable runnable;
	private int x, y;

	public PlayingFieldController(GameView gameView, MainController mainController) {
		this.gameView = gameView;
		this.mainController = mainController;
		
		playingField = new PlayingField();
		slash = new SlashTrailSection();
		
		runnable = new Animate();
		thread = new Thread(runnable);
		
		gameView.addMouseListener(this);
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

	private class Animate implements Runnable {
		@Override
		public void run() {
			for (;;) {
				GameObjectType gameObjectType = GameObjectType.getRandomFruitType();
				SpawnSide spawnSide = SpawnSide.getRandomSide();
				
				if (gameObjectType == GameObjectType.BOMB) {
					gameObject = new Bomb();
				} else {
					gameObject = new Fruit();
				}
				
				new SlashTrailSectionController(slash, gameObject);
				
				Random r = new Random();
				
				if (spawnSide == SpawnSide.BOTTOM || spawnSide == SpawnSide.TOP) {
					x = r.nextInt(470);
				} else {
					y = r.nextInt(470);
				}
				
				boolean firstTime = true;
				
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
	
					// Repaint the screen so the GameObject gets updated/animated
					gameObject.setX(x);
					gameObject.setY(y);
					gameObject.setObjectType(gameObjectType);
					
					gameView.animateGameObject(gameObject);
					
					// -80 So you know for sure the fruit or bomb is out of the screen.
					if (x > 500 || x < -80 
							|| y > 500 || y < -80) {
						x = 0;
						y = 0;
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
}