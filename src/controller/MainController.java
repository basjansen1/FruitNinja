package controller;

import view.GameOverView;
import view.GameView;
import view.MyContentPane;
import view.MyGui;
import view.PlayerView;

/**
 * @author Bas Jansen
 */
public class MainController {
	private MyGui myGui;
	private SoundController soundController;
	private MyContentPane myContentPane;
	private GameView gameView;
	private PlayerView playerView;

	public MainController() {
		myGui = new MyGui();
	}
	
	public void setGameView() {
		playerView = new PlayerView();
		gameView = new GameView();
		
		new GameController(gameView, playerView, this).startGame();
		soundController = new SoundController();
		soundController.startGameSoundTrack();
		
		MyContentPane myContentPane = new MyContentPane();
		myContentPane.setGameView(gameView, playerView);
		this.myContentPane = myContentPane;
		
		myGui.setupGui(myContentPane);
	}
	
	public void setGameOverView(int score) {
		GameOverView gameOverView = new GameOverView(score);
		
		myContentPane.remove(gameView);
		myContentPane.remove(playerView);
		
		soundController.stopGameSoundTrack();
		
		myContentPane.setGameOverView(gameOverView);
		
		myGui.setupGui(myContentPane);
	}
}
