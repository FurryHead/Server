package org.hyperion.rs2.model.region.boundary;

import org.hyperion.rs2.model.Location;

/**
 * A certain region of the map that pertains certain attributes.
 * @author Thomas Nappo
 */
public class Boundary {
	
	/**
	 * The location parameters.
	 */
	private Location bottomLeft, upperRight;
	
	/**
	 * Gets the bottom right location of this boundary.
	 * @return The bottom right location of this boundary.
	 */
	public Location getBottomLeft() {
		return bottomLeft;
	}
	
	/**
	 * Gets the upper left location of this boundary.
	 * @return The upper left location of this boundary.
	 */
	public Location getUpperRight() {
		return upperRight;
	}
	
	/**
	 * Constructs a new boundary.
	 * @param bottomLeft The bottom left location of this boundary.
	 * @param upperRight The upper left location of this boundary.
	 */
	public Boundary(Location bottomLeft, Location upperRight) {
		this.bottomLeft = bottomLeft; this.upperRight = upperRight;
	}
	
	/**
	 * Gets a boundary for a data enumeration.
	 * @param data The data enumeration to acquire for.
	 * @return The data enumeration to acqurie for, or <code>null</code> if we couldn't find it.
	 */
	public static Boundary boundaryFor(Data data) {
		for(Data entry : Data.values()) {
			if(entry.equals(data)) {
				return entry.getBoundary();
			}
		}
		return null;
	}

	/**
	 * Enumeration of boundaries already defined.
	 * @author Thomas Nappo
	 */
	enum Data {
		
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
		Data(Boundary boundary) {
			this.boundary = boundary;
		}

	}

}
