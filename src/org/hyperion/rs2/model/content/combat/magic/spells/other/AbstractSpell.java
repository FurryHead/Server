package org.hyperion.rs2.model.content.combat.magic.spells.other;

import org.hyperion.rs2.model.Animation;
import org.hyperion.rs2.model.Entity;
import org.hyperion.rs2.model.Graphic;
import org.hyperion.rs2.model.Player;
import org.hyperion.rs2.model.Projectile;
import org.hyperion.rs2.model.content.combat.magic.spells.ProjectileBasedSpell;

/**
 * A spell in which applys certain special effects to the victim.
 * @author Thomas Nappo
 */
public abstract class AbstractSpell extends ProjectileBasedSpell {

	/**
	 * Constructs a new abstract spell.
	 */
	public AbstractSpell(int spellId, Player caster, Entity defender,
			Animation cast, Graphic castGfx, Projectile projectile,
			Graphic endGfx) {
		super(spellId, caster, defender, cast, castGfx, projectile, endGfx);
	}

	/**
	 * Applys special effects for the spell.
	 */
	public abstract void applyEffects();

}
