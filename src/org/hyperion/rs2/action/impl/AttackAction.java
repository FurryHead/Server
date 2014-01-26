package org.hyperion.rs2.action.impl;

import org.hyperion.rs2.action.Action;
import org.hyperion.rs2.model.Entity;
import org.hyperion.rs2.model.Player;
import org.hyperion.rs2.model.World;
import org.hyperion.rs2.model.content.combat.CombatHandler;
import org.hyperion.rs2.model.content.combat.CombatType;
import org.hyperion.rs2.model.EntityCooldowns.CooldownFlags;
import org.hyperion.rs2.tickable.Tickable;

/**
 * Handles an action for an attacking player.
 * @author Thomas Nappo
 */
public class AttackAction extends Action {
	
	/**
	 * The victim of this attack action.
	 */
	private Entity victim;
	
	/**
	 * The type of attack.
	 */
	private CombatType type;
	
	/**
	 * Constructor method for this action.
	 * @param player The attacker.
	 * @param victim The attacked.
	 * @param type The type of attack.
	 */
	public AttackAction(Player player, Entity victim, CombatType type) {
		super(player, 600);
		this.victim = victim; this.type = type;
	}

	@Override
	public QueuePolicy getQueuePolicy() {
		return QueuePolicy.NEVER;
	}

	@Override
	public WalkablePolicy getWalkablePolicy() {
		return WalkablePolicy.FOLLOW;
	}
	
	@Override
	public void execute() {
		final Player player = getPlayer();
		if(CombatHandler.canAttack(player, victim)) {
			switch(type) {
			default:
				if(!player.getEntityCooldowns().get(CooldownFlags.MELEE_SWING)) {
					if(player.isInCombat() && !player.getLocation().isWithinDistance(player, victim, type.getDistance())) {
						return;
					}
					player.setInCombat(true);
					player.setAggressorState(true);
					CombatHandler.doAttack(player, victim, type);
					player.getEntityCooldowns().flag(CooldownFlags.MELEE_SWING, CombatHandler.getAttackSpeed(player) * 600, player);
					player.getAttributeMap().put("WIELD_DELAY", 2);
					player.getAttributeMap().put("LAST_ATTACK", 0);
					World.getWorld().submit(new Tickable(1) {
						@Override
						public void execute() {
							int value = player.getAttribute("WIELD_DELAY");
							if(value < 1) {
								this.stop();
								return;
							}
							player.getAttributeMap().remove("WIELD_DELAY");
							player.getAttributeMap().put("WIELD_DELAY", value - 1);
						}
					});
				}
				break;
			case RANGED:
				if(!player.getEntityCooldowns().get(CooldownFlags.RANGED_SHOT)) {
					if(player.isInCombat() && !player.getLocation().isWithinDistance(player, victim, type.getDistance())) {
						return;
					}
					player.setInCombat(true);
					player.setAggressorState(true);
					CombatHandler.doAttack(player, victim, type);
					player.getEntityCooldowns().flag(CooldownFlags.RANGED_SHOT, CombatHandler.getAttackSpeed(player) * 600, player);
					player.getAttributeMap().put("WIELD_DELAY", 2);
					player.getAttributeMap().put("LAST_ATTACK", 0);
					World.getWorld().submit(new Tickable(1) {
						@Override
						public void execute() {
							int value = player.getAttribute("WIELD_DELAY");
							if(value < 1) {
								this.stop();
								return;
							}
							player.getAttributeMap().remove("WIELD_DELAY");
							player.getAttributeMap().put("WIELD_DELAY", value - 1);
						}
					});
				}
				break;
			}
		} else {
			this.terminate();
		}
	}

	@Override
	public void terminate() {
		if(!(getPlayer() == null)) {
			getPlayer().setInteractingEntity(null);
			getPlayer().getCombatAssistant().setAttackAction(null);
		}
		super.stop();
	}

}
