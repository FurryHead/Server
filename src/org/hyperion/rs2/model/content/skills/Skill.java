package org.hyperion.rs2.model.content.skills;

/**
 * A certain skill.
 * @author Thomas Nappo
 */
public abstract class Skill {
	
	/**
	 * The identification number of the skill.
	 */
	protected int id;
	
	/**
	 * Constructs a new skill.
	 * @param The identification number of the skill.
	 */
	public Skill(int id) {
		this.id = id;
	}

}
