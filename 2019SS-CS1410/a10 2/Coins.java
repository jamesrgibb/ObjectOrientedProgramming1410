package a10;

import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;

public class Coins extends Actor implements Attack {

	public Coins(Double startingPosition, Double initHitbox, BufferedImage img, int health, int coolDown, double speed,
			int attackDamage) {
		super(startingPosition, initHitbox, img, health, coolDown, speed, attackDamage);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
