package org.hyperion.rs2.model.content.combat.impl;

import org.hyperion.rs2.model.Damage.Hit;
import org.hyperion.rs2.model.Entity;
import org.hyperion.rs2.model.content.combat.Combat;

/**
 * Magic combat handler.
 * @author Thomas Nappo
 */
public class MagicCombat extends Combat {
	
	/**
	 * The spell id to cast.
	 */
	@SuppressWarnings("unused")
	private int spellId;

	/**
	 * Constructs a new magic combat instance.
	 * @param attacker The attacking entity.
	 * @param defender The defending entity.
	 * @param spellId The spell id to cast.
	 */
	public MagicCombat(Entity attacker, Entity defender, int spellId) {
		super(attacker, defender);
		this.spellId = spellId;
	}

	@Override
	protected void attack() {
		
	}

	@Override
	protected Hit calculateHit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected double calculateAccuracy() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void special() {
		// TODO Auto-generated method stub
		
	}

}
