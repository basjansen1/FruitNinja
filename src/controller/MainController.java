package controller;

import view.GameOverView;
import view.GameView;
import view.MyContentPane;
import view.MyGui;
import view.PlayerView;

public class MainController {
	private MyGui myGui;

	public MainController() {
		myGui = new MyGui();
	}
	
	public void setGameView() {
		PlayerView playerView = new PlayerView();
		
		GameView gameView = new GameView();
		new PlayingFieldController(gameView, this);
		
		new SoundController().startGameSoundTrack();
		
		MyContentPane myContentPane = new MyContentPane();
		myContentPane.setGameView(gameView, playerView);
		
		myGui.setupGui(myContentPane);
	}
	
	public void setGameOverView() {
		GameOverView gameOverView = new GameOverView();
		MyContentPane myContentPane = new MyContentPane();
		
		myContentPane.setGameOverView(gameOverView);
	}
}
