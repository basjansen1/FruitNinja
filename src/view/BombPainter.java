package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Bomb;

/**
 * @author Bas Jansen
 * This class is responsible for drawing the bomb on screen.
 */
public class BombPainter extends JPanel {
	private static final long serialVersionUID = 1L;
	
	BufferedImage bfImg;
	
	public BombPainter() {
		try {
			bfImg = ImageIO.read(getClass().getResourceAsStream("/resources/bomb.png"));
		} catch (IOException e) {
		}
	}

	public void paintBomb(Graphics g, Bomb b) {
		if (bfImg != null) {
			g.drawImage(bfImg, b.getX(), b.getY(), this);
		}
	}
}
