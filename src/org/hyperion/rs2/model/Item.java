package org.hyperion.rs2.model;

/**
 * Represents a single item.
 * @author Graham Edgecombe
 *
 */
public class Item {
	
	/**
	 * The id.
	 */
	private int id;
	
	/**
	 * The number of items.
	 */
	private int count;
	
	/**
	 * Creates a single item.
	 * @param id The id.
	 */
	public Item(int id) {
		this(id, 1);
	}
	
	/**
	 * Creates a stacked item.
	 * @param id The id.
	 * @param count The number of items.
	 * @throws IllegalArgumentException if count is negative.
	 */
	public Item(int id, int count) {
		if(count < 0) {
			throw new IllegalArgumentException("Count cannot be negative.");
		}
		this.id = id;
		this.count = count;
	}
	
	/**
	 * Gets the definition of this item.
	 * @return The definition.
	 */
	public ItemDefinition getDefinition() {
		return ItemDefinition.forId(id);
	}
	
	/**
	 * Gets the item id.
	 * @return The item id.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Gets the attack speed of this item.
	 * @return The attack speed of this item.
	 */
	public int getAttackSpeed() {
		int s = 5;
		switch(id) {
		case 4151:
			return 4;
		case 861:
			return 4;
		}
		return s;
	}
	
	/**
	 * Gets the count.
	 * @return The count.
	 */
	public int getCount() {
		return count;
	}
	
	@Override
	public String toString() {
		return Item.class.getName() + " [id=" + id + ", count=" + count + "]";
	}

	/**
	 * Sets the count of this item.
	 * @param count The new count.
	 */
	public void setCount(int count) {
		this.count = count;
	}

}
