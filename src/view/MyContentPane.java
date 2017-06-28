package view;

import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 * @author Bas Jansen
 */
public class MyContentPane extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private PlayerView playerView;
	private GameView gameView;

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
		this.remove(playerView);
		this.remove(gameView);
		
		this.add(gameOverView);
	}
}
