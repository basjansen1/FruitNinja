package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Bomb;
import model.Fruit;
import model.GameObject;
import model.GameObjectType;

@SuppressWarnings("unused")
public class GameView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GameObject gameObject;
	
	private int x, y;
	
	public GameView() {
		this.setPreferredSize(new Dimension(500, 500));
	}
	
	public void animateGameObject(GameObject gameObject) {
		this.gameObject = gameObject;
		this.repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		try {
			BufferedImage img = ImageIO.read(getClass().getResourceAsStream("/resources/background.png"));
			g.drawImage(img, 0, 0, this);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		if (gameObject.getObjectType() == GameObjectType.BOMB) {
			new BombPainter().paintBomb(g, (Bomb)gameObject);
		} else {
			new FruitPainter().paintFruit(g, (Fruit)gameObject);
		}
	}
}