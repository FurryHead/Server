package org.hyperion.rs2.event.impl;

import org.hyperion.rs2.event.Event;
import org.hyperion.rs2.model.GroundItem;
import org.hyperion.rs2.model.GroundItemManager;
import org.hyperion.rs2.model.World;
import org.hyperion.rs2.model.region.Region;

/**
 * Handles functions for ground items.
 * 
 * @author user Shoes
 * 
 */
public class GroundItemEvent extends Event {

	/**
	 * The ground item instance.
	 */
	private GroundItem item;

	/**
	 * The function for the event to perform.
	 * 
	 * @author user Shoes
	 * 
	 */
	public enum Function {
		REMOVE(150000), SHOW(60000);

		private Function(int delay) {
			this.delay = delay;
		}

		public int getDelay() {
			return delay;
		}

		private int delay;
	}

	/**
	 * The function the event will perform.
	 */
	private Function function;

	public GroundItemEvent(GroundItem item, Function function) {
		super(function.getDelay());
		this.item = item;
		this.function = function;
	}

	@Override
	public void execute() {
		if (item != null) {
			switch (function) {

			case SHOW:
				Region region = World.getWorld().getRegionManager()
						.getRegionByLocation(item.getLocation());
				if (region.getGroundItems().contains(item) && item.isPrivate()) {
					GroundItemManager.getInstance().removeGroundItem(item);
					item = new GroundItem(item.getItem(),
							item.getLocation(), false);
					GroundItemManager.getInstance().addGroundItem(item);
				}
				break;

			case REMOVE:
				GroundItemManager.getInstance().removeGroundItem(item);
				break;

			}
		}
		stop();
	}

}