package org.hyperion.rs2.model.content.combat;

import org.hyperion.rs2.model.Entity;
import org.hyperion.rs2.model.Player;
import org.hyperion.rs2.model.Damage.Hit;

/**
 * Abstract class of a combat instance.
 * @author Thomas Nappo
 */
public abstract class Combat {
	
	/**
	 * Starts a new attack.
	 */
	protected abstract void attack();
	
	/**
	 * Calculates a hit for the attacker who is attacking the defender.
	 */
	protected abstract Hit calculateHit();
	
	/**
	 * Calculates accuracy for the attacker who is attacking the defender.
	 */
	protected abstract double calculateAccuracy();
	
	/**
	 * Gets the attacker as a player.
	 * @return The attacker as a player.
	 */
	protected Player a() {
		return (Player) attacker;
	}
	
	/**
	 * Gets the defender as a player.
	 * @return The defender as a player.
	 */
	protected Player d() {
		return (Player) defender;
	}
	
	/**
	 * Appends a special attack move from the attacker to the defender.
	 */
	protected abstract void special();
	
	/**
	 * Checks whether attacker & defender are both players.
	 * @return <code>true</code> if they are both players, <code>false</code> if not.
	 */
	protected boolean bothPlayers() {
		return attacker instanceof Player && defender instanceof Player;
	}
	
	/**
	 * Defender + attacker entity instances.
	 */
	protected final Entity attacker, defender;
	
	/**
	 * Gets the attacker entity of this combat instance.
	 * @return The attacker entity of this combat instance.
	 */
	public Entity getAttacker() {
		return attacker;
	}
	
	/**
	 * Gets the defender entity of this combat instance.
	 * @return The defender entity of this combat instance.
	 */
	public Entity getDefender() {
		return defender;
	}
	
	/**
	 * Plays the block animatino for the defender.
	 */
	protected void block() {
		if(d() == null) {
			return;
		}
		if(d().isDead()) {
			return;
		}
		if(!d().getAttributeMap().containsKey("LAST_ATTACK") || d().getAttributeMap().get("LAST_ATTACK") > 3) {
			d().playAnimation(d().getCombatAssistant().getDefenseAnimation());
			return;
		}
	}
	
	/**
	 * Constructs a new combat instance.
	 * @param attacker The attacking instance.
	 * @param defender The defending instance.
	 */
	public Combat(Entity attacker, Entity defender) {
		this.attacker = attacker; this.defender = defender;
	}
	
	/**
	 * Inflicts damage to the defender from the attacker.
	 * @param damage The damage to be applied.
	 */
	protected void inflictDamage(Hit damage) {
		inflictDamage(attacker, defender, damage);
	}
	
	/**
	 * Inflicts damage to an entity from an entity.
	 * @param attacker The attacker, or "source" of the damage.
	 * @param defender The defender, or "intaker" of the damage.
	 * @param damage The damage to be applied.
	 */
	protected void inflictDamage(Entity attacker, Entity defender, Hit damage) {
		if((attacker instanceof Player) && (defender != null)) {
			Player p = (Player) defender;
			p.getHitQueue().getHits().add(damage);
		}
	}

}
