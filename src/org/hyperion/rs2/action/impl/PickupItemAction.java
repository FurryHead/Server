package org.hyperion.rs2.action.impl;

import org.hyperion.rs2.model.GroundItem;
import org.hyperion.rs2.model.GroundItemManager;
import org.hyperion.rs2.model.Location;
import org.hyperion.rs2.model.World;
import org.hyperion.rs2.model.Player;
import org.hyperion.rs2.model.region.Region;

public class PickupItemAction extends PendingAction {

	/**
	 * The x location.
	 */
	private int x;
	
	/**
	 * The y location.
	 */
	private int y;
	
	/**
	 * The item id.
	 */
	private int id;
	
	public PickupItemAction(Player player, int id, int x, int y) {
		super(player, 300);
		this.id = id;
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean canRun() {
		return player.getLocation().getDistanceTo(Location.create(x, y, player.getLocation().getZ())) <= 1;
	}

	@Override
	public void preformAction() {
		Location location = Location.create(x, y, player.getLocation().getZ());
		Region region = World.getWorld().getRegionManager().getRegionByLocation(location);
		GroundItem item = null;
		for(GroundItem gI : region.getGroundItems()) {
			if (gI.getLocation().equals(location)) {
				if (gI.getItem().getId() == id) {
					item = gI;
					break;
				}
			}
		}
		if (item == null) {
			return;
		}
		GroundItemManager.getInstance().removeGroundItem(item);
		if (player.getInventory().hasRoomFor(item.getItem())) {
			player.getInventory().add(item.getItem());
		}
		stop();
	}

	@Override
	public QueuePolicy getQueuePolicy() {
		return QueuePolicy.NEVER;
	}
	
	@Override
	public WalkablePolicy getWalkablePolicy() {
		return WalkablePolicy.NON_WALKABLE;
	}

}