package org.hyperion.rs2.model;

import org.hyperion.rs2.event.impl.GroundItemEvent;
import org.hyperion.rs2.event.impl.GroundItemEvent.Function;
import org.hyperion.rs2.model.Player;
import org.hyperion.rs2.model.region.Region;

public class GroundItemManager {

	/**
	 * The ground item manager singleton.
	 */
	private static final GroundItemManager instance = new GroundItemManager();

	/**
	 * Gets the static instance for non-static references.
	 * 
	 * @return The static ground item manager instance.
	 */
	public static GroundItemManager getInstance() {
		return instance;
	}
	
	public void addGroundItem(GroundItem item, boolean showToAll, Player player) {
		player.getActionSender().addGroundItem(item);
		Region region = World.getWorld().getRegionManager().getRegionByLocation(item.getLocation());
		region.addGroundItem(item);
		if (showToAll) {
			World.getWorld().submit(new GroundItemEvent(item, Function.SHOW));
		}
		World.getWorld().submit(new GroundItemEvent(item, Function.REMOVE));
	}

	public void addGroundItem(GroundItem item) {
		Region region = World.getWorld().getRegionManager().getRegionByLocation(item.getLocation());
		region.addGroundItem(item);
		for (Player p : region.getPlayers()) {
			p.getActionSender().addGroundItem(item);
		}
		World.getWorld().submit(new GroundItemEvent(item, Function.REMOVE));
	}

	public void removeGroundItem(GroundItem item) {
		Region region = World.getWorld().getRegionManager().getRegionByLocation(item.getLocation());
		if (region.getGroundItems().contains(item)) {
			region.removeGroundItem(item);
		}
		for (Player p : World.getWorld().getPlayers()) {
			p.getActionSender().removeGroundItem(item);
		}
	}

}
