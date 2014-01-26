package org.hyperion.rs2.model.content.combat.magic;

import org.hyperion.rs2.model.Player;

/**
 * Assists with magic information for a specific player.
 * @author Thomas Nappo
 */
public class MagicAssistant {
	
	/**
	 * The player of this magic assistant.
	 */
	@SuppressWarnings("unused")
	private Player player;
	
	/**
	 * Constructs a new magic assistant.
	 * @param player The player of this magic assistant.
	 */
	public MagicAssistant(Player player) {
		this.player = player;
	}
	
	/**
	 * Gets a magic spell for an id.
	 * @param id The id to check.
	 * @return The magic spell for the id.
	 */
	/*public Spell spellForId(int id) {
		return id;
	}*/

}
