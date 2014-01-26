package org.hyperion.rs2.model.content.combat.magic.spells;

import org.hyperion.rs2.event.Event;
import org.hyperion.rs2.model.Animation;
import org.hyperion.rs2.model.Damage.Hit;
import org.hyperion.rs2.model.Damage.HitType;
import org.hyperion.rs2.model.Entity;
import org.hyperion.rs2.model.Graphic;
import org.hyperion.rs2.model.Player;
import org.hyperion.rs2.model.Projectile;
import org.hyperion.rs2.model.World;
import org.hyperion.rs2.model.content.combat.magic.Spell;
import org.hyperion.rs2.task.Task;
import org.hyperion.rs2.tickable.Tickable;
import org.hyperion.rs2.util.Misc;

/**
 * A spell that sends a projectile.
 * @author Thomas Nappo
 */
public abstract class ProjectileBasedSpell extends Spell {
	
	/**
	 * The caster of the spell.
	 */
	protected Player caster;
	
	/**
	 * The defender who the spell is being casted upon.
	 */
	protected Entity defender;
	
	/**
	 * The animation that occurs when one casts a spell.
	 */
	protected Animation cast;
	
	/**
	 * The graphics that occur on casting/spell appending.
	 */
	protected Graphic castGfx, endGfx;
	
	/**
	 * The projectile sent for the spell.
	 */
	protected Projectile projectile;

	/**
	 * Constructs a new projectile based spell.
	 */
	public ProjectileBasedSpell(int spellId, Player caster, Entity defender, Animation cast, Graphic castGfx, Projectile projectile, Graphic endGfx) {
		super(spellId);
		this.cast = cast; this.castGfx = castGfx; this.projectile = projectile; this.endGfx = endGfx;
	}
	
	/**
	 * Casts the standard spell procedure.
	 * @param delay The amount of time to wait afterwards the spell is casted.
	 * @param afterTask After the delay this task is casted.
	 */
	protected void standardCast(final int delay, final Task afterTask) {
		caster.playAnimation(cast);
		caster.playGraphics(castGfx);
		World.getWorld().submit(new Tickable(2) {
			@Override
			public void execute() {
				this.stop();
				caster.playProjectile(projectile);
				World.getWorld().submit(new Tickable(2) {
					@Override
					public void execute() {
						this.stop();
						defender.playGraphics(endGfx);
						World.getWorld().submit(new Tickable(1) {
							@Override
							public void execute() {
								this.stop();
								int verdict = Misc.random(5);
								((Player) defender).getHitQueue().getHits().add(new Hit(verdict, verdict > 0 ? HitType.NORMAL_DAMAGE : HitType.NO_DAMAGE));
								World.getWorld().submit(new Event(delay) {
									@Override
									public void execute() {
										this.stop();
										World.getWorld().submit(afterTask);
									}
								});
							}
						});
					}
				});
			}
		});
	}

}
