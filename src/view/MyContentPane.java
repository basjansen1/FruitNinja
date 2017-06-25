package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import model.PlayingField;

@SuppressWarnings("unused")
public class MyContentPane extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PlayerView playerView;
	private GameView gameView;
	private GameOverView gameOverView;

	public MyContentPane() {		
		this.setLayout(new BorderLayout());
	}

	
	public void setGameView(GameView gameView, PlayerView playerView) {
		this.playerView = playerView;
		this.add(playerView, BorderLayout.NORTH);
		
		this.gameView = gameView;
		this.add(gameView, BorderLayout.CENTER);
	}

	
	public void setGameOverView(GameOverView gameOverView) {
		
	}
}
