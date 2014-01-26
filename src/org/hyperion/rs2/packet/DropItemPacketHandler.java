package org.hyperion.rs2.packet;

import org.hyperion.rs2.model.GroundItem;
import org.hyperion.rs2.model.GroundItemManager;
import org.hyperion.rs2.model.Item;
import org.hyperion.rs2.model.entity.player.Player;
import org.hyperion.rs2.net.Packet;

public class DropItemPacketHandler implements PacketHandler {

	@Override
	public void handle(Player player, Packet packet) {
		int id = packet.getShortA();
		packet.getByte();
		packet.getByte();
		int slot = packet.getShortA();
		if (player.getInventory().contains(id)) {
			Item invItem = player.getInventory().get(slot);
			player.getInventory().remove(slot, invItem);
			GroundItemManager.getInstance().addGroundItem(new GroundItem(invItem, player.getLocation(), true), true, player);
		}
	}

}