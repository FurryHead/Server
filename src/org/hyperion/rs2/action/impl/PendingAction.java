package org.hyperion.rs2.action.impl;

import org.hyperion.rs2.action.Action;
import org.hyperion.rs2.model.Player;

public abstract class PendingAction extends Action {

	/**
	 * Creates a pending action.
	 * @param player The player performing this action.
	 * @param delay The delay between runs.
	 */
	public PendingAction(Player player, long delay) {
		super(player, delay);
	}
	
	/**
	 * Performs the action
	 */
	public abstract void preformAction();
	
	/**
	 * A flag representing if the player meets the requirements.
	 * @return
	 */
	public abstract boolean canRun();
	
	@Override
	public void execute() {
		if (canRun()) {
			preformAction();
		}
	}
	
}
