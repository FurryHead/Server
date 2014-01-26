package org.hyperion.rs2.model.content.combat;

/**
 * Enumeration of combat types.
 * @author Thomas Nappo
 */
public enum CombatType {
	
	MELEE(1),
	RANGED(8),
	MAGIC(10);
	
	/**
	 * The distance at which this combat type can attack from.
	 */
	private int distance;
	
	/**
	 * Gets the distance at which this combat type can attack from.
	 * @return The distance at which this combat type can attack from.
	 */
	public int getDistance() {
		return distance;
	}
	
	/**
	 * Constructs a new combat type enumeration.
	 * @param distance The distance at which this combat type can attack from.
	 */
	CombatType(int distance) {
		this.distance = distance;
	}

}
