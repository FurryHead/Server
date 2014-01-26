package org.hyperion.rs2.model.content.combat.impl;

import org.hyperion.rs2.model.Damage.Hit;
import org.hyperion.rs2.model.Damage.HitType;
import org.hyperion.rs2.model.Entity;
import org.hyperion.rs2.model.Projectile;
import org.hyperion.rs2.model.World;
import org.hyperion.rs2.model.content.combat.Combat;
import org.hyperion.rs2.tickable.Tickable;
import org.hyperion.rs2.util.Misc;

/**
 * Ranged combat handler.
 * @author Thomas Nappo
 */
public class RangeCombat extends Combat {

	/**
	 * Constructs a new ranged combat instance.
	 * @param attacker The attacking entity.
	 * @param defender The defending entity.
	 */
	public RangeCombat(Entity attacker, Entity defender) {
		super(attacker, defender);
	}

	@Override
	public void attack() {
		//Lots of work to do, this is extremely confusing with getting the projectile right -.-
		if(bothPlayers()) {
			a().playAnimation(a().getCombatAssistant().getAttackAnimation());
			a().playGraphics(a().getCombatAssistant().getPullbackGraphic());
			World.getWorld().submit(new Tickable(2) {
				@Override
				public void execute() {
					this.stop();
					a().getActionSender().sendMessage("LAUNCH ARROW!");
					final Projectile p = 
							Projectile.create(a().getLocation(), d().getLocation(), a().getCombatAssistant().getProjectile(), 0, 50, 28, 43, 35, d(), 3, 64);
					p.setLockon(d());
					//p.setSpeedRange(attacker, defender);
					a().playProjectile(p);
					block();
					World.getWorld().submit(new Tickable(1) {
						@Override
						public void execute() {
							this.stop();
							int verdict = Misc.random(5);
							inflictDamage(new Hit(verdict, verdict > 0 ? HitType.NORMAL_DAMAGE : HitType.NO_DAMAGE));
						}
					});
				}
			});
		}
	}

	@Override
	protected Hit calculateHit() {
		@SuppressWarnings("unused")
		double base = 1/64;
		
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
