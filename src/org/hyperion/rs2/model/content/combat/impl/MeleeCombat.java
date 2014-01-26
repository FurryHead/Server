package org.hyperion.rs2.model.content.combat.impl;

import org.hyperion.rs2.model.Animation;
import org.hyperion.rs2.model.Bonuses;
import org.hyperion.rs2.model.Damage.Hit;
import org.hyperion.rs2.model.Damage.HitType;
import org.hyperion.rs2.model.Entity;
import org.hyperion.rs2.model.Graphic;
import org.hyperion.rs2.model.Item;
import org.hyperion.rs2.model.Skills;
import org.hyperion.rs2.model.World;
import org.hyperion.rs2.model.container.Equipment;
import org.hyperion.rs2.model.content.combat.Combat;
import org.hyperion.rs2.model.content.combat.SpecialHandler;
import org.hyperion.rs2.tickable.Tickable;
import org.hyperion.rs2.util.Misc;

/**
 * Melee combat handler.
 * @author Thomas Nappo
 */
public class MeleeCombat extends Combat {
	
	/**
	 * Constructs a new melee combat instance.
	 * @param attacker The attacker instance.
	 * @param defender The defender instance.
	 */
	public MeleeCombat(Entity attacker, Entity defender) {
		super(attacker, defender);
	}

	@Override
	public void attack() {
		if(bothPlayers()) {
			SpecialHandler s = a().getCombatAssistant().getSpecialHandler();
			if(s.isUsingSpecial()) {
				special();
			} else {
				a().playAnimation(a().getCombatAssistant().getAttackAnimation());
				block();
				World.getWorld().submit(new Tickable(1) {
					@Override
					public void execute() {
						this.stop();
						if(a().isDead() || d().isDead()) {
							return;
						}
						inflictDamage(calculateHit());
					}
				});
			}
		}
	}

	@Override
	protected Hit calculateHit() {
		int verdict = 0, maxHit;
		double baseDamage, effectiveStrength;
		//Could use a lot of fixing - Thomas
		if(bothPlayers()) {
			effectiveStrength = (int) (a().getSkills().getLevel(Skills.STRENGTH));
			baseDamage = 13 + effectiveStrength + 
					(a().getBonuses(Bonuses.STRENGTH) / 8) + ((effectiveStrength * a().getBonuses(Bonuses.STRENGTH)) / 64);
			maxHit = (int) (baseDamage / 10);
			verdict = (int) Misc.random(maxHit);
			if(verdict > maxHit) {
				verdict = maxHit;
			}
			if(verdict > d().getHitpoints()) {
				verdict = d().getHitpoints();
			}
			if(calculateAccuracy() < 1) {
				verdict = 0;
			}
		}
		return new Hit(verdict, verdict > 0 ? HitType.NORMAL_DAMAGE : HitType.NO_DAMAGE);
	}

	@Override
	protected double calculateAccuracy() {
		//TODO: Bonus stats, attack styles
		return ((Misc.random(a().getSkills().getLevel(Skills.ATTACK)) + 1) / (Misc.random(d().getSkills().getLevel(Skills.DEFENCE)) + 1));
	}

	@Override
	protected void special() {
		final SpecialHandler s = a().getCombatAssistant().getSpecialHandler();
		s.decreaseSpecialAmount(s.getSpecialAmountForWeapon());
		s.setUsingSpecial(false);
		Item weapon = a().getEquipment().get(Equipment.SLOT_WEAPON);
		if(weapon == null) {
			return; // We can't special attack without a weapon.
		}
		switch(weapon.getId()) {
		case 5698: //DDS
			d().playAnimation(d().getCombatAssistant().getDefenseAnimation());
			a().playAnimation(Animation.create(1062));
			a().playGraphics(Graphic.create(252, 0, 100));
			World.getWorld().submit(new Tickable(1) {
				@Override
				public void execute() {
					this.stop();
					if(d().isDead()) {
						return;
					}
					int verdict = (int) (calculateHit().getDamage() * 1.50);
					int verdict2 = (int) (calculateHit().getDamage());
					int total = verdict + verdict2;
					if(total > d().getHitpoints()) {
						verdict = total / 2;
						verdict2 = total / 2;
						total = verdict + verdict2;
					}
					inflictDamage(new Hit(verdict, verdict > 0 ? HitType.NORMAL_DAMAGE : HitType.NO_DAMAGE));
					if(d().isDead() || ((d().getHitpoints() - total) < 0)) {
						return;
					}
					inflictDamage(new Hit(verdict2, verdict2 > 0 ? HitType.NORMAL_DAMAGE : HitType.NO_DAMAGE));
				}
			});
			break;
		}
	}

}
