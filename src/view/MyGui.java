package view;

import javax.swing.JFrame;

public class MyGui extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void setupGui(MyContentPane myContentPane) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setContentPane(myContentPane);
		this.pack();
		this.setVisible(true);
	}
}
