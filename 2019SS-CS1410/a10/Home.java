package a10;

import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;

public class Home extends Actor {
	private boolean isColliding;

	public Home(Double startingPosition, Double initHitbox, BufferedImage img, int health, int coolDown, double speed,
			int attackDamage) {
		super(startingPosition, initHitbox, img, health, coolDown, speed, attackDamage);

		isColliding = false;
	}

	/**
	 * An attack means the two hotboxes are overlapping and the Actor is ready to
	 * attack again (based on its cooldown).
	 * 
	 * Plants only attack Zombies.
	 * 
	 * @param other
	 */
	@Override
	public void attack(Actor other) {
		if (other instanceof Zombie)
			super.attack(other);
	}

	/**
	 * Set the collision status
	 * 
	 * @param collisionStatus
	 */
	public void setColliding(boolean collisionStatus) {
		isColliding = collisionStatus;

	}

	/**
	 * Get the collision status.
	 * 
	 * @return
	 */
	public boolean getColliding() {
		return isColliding;
	}

	public void setCollisionStatus3(Actor other) {
		if (other instanceof Zombie && this.isCollidingOther(other))
			setColliding(true);
	}

}
