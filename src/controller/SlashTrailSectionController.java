package controller;

import javax.swing.JOptionPane;

import model.GameObject;
import model.SlashTrailSection;

@SuppressWarnings("unused")
public class SlashTrailSectionController {
	private SlashTrailSection slash;
	private GameObject gameObject;
	private Thread thread;
	private Runnable runnable;
	
	public SlashTrailSectionController(SlashTrailSection slash, GameObject gameObject) {
		this.slash = slash;
		this.gameObject = gameObject;
		
		runnable = new CheckSlash();
		thread = new Thread(runnable);
		
		thread.start();
	}
	
	private class CheckSlash implements Runnable {
		@Override
		public void run() {
			while(true) {
				// Check if slash is done correctly.		
				
//				if (slash.getEndX() >= gameObject.getX() && slash.getEndX() <= gameObject.getX() + 40
//					&& (slash.getEndY() >= gameObject.getY() && slash.getEndY() <= gameObject.getY() + 40)) {
//					JOptionPane.showMessageDialog(null, "SLICEDDD THROUGH");
//				}
				
				System.out.println(slash.getEndX());
				System.out.println(slash.getEndY());
				System.out.println(gameObject.getX());
				System.out.println(gameObject.getY());
				
				if (slash.getEndX() >= gameObject.getX() && slash.getEndX() <= gameObject.getX()
						&& (slash.getEndY() >= gameObject.getY() && slash.getEndY() <= gameObject.getY())) {
					JOptionPane.showMessageDialog(null, "SLICEDDD THROUGH");
				}
				
				if (slash.getEndX() != 0)
					JOptionPane.showMessageDialog(null, "X: " + slash.getEndX() + slash.getEndY() +  "GAME OBJECT:  " + gameObject.getX() );
				
				slash.getEndX();
				slash.getEndY();
				slash.getStartY();
				slash.getStartX();
				
				gameObject.getX();
				gameObject.getY();
			}
		}
	}
}
