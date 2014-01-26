package org.hyperion.rs2.packet;

import org.hyperion.rs2.Constants;
import org.hyperion.rs2.action.impl.AttackAction;
import org.hyperion.rs2.model.Item;
import org.hyperion.rs2.model.Player;
import org.hyperion.rs2.model.World;
import org.hyperion.rs2.model.container.Equipment;
import org.hyperion.rs2.model.content.combat.CombatType;
import org.hyperion.rs2.net.Packet;
import org.hyperion.rs2.tickable.Tickable;

/**
 * Handles players clicking other players.
 * @author Thomas Nappo
 */
public class PlayerOptionPacketHandler implements PacketHandler {

	@Override
	public void handle(Player player, Packet packet) {
		switch(packet.getOpcode()) {
		case 128:
			/*
			 * Option 1.
			 */
			option1(player, packet);
			break;
		case 37:
			/*
			 * Option 2.
			 */
			option2(player,  packet);
			break;
		case 227:
			/*
			 * Option 3.
			 */
			option3(player, packet);
			break;
		}
	}

	/**
	 * Handles the first option on a player option menu.
	 * @param player
	 * @param packet
	 */
	private void option1(final Player player, Packet packet) {
		int id = packet.getShort() & 0xFFFF;
		if(id < 0 || id >= Constants.MAX_PLAYERS) {
			return;
		}
		final Player victim = (Player) World.getWorld().getPlayers().get(id);
		if(victim != null) {
			player.setInteractingEntity(victim);
			CombatType combatType = CombatType.MELEE;
			Item weapon = player.getEquipment().get(Equipment.SLOT_WEAPON);
			if(weapon == null) {
				submit(player, victim, combatType);
				return;
			}
			String name = weapon.getDefinition().getName().toLowerCase().trim();
			if(name.contains("bow")) {
				combatType = CombatType.RANGED;
				submit(player, victim, combatType);
				return;
			}
			if(player.getLocation().isWithinInteractionDistance(victim.getLocation())) {
				submit(player, victim, combatType);
			} else {
				player.getAttributes().add("WALKING_TO_ATTACK");
				final CombatType type = combatType;
				World.getWorld().submit(new Tickable(1) {
					@Override
					public void execute() {
						if(!player.hasAttribute("WALKING_TO_ATTACK")) {
							this.stop();
							return;
						}
						if(player.getLocation().isWithinInteractionDistance(victim.getLocation())) {
							submit(player, victim, type);
							player.getAttributes().remove("WALKING_TO_ATTACK");
							this.stop();
						}
					}
				});
			}
			/*player.getWalkingQueue().reset();
			player.getCombatState().setAttackAction(new AttackAction(player, victim));
			player.getActionQueue().addAction(player.getCombatState().getAttackAction());*/
		}
	}
	
	/**
	 * Submit a new attack action.
	 * @param type The combat type.
	 */
	private void submit(Player player, Player victim, CombatType type) {
		player.getCombatAssistant().setAttackAction(new AttackAction(player, victim, type));
		player.getActionQueue().addAction(player.getCombatAssistant().getAttackAction());
	}
	
	/**
	 * Handles the second option on a player option menu.
	 * @param player
	 * @param packet
	 */
	private void option2(Player player, Packet packet) {
		int id = packet.getShort() & 0xFFFF;
		if(id < 0 || id >= Constants.MAX_PLAYERS) {
			return;
		}
	}
	
	/**
	 * Handles the third option on a player option menu.
	 * @param player
	 * @param packet
	 */
	private void option3(Player player, Packet packet) {
		int id = packet.getLEShortA() & 0xFFFF;
		if(id < 0 || id >= Constants.MAX_PLAYERS) {
			return;
		}
	}
		
}

