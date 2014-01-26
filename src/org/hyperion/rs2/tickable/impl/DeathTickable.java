package org.hyperion.rs2.tickable.impl;

import org.hyperion.rs2.event.Event;
import org.hyperion.rs2.model.Animation;
import org.hyperion.rs2.model.Entity;
import org.hyperion.rs2.model.NPC;
import org.hyperion.rs2.model.Player;
import org.hyperion.rs2.model.Skills;
import org.hyperion.rs2.model.World;
import org.hyperion.rs2.tickable.Tickable;

/**
 * The death event handles player and npc deaths. Drops loot, does animation, teleportation, etc.
 * @author Graham
 *
 */
@SuppressWarnings("unused")
public class DeathTickable extends Tickable {
	
	private Entity entity;

	/**
	 * Creates the death event for the specified entity.
	 * @param entity The player or npc whose death has just happened.
	 */
	public DeathTickable(Entity entity) {
		super(6);
		this.entity = entity;
	}

	@Override
	public void execute() {
		if(entity instanceof Player) {
			Player p = (Player) entity;
			p.getSkills().setLevel(Skills.HITPOINTS, p.getSkills().getLevelForExperience(Skills.HITPOINTS));
			entity.setDead(false);
			entity.setTeleportTarget(Entity.DEFAULT_LOCATION);
			p.getActionSender().sendMessage("Oh dear, you are dead!");
			p.playAnimation(Animation.create(-1));
			this.stop();
		}
	}

}