package org.hyperion.rs2.model.region.boundary;

import org.hyperion.rs2.model.Location;

/**
 * Enumeration of boundaries already defined.
 * @author Thomas Nappo
 */
public enum Boundaries {
	
	VARROCK(new Boundary(Location.create(3200, 3200), Location.create(3211, 3211)));
	
	/**
	 * The boundary of this enumeration.
	 */
	private Boundary boundary;
	
	/**
	 * Gets the physical boundary of this enumeration.
	 * @return The physical boundary of this enumeration.
	 */
	public Boundary getBoundary() {
		return boundary;
	}
	
	/**
	 * Constructs a new boundaries enumeration.
	 * @param boundary The physical boundary to contain.
	 */
	Boundaries(Boundary boundary) {
		this.boundary = boundary;
	}

}
