package org.hyperion.rs2.model.region.boundary;

import org.hyperion.rs2.model.Player;

/**
 * Manages a certain boundary regions for a specific player 
 * @author Thomas Nappo
 */
public class BoundaryManager {
	
	/**
	 * The player of this boundary manager.
	 */
	private Player player;
	
	/**
	 * Constructs a new boundary manager.
	 * @param player The player who we are managing boundaries for.
	 */
	public BoundaryManager(Player player) {
		this.player = player;
	}
	
	/**
	 * Determines whether or not this player is in a certain boundary.
	 * @param boundary The boundary that we are checking.
	 * @return <code>true</code> if the player is within the boundary, <code>false</code> otherwise.
	 */
	public boolean inBoundary(Boundary boundary) {
		return player.getLocation().getX() >= boundary.getBottomLeft().getX() &&
				player.getLocation().getX() <= boundary.getUpperRight().getX() &&
				player.getLocation().getY() >= boundary.getBottomLeft().getY() &&
				player.getLocation().getY() <= boundary.getBottomLeft().getY();
	}

}
