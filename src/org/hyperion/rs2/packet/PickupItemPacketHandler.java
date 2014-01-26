package org.hyperion.rs2.packet;


import org.hyperion.rs2.action.impl.PickupItemAction;
import org.hyperion.rs2.model.entity.player.Player;
import org.hyperion.rs2.net.Packet;

public class PickupItemPacketHandler implements PacketHandler {

	@Override
	public void handle(Player player, Packet packet) {
		int y = packet.getLEShort();
		int id = packet.getShort();
		int x = packet.getLEShort();
		player.getActionQueue().addAction(new PickupItemAction(player, id, x, y));
	}

}