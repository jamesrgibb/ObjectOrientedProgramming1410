package a10;

import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SubCow extends Plant {

	public SubCow(Double startingPosition, Double initHitbox, BufferedImage img, int health, int coolDown,
			int attackDamage) {
		super(startingPosition, initHitbox, img, health, coolDown, attackDamage);

	}

	@Override
	public void attack(Actor other) {
		if (other instanceof Zombie) {
			super.attack(other);
		}
	}

	@Override
	public void removeAction(ArrayList<Actor> others) {
		this.changeHealth(-(attackDamage * attackDamage));

	}

}
