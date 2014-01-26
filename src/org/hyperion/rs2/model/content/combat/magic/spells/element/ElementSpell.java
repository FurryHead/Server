package org.hyperion.rs2.model.content.combat.magic.spells.element;

import org.hyperion.rs2.model.Animation;
import org.hyperion.rs2.model.Entity;
import org.hyperion.rs2.model.Graphic;
import org.hyperion.rs2.model.Player;
import org.hyperion.rs2.model.Projectile;
import org.hyperion.rs2.model.content.combat.magic.spells.ProjectileBasedSpell;

/**
 * A modern element (air, water, earth, fire) spell.
 * @author Thomas Nappo
 */
public abstract class ElementSpell extends ProjectileBasedSpell {
	
	/**
	 * Constructs a new element spell.
	 * @param elementType The type of element this spell is.
	 */
	public ElementSpell(int spellId, Player caster, Entity defender,
			Animation cast, Graphic castGfx, Projectile projectile,
			Graphic endGfx, ElementType elementType) {
		super(spellId, caster, defender, cast, castGfx, projectile, endGfx);
		this.elementType = elementType;
	}

	/**
	 * The type of element this spell is.
	 */
	protected ElementType elementType;
	
}
