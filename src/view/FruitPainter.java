package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Fruit;

/**
 * @author Bas Jansen
 * This class is responsible for drawing a fruit on screen.
 */
public class FruitPainter extends JPanel {
	private static final long serialVersionUID = 1L;
	private BufferedImage bfImg;
	
	private void loadImage(Fruit fruit) {		
		try {
			switch(fruit.getObjectType()) {
				case STRAWBERRY:
					bfImg = ImageIO.read(getClass().getResourceAsStream("/resources/strawberry.png"));
					break;
				case APPLE:
					bfImg = ImageIO.read(getClass().getResourceAsStream("/resources/apple.png"));
					break;
				case ORANGE:
					bfImg = ImageIO.read(getClass().getResourceAsStream("/resources/orange.png"));
					break;
				default:
					break;
			}
		} catch (IOException e) {
		}
	}
	
	public void paintFruit(Graphics g, Fruit fruit) {
		loadImage(fruit);

		g.drawImage(bfImg, fruit.getX(), fruit.getY(), this);	
	}
}
