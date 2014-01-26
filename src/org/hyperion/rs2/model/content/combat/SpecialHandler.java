package org.hyperion.rs2.model.content.combat;

import org.hyperion.rs2.model.Item;
import org.hyperion.rs2.model.Player;
import org.hyperion.rs2.model.container.Equipment;

/**
 * Handles actions related to special attacks for a player.
 * @author Thomas Nappo
 */
public class SpecialHandler {
	
	/**
	 * Determines whether or not this player is using special attack.
	 */
	private boolean usingSpecial;
	
	/**
	 * Determines whether or not this player is using special attack.
	 * @return <code>true</code> if they are, <code>false</code> otherwise.
	 */
	public boolean isUsingSpecial() {
		return usingSpecial;
	}
	
	/**
	 * Sets whether or not this player is using special attack.
	 * @param value The new value, <code>true</code> if they are.
	 */
	public void setUsingSpecial(boolean value) {
		this.usingSpecial = value;
	}
	
	/**
	 * The amount of special attack energy this player has.
	 */
	private int specialAmount = 100;
	
	/**
	 * Gets the amount of special attack energy this player has.
	 * @return The amount of special attack energy this player has.
	 */
	public int getSpecialAmount() {
		return specialAmount;
	}
	
	/**
	 * Sets the special amount energy this player has.
	 * @param amount The new amount of special energy.
	 */
	public void setSpecialAmount(int amount) {
		this.specialAmount = amount;
	}
	
	/**
	 * Decreases the special amount energy this player has, auto sets to 0, if went under 0.
	 * @param amount The amount to decrease by.
	 */
	public void decreaseSpecialAmount(int amount) {
		this.specialAmount = (specialAmount - amount);
		if(specialAmount < 0) {
			specialAmount = 0;
		}
	}
	
	/**
	 * Updates the special attack bar.
	 */
	public void updateBar() {
		//TODO, needs action sender, ect.
	}
	
	/**
	 * Gets the amount of special attack required for the weapon the player is wielding.
	 * @return The amount of special attack required for the weapon the player is wielding.
	 */
	public int getSpecialAmountForWeapon() {
		if(player == null) {
			return 0;
		}
		if(player.getEquipment().get(Equipment.SLOT_WEAPON) == null) {
			return 0;
		}
		Item weapon = player.getEquipment().get(Equipment.SLOT_WEAPON);
		switch(weapon.getId()) {
		case 5698: //DDS
			return 25;
		case 4151: //Whip
			return 50;
		case 861: //Magic shortbow
			return 55;
		}
		return 0;
	}
	
	/**
	 * The player who we are handling special attacks for.
	 */
	private Player player;
	
	/**
	 * Gets the player who we are handling special attacks for.
	 * @return The player who we are handling special attacks for.
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Constructs a new player.
	 * @param player The player who were are handling special attacks for.
	 */
	public SpecialHandler(Player player) {
		this.player = player;
	}

}
