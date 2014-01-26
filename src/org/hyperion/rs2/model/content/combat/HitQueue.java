package org.hyperion.rs2.model.content.combat;

import java.util.LinkedList;
import java.util.Queue;

import org.hyperion.rs2.model.Player;
import org.hyperion.rs2.model.Damage.Hit;
import org.hyperion.rs2.model.Damage.HitType;

/**
 * Queue of hits yet to be processed for a certain player.
 * @author Thomas Nappo
 */
public class HitQueue {
	
	//Old but w/e
	
	/**
	 * Constructs a new hit queue.
	 * @param player The player of the hit queue.
	 */
	public HitQueue(Player player) {
		this.player = player;
	}
	
	/**
	 * Stores the hits yet to be queued.
	 */
	private Queue<Hit> hits = new LinkedList<Hit>();
	
	/**
	 * Gets the hits yet to be queued.
	 * @return hits
	 */
	public Queue<Hit> getHits() {
		return hits;
	}
	
	/**
	 * The player of the hit queue.
	 */
	private Player player;
	
	/**
	 * Gets the player of the hit queue.
	 * @return player
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Processes all the hits of the queue.
	 */
	public boolean processHits() {
		Hit next = new Hit(0, HitType.NO_DAMAGE);
		int i = 0;
		while(next != null && i < 2) {
			next = hits.poll();
			if(next == null) {
				return false;
			}
			if(next.getDamage() < 0) {
				continue;
			}
			appendHit(next);
			i++;
		}
		return true;
	}
	
	/**
	 * Appends a hit of damage to the player.
	 * @param hit The hit.
	 */
	public void appendHit(Hit hit) {
		while((player.getHitpoints() - hit.getDamage()) < 0) {
			hit.setDamage(hit.getDamage() - 1);
		}
		if(hit.getDamage() < 1) {
			hit.setType(HitType.NO_DAMAGE);
		}
		if(hit.getDamage() < 0) {
			return;
		}
		player.inflictDamage(hit);
	}

}