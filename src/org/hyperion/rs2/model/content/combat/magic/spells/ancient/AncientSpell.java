package org.hyperion.rs2.model.content.combat.magic.spells.ancient;

import org.hyperion.rs2.GameEngine;
import org.hyperion.rs2.model.Animation;
import org.hyperion.rs2.model.Entity;
import org.hyperion.rs2.model.Graphic;
import org.hyperion.rs2.model.Player;
import org.hyperion.rs2.model.Projectile;
import org.hyperion.rs2.model.content.combat.magic.spells.other.AbstractSpell;
import org.hyperion.rs2.task.Task;

/**
 * An ancient magicks spell.
 * @author Thomas Nappo
 */
public abstract class AncientSpell extends AbstractSpell {

	/**
	 * Constructs a new ancient magicks spell.
	 */
	public AncientSpell(int spellId, Player caster, Entity defender,
			Animation cast, Graphic castGfx, Projectile projectile,
			Graphic endGfx) {
		super(spellId, caster, defender, cast, castGfx, projectile, endGfx);
	}
	
	@Override
	public void cast() {
		this.standardCast(0, new Task() {
			@Override
			public void execute(GameEngine context) {
				applyEffects();
			}
		});
	}

}
