package org.hyperion.rs2.action.impl;

import org.hyperion.rs2.action.Action;
import org.hyperion.rs2.model.Item;
import org.hyperion.rs2.model.Player;
import org.hyperion.rs2.model.container.Equipment;
import org.hyperion.rs2.model.container.Equipment.EquipmentType;

/**
 * Switches a player's weapon.
 * @author Thomas Nappo
 */
public class WeaponSwitchAction extends Action {

	/**
	 * Constructs a new weapon switch action.
	 */
	public WeaponSwitchAction(Player player, Item item, int slot) {
		super(player, 0); this.item = item; this.slot = slot;
	}

	@Override
	public QueuePolicy getQueuePolicy() {
		return QueuePolicy.ALWAYS;
	}

	@Override
	public WalkablePolicy getWalkablePolicy() {
		return WalkablePolicy.WALKABLE;
	}

	@Override
	public void terminate() {
		super.stop();
	}
	
	/**
	 * Data required to do the switch.
	 */
	private Item item; private int slot;

	@Override
	public void execute() {
		if(player == null) {
			terminate();
		}
		if(player.isDead() || item == null) {
			terminate();
		}
		EquipmentType type = Equipment.getType(item);
		Item oldEquip = null;
		boolean stackable = item.getDefinition().isStackable();
		if (player.getEquipment().isSlotUsed(type.getSlot())) {
			oldEquip = player.getEquipment().get(type.getSlot());
			if(stackable && oldEquip.getId() == item.getId()) {
				item.setCount(item.getCount() + oldEquip.getCount());
			}
			player.getEquipment().set(type.getSlot(), null);
		}
		player.getInventory().set(slot, null);
		if(oldEquip != null) {
			if(!oldEquip.getDefinition().isStackable() || !stackable) {
				player.getInventory().add(oldEquip);
			}
		}
		player.getEquipment().set(type.getSlot(), item);
		player.getBonus().sendBonus();
		if(type.equals(EquipmentType.WEAPON)) {
			player.getActionQueue().clearNonWalkableActions();
		}
		player.getCombatAssistant().getSpecialHandler().setUsingSpecial(false);
		terminate();
	}

}
