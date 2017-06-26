package controller;

import java.awt.event.MouseEvent;

import model.GameObject;
import model.SlashTrailSection;

public class SlashTrailSectionController {
	private SlashTrailSection slash;
	private GameObject gameObject;
	private PlayingFieldController playingFieldController;
	private Thread thread;
	private Runnable runnable;
	
	public SlashTrailSectionController(SlashTrailSection slash, GameObject gameObject, PlayingFieldController playingFieldController) {
		this.slash = slash;
		this.gameObject = gameObject;
		
		this.playingFieldController = playingFieldController;
		
		runnable = new CheckSlash();
		thread = new Thread(runnable);
		
		thread.start();
		
		//addMouseMotionListener();
	}
		
	private class CheckSlash implements Runnable {
		@Override
		public void run() {
			while(true) {
				MouseEvent me = playingFieldController.getMouseDraggedEvent();
				
				if (me != null) {
					System.out.println("Mouse x: " + me.getX() + " Mouse Y: " + me.getY());
					if (slicedThrough(me.getX(), me.getY())) {
//						JOptionPane.showMessageDialog(null, "SLICED THROUGH");
						System.out.println("SLICED THROUGH");
						
						slash.setEndPoint(0, 0);
						slash.setStartPoint(0, 0);
						
//						playingFieldController.repaintGameView();
						
						try {
							Thread.sleep(2500);
						} catch (InterruptedException e) {
							System.out.println();
						}
					}
				} else {
					continue;
				}
			}
		}
	}
	
	/*
	 * @param: x: x position of the dragged mouse
	 * @param: y: y position of the dragged mouse
	 */
	public boolean slicedThrough(int x, int y) {
		if ((x >= gameObject.getX()) && (x <= (gameObject.getX() + 40)) && (y >= gameObject.getY())
				&& (y <= (gameObject.getY() + 40))) {
			return true;
		} else {
			return false;
		}
	}
}
