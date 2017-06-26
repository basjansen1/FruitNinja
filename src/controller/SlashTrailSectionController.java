package controller;

import java.awt.Rectangle;
import java.awt.geom.Area;

import javax.swing.JOptionPane;

import model.GameObject;
import model.SlashTrailSection;

public class SlashTrailSectionController {
	private SlashTrailSection slash;
	private GameObject gameObject;
	private PlayingFieldController playingFieldController;
	private Thread thread;
	private Runnable runnable;
	private boolean collision;
	
	public SlashTrailSectionController(SlashTrailSection slash, GameObject gameObject, PlayingFieldController playingFieldController) {
		this.slash = slash;
		this.gameObject = gameObject;
		
		this.playingFieldController = playingFieldController;
		
		runnable = new CheckSlash();
		thread = new Thread(runnable);
		
		thread.start();
	}
	
	private class CheckSlash implements Runnable {
		@Override
		public void run() {
			while(true) {
				// Check if slash is done correctly.					
				System.out.println("end x: " + slash.getEndX());
				System.out.println("end y: " + slash.getEndY());
				System.out.println("game x: " + gameObject.getX());
				System.out.println("game y " + gameObject.getY());
				
				if (slicedThrough()) {
					JOptionPane.showMessageDialog(null, "SLICED THROUGH");
					
					slash.setEndPoint(0, 0);
					slash.setStartPoint(0, 0);
					
					playingFieldController.repaintGameView();
				}
			}
		}
		
		// Method stil doesnt work.
		public boolean slicedThrough() {
//		    return x < r.x + r.width && x + width > r.x && y < r.y + r.height && y + height > r.y;
//			return slash.getEndX() < gameObject.getX() + width && slash.getEndX() + width > gameObject.getX() && slash.getEndY() < slash.getEndY() + gameObject.getY() + width && slash.getEndY() + width > gameObject.getY();

			int width = 40;
			int mouseX = slash.getEndX() - slash.getStartX();
			int mouseY = slash.getEndY() - slash.getStartY();
			
			if (mouseX > 0) {
				return mouseX < gameObject.getX() + width && mouseX + width > gameObject.getX() && mouseY < gameObject.getY() + width && mouseY + width > gameObject.getY();
			}
			return false;
		}
	}
}
