package view;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Bas Jansen
 */
public class PlayerView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel lblScorePlaceHolder;
	private JLabel lblLivesPlaceHolder;
	private JLabel lblScoreValue;
	private JLabel lblLivesValue;
	
	public PlayerView() {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		lblScorePlaceHolder = new JLabel("Score: ");
		lblLivesPlaceHolder = new JLabel("Lives: ");
		lblScoreValue = new JLabel("300");
		lblLivesValue = new JLabel("2");
		
		setupPane();
	}
	
	private void setupPane() {
		this.setPreferredSize(new Dimension(500, 40));
		this.add(lblScorePlaceHolder);
		this.add(Box.createHorizontalStrut(90));
		this.add(lblScoreValue);
		this.add(Box.createHorizontalStrut(100));
		this.add(lblLivesPlaceHolder);
		this.add(Box.createHorizontalStrut(90));
		this.add(lblLivesValue);
	}
}
