package org.hyperion.rs2.model;

public class GroundItem {

	/**
	 * The item this ground item represents.
	 */
	private final Item item;

	/**
	 * The location of the ground item.
	 */
	private final Location location;

	/**
	 * A flag representing whether or not the ground item is private.
	 */
	private final boolean isPrivate;

	/**
	 * Constructs a new ground item.
	 * 
	 * @param item
	 *            The item this ground item represents.
	 * @param removeDelay
	 *            The delay to remove.
	 * @param amount
	 *            The amount of stacked items.
	 */
	public GroundItem(Item item, Location location, boolean isPrivate) {
		this.item = item;
		this.location = location;
		this.isPrivate = isPrivate;
	}
	
	/**
	 * @return <Code>true</code> if is private <code>false</code> if not.
	 */
	public boolean isPrivate() {
		return isPrivate;
	}

	/**
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}
}