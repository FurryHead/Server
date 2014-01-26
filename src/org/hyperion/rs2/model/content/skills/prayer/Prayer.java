package org.hyperion.rs2.model.content.skills.prayer;

import org.hyperion.rs2.model.Player;
import org.hyperion.rs2.model.Skills;
import org.hyperion.rs2.model.content.skills.Skill;

/**
 * The prayer skill.
 * @author Thomas Nappo
 */
public class Prayer extends Skill {

	/**
	 * Constructs a new prayer skill.
	 * @param the player who will be using the skill.
	 */
	public Prayer(Player player) {
		super(Skills.PRAYER); //Superconstruct with the prayer skill id.
	}

}
