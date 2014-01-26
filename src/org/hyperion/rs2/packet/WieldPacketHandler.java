package org.hyperion.rs2.packet;

import org.hyperion.rs2.action.Action;
import org.hyperion.rs2.action.impl.WeaponSwitchAction;
import org.hyperion.rs2.model.Item;
import org.hyperion.rs2.model.Player;
import org.hyperion.rs2.model.World;
import org.hyperion.rs2.model.container.Equipment.EquipmentType;
import org.hyperion.rs2.model.container.Equipment;
import org.hyperion.rs2.model.container.Inventory;
import org.hyperion.rs2.net.Packet;
import org.hyperion.rs2.tickable.Tickable;

/**
 * Handles the 'wield' option on items.
 * @author Graham Edgecombe
 * @author Thomas Nappo
 */
public class WieldPacketHandler implements PacketHandler {

	@Override
	public void handle(final Player player, Packet packet) {
		int id = packet.getShort() & 0xFFFF;
		int slot = packet.getShortA() & 0xFFFF;
		int interfaceId = packet.getShortA() & 0xFFFF;

		switch(interfaceId) {
		case Inventory.INTERFACE:
			if(slot >= 0 && slot < Inventory.SIZE) {
				if(player.hasAttribute("SWITCH_QUEUE")) {
					return;
				}
				Item item = player.getInventory().get(slot);
				if(item != null && item.getId() == id) {
					EquipmentType type = Equipment.getType(item);
					final Action action = new WeaponSwitchAction(player, item, slot);
					final int delay = player.getAttribute("WIELD_DELAY");
					if(delay < 1 || !type.equals(EquipmentType.WEAPON)) {
						action.execute();
						return;
					}
					player.getAttributes().add("SWITCH_QUEUE");
					World.getWorld().submit(new Tickable(delay) {
						@Override
						public void execute() {
							action.execute();
							player.getAttributes().remove("SWITCH_QUEUE");
							this.stop();
						}
					});
				}
				/*Item item = player.getInventory().get(slot);
				if(item != null && item.getId() == id) {
					EquipmentType type = Equipment.getType(item);
					Item oldEquip = null;
					boolean stackable = false;
					if(player.getEquipment().isSlotUsed(type.getSlot()) && !stackable) {
						oldEquip = player.getEquipment().get(type.getSlot());
						player.getEquipment().set(type.getSlot(), null);
					}
					player.getInventory().set(slot, null);
					if(oldEquip != null) {
						player.getInventory().add(oldEquip);
					}
					if(!stackable) {
						player.getEquipment().set(type.getSlot(), item);
					} else {
						player.getEquipment().add(item);
					}
				}
				player.getBonus().sendBonus();
				if(player.getCombatState().getAttackAction() != null) {
					player.getCombatState().getAttackAction().terminate();
				}*/
			}
			break;
		}
	}

}
