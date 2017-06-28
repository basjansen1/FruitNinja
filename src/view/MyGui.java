package view;

import javax.swing.JFrame;

/**
 * @author Bas Jansen
 */
public class MyGui extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public void setupGui(MyContentPane myContentPane) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(myContentPane);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
}
