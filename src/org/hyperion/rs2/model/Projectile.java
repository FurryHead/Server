package org.hyperion.rs2.model;

/**
 * Represents a moving graphic.
 * @author Michael Bull
 * @author Thomas Nappo
 */
public class Projectile {
		
	/**
	 * Creates a projectile.
	 * @param id The id.
	 * @param delay The delay.
	 * @return The new graphic object.
	 */
	public static Projectile create(Location start, Location finish, int id, int delay, int angle, int speed, int startHeight, int endHeight, Entity lockon, int slope, int radius) {
		return new Projectile(start, finish, id, delay, angle, speed, startHeight, endHeight, lockon, slope, radius);
	}
	
	/**
	 * The id.
	 */
	private int id;
	
	/**
	 * The delay.
	 */
	private int delay;
	
	/**
	 * The angle.
	 */
	private int angle;

	/**
	 * The speed.
	 */
	private int speed;
	
	/**
	 * The start height.
	 */
	private int startHeight;
	
	/**
	 * The end height.
	 */
	private int endHeight;
	
	/**
	 * The lockon.
	 */
	private Entity lockon;
	
	/**
	 * The slope.
	 */
	private int slope;

	/**
	 * The starting location
	 */
	private Location start;

	/**
	 * The finishing location
	 */
	private Location finish;
	
	/**
	 * The radius that the projectile is launched from.
	 */
	private int radius;
	
	/**
	 * Creates a graphic.
	 * @param id The id.
	 * @param delay The delay.
	 */
	private Projectile(Location start, Location finish, int id, int delay, int angle, int speed, int startHeight, int endHeight, Entity lockon, int slope, int radius) {
		this.id = id;
		this.delay = delay;
		this.start = start;
		this.finish = finish;
		this.angle = angle;
		this.speed = speed;
		this.startHeight = startHeight;
		this.endHeight = endHeight;
		this.lockon = lockon;
		this.slope = slope;
		this.radius = radius;
	}
	
	/**
	 * Sets the lockon entity.
	 * @param entity The lockon.
	 */
	public void setLockon(Entity entity) {
		if(entity == null) {
			return;
		}
		this.lockon = entity;
		this.finish = entity.getLocation();
	}
	
	private int hitDelay;
	
	/**
	 * The hit delay of the projectile.
	 * <notable>you should set speed first</notable>
	 */
	public int getHitDelay() {
		return hitDelay;
	}
	
	public void setSpeedRange(Entity attacker, Entity victim) {
		this.start = attacker.getLocation();
		this.finish = victim.getLocation();
		int gfxDelay;
		if(attacker.getLocation().isWithinDistance(attacker, victim, 1)) {
			speed = 20;
		} else if(attacker.getLocation().isWithinDistance(attacker, victim, 3)) {
			speed = 25;
		} else if(attacker.getLocation().isWithinDistance(attacker, victim, 8)) {
			speed = 30;
		} else {
			speed = 40;
		}
		gfxDelay = speed + 20;
		hitDelay = (int) ((gfxDelay / 20) - 2);
	}
	
	/**
	 * Sets the speed.
	 */
	public void setSpeedMagic(Entity cast, Entity source) {
		this.start = cast.getLocation();
		this.finish = source.getLocation();
		int gfxDelay;
		if(cast.getLocation().isWithinDistance(cast, source, 1)) {
			speed = 30;
		} else if(cast.getLocation().isWithinDistance(cast, source, 5)) {
			speed = 40;
		} else if(cast.getLocation().isWithinDistance(cast, source, 8)) {
			speed = 45;
		} else {
			speed = 55;
		}
		gfxDelay = speed + 20;
		hitDelay = (int) ((gfxDelay / 20) - 1);
	}
	
	/**
	 * Gets the id.
	 * @return The id.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Gets the graphic for the projectile.
	 * @return The graphic.
	 */
	public Graphic getGraphic() {
		return Graphic.create(id);
	}
	
	/**
	 * Gets the delay.
	 * @return The delay.
	 */
	public int getDelay() {
		return delay;
	}

	/**
	 * Gets the angle.
	 * @return The angle.
	 */
	public int getAngle() {
		return angle;
	}

	/**
	 * Gets the lockon.
	 * @return The lockon.
	 */
	public Entity getLockon() {
		return lockon;
	}

	/**
	 * Gets the slope.
	 * @return The slope.
	 */
	public int getSlope() {
		return slope;
	}
	
	/**
	 * Gets the start height.
	 * @return The start height.
	 */
	public int getStartHeight() {
		return startHeight;
	}

	/**
	 * Gets the end height.
	 * @return The end height.
	 */
	public int getEndHeight() {
		return endHeight;
	}
	
	/**
	 * Gets the speed.
	 * @return The speed.
	 */
	public int getSpeed() {
		return speed;
	}
	
	/**
	 * Gets the starting location.
	 * @return The starting location.
	 */
	public Location getStart() {
		return start;
	}

	/**
	 * Gets the finishing location.
	 * @return The finishing location.
	 */
	public Location getFinish() {
		return finish;
	}

	/**
	 * Gets the radius.
	 * @return The radius.
	 */
	public int getRadius() {
		return radius;
	}
}