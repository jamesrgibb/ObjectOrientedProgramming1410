package a10;

import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SubChicken extends Plant {
	private int health;

	public SubChicken(Double startingPosition, Double initHitbox, BufferedImage img, int health, int coolDown,
			int attackDamage) {
		super(startingPosition, initHitbox, img, health, coolDown, attackDamage);
		this.health = health;
	}

}
