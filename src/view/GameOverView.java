package view;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * @author Bas Jansen
 */
public class GameOverView extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JLabel lblGameOver;
	private JLabel lblYourScorePlaceholder;
	private JLabel lblScore;
	
	public GameOverView(int score) {
		this.setPreferredSize(new Dimension(500, 500));
		lblGameOver = new JLabel("Game Over!", SwingConstants.CENTER);
		lblGameOver.setVerticalAlignment(SwingConstants.CENTER);
		lblGameOver.setFont(new Font("TimesNewRoman", Font.BOLD, 50));
		
		lblYourScorePlaceholder = new JLabel("Your score is: ", SwingConstants.CENTER);
		lblYourScorePlaceholder.setVerticalAlignment(SwingConstants.CENTER);
		lblYourScorePlaceholder.setFont(new Font("TimesNewRoman", Font.BOLD, 35));
		
		lblScore = new JLabel(score + "", SwingConstants.CENTER);
		lblScore.setVerticalAlignment(SwingConstants.CENTER);
		lblScore.setFont(new Font("TimesNewRoman", Font.BOLD, 35));
		
		this.add(lblGameOver);
		this.add(lblYourScorePlaceholder);
		this.add(lblScore);
	}
}
