package org.hyperion.rs2.model.region;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

import org.hyperion.rs2.model.GameObject;
import org.hyperion.rs2.model.NPC;
import org.hyperion.rs2.model.Player;
import org.hyperion.rs2.model.World;
import org.hyperion.rs2.model.GroundItem;

/**
 * Represents a single region.
 * @author Graham Edgecombe
 *
 */
public class Region {

	/**
	 * The region coordinates.
	 */
	private RegionCoordinates coordinate;
	
	/**
	 * A list of players in this region.
	 */
	private List<Player> players = new LinkedList<Player>();
	
	/**
	 * Gets the list of ground items.
	 * 
	 * @return The list of ground items.
	 */
	public Collection<GroundItem> getGroundItems() {
		return groundItems;
	}
	
  /**
	 * A list of ground items in this region.
	 */
	private final ArrayList<GroundItem> groundItems = new ArrayList<GroundItem>();
	
	/**
	 * A list of NPCs in this region.
	 */
	private List<NPC> npcs = new LinkedList<NPC>();
	
	/**
	 * A list of objects in this region.
	 */
	private List<GameObject> objects = new LinkedList<GameObject>();
	
	/**
	 * Adds a ground item to this region.
	 * 
	 * @param item
	 *            The item to add.
	 */
	public void addGroundItem(GroundItem item) {
		synchronized (this) {
			groundItems.add(item);
		}
	}

	/**
	 * Removes a ground item from this region.
	 * 
	 * @param item
	 *            The item to remove.
	 */
	public void removeGroundItem(GroundItem item) {
		synchronized (this) {
			groundItems.remove(item);
		}
	}
	
	
	
	/**
	 * Creates a region.
	 * @param coordinate The coordinate.
	 */
	public Region(RegionCoordinates coordinate) {
		this.coordinate = coordinate;
	}
	
	/**
	 * Gets the region coordinates.
	 * @return The region coordinates.
	 */
	public RegionCoordinates getCoordinates() {
		return coordinate;
	}

	/**
	 * Gets the list of players.
	 * @return The list of players.
	 */
	public Collection<Player> getPlayers() {
		synchronized(this) {
			return Collections.unmodifiableCollection(new LinkedList<Player>(players));
		}
	}
	
	/**
	 * Gets the list of NPCs.
	 * @return The list of NPCs.
	 */
	public Collection<NPC> getNpcs() {
		synchronized(this) {
			return Collections.unmodifiableCollection(new LinkedList<NPC>(npcs));
		}
	}
	
	/**
	 * Gets the list of objects.
	 * @return The list of objects.
	 */
	public Collection<GameObject> getGameObjects() {
		return objects;
	}

  /**
	 * Adds a new player.
	 * 
	 * @param player
	 *            The player to add.
	 */
	public void addPlayer(final Player player) {
		synchronized (this) {
			if (players.contains(player)) {
				return;
			}
			players.add(player);

			new Thread() { //New thread so that the delay doesn't screw anything up

				public void run() {
					try {
						Thread.sleep(500); //Delay on login this runs before ActionSender is initialized
						//If anyone can tell me a better way to do this I would be happy to
					} catch (InterruptedException e) {
					}
					for (GroundItem item : player.getRegion().getGroundItems()) {
						if (!item.isPrivate()) {
							player.getActionSender().addGroundItem(item);
						}
					}
				}

			}.start();
		}
	}

	/**
	 * Removes an old player.
	 * @param player The player to remove.
	 */
	public void removePlayer(Player player) {
		synchronized(this) {
			players.remove(player);
		}
	}

	/**
	 * Adds a new NPC.
	 * @param npc The NPC to add.
	 */
	public void addNpc(NPC npc) {
		synchronized(this) {
			npcs.add(npc);
		}
	}

	/**
	 * Removes an old NPC.
	 * @param npc The NPC to remove.
	 */
	public void removeNpc(NPC npc) {
		synchronized(this) {
			npcs.remove(npc);
		}
	}
	
	/**
	 * Gets the regions surrounding a location.
	 * @return The regions surrounding the location.
	 */
	public Region[] getSurroundingRegions() {
		Region[] surrounding = new Region[9];
		surrounding[0] = this;
		surrounding[1] = World.getWorld().getRegionManager().getRegion(this.getCoordinates().getX() - 1, this.getCoordinates().getY() - 1);
		surrounding[2] = World.getWorld().getRegionManager().getRegion(this.getCoordinates().getX() + 1, this.getCoordinates().getY() + 1);
		surrounding[3] = World.getWorld().getRegionManager().getRegion(this.getCoordinates().getX() - 1, this.getCoordinates().getY());
		surrounding[4] = World.getWorld().getRegionManager().getRegion(this.getCoordinates().getX(), this.getCoordinates().getY() - 1);
		surrounding[5] = World.getWorld().getRegionManager().getRegion(this.getCoordinates().getX() + 1, this.getCoordinates().getY());
		surrounding[6] = World.getWorld().getRegionManager().getRegion(this.getCoordinates().getX(), this.getCoordinates().getY() + 1);
		surrounding[7] = World.getWorld().getRegionManager().getRegion(this.getCoordinates().getX() - 1, this.getCoordinates().getY() + 1);
		surrounding[8] = World.getWorld().getRegionManager().getRegion(this.getCoordinates().getX() + 1, this.getCoordinates().getY() - 1);
		return surrounding;
	}

}
