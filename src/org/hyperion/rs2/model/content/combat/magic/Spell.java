package org.hyperion.rs2.model.content.combat.magic;

/**
 * A spell that can be performed.
 * @author Thomas Nappo
 */
public abstract class Spell {
	
	/**
	 * The spell id of this spell.
	 */
	protected int spellId;
	
	/**
	 * Gets the spell id of this spell.
	 * @return The spell id of this spell.
	 */
	public int getSpellId() {
		return spellId;
	}
	
	/**
	 * Casts the spell.
	 */
	public abstract void cast();
	
	/**
	 * Constructs a new spell.
	 * @param spellId The spell id of this spell.
	 */
	public Spell(int spellId) {
		this.spellId = spellId;
	}

}
