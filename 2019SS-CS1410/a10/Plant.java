package a10;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Plant extends Actor {
	private boolean isColliding;

	public Plant(Point2D.Double startingPosition, Point2D.Double initHitbox, BufferedImage img, int health,
			int coolDown, int attackDamage) {
		super(startingPosition, initHitbox, img, health, coolDown, 0, attackDamage);
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

	/**
	 * Set collision status on this if it overlaps with other.
	 * 
	 * @param other
	 */
	public void setCollisionStatus2(Actor other) {
		Point2D.Double shift = new Point2D.Double(75, 0);
		if (other instanceof Plant && this.isCollidingOther(other))
			setColliding(true);
		if (this != other && this.isCollidingOther(other) && other.getSpeed() >= 0) {
			other.shiftPosition(shift);
		}
	}
}
