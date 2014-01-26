package org.hyperion.rs2.model.content.skills.prayer;

import org.hyperion.rs2.model.Player;

/**
 * A prayer handler to handle "prayers" of a player. NOT THE SKILL.
 * @author Thomas Nappo
 */
public class PrayerHandler {
	
	/**
	 * The player we will handle prayer for.
	 */
	@SuppressWarnings("unused")
	private Player player;
	
	/**
	 * Constructs a new prayer handler for a specific player.
	 * @param player The player we will handle prayer for.
	 */
	public PrayerHandler(Player player) {
		this.player = player;
	}

}
