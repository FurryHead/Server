package org.hyperion.rs2.tickable;

import org.hyperion.rs2.event.Event;

/**
 * A RuneScape tickable.
 * @author Thomas Nappo
 */
public abstract class Tickable extends Event {

	/**
	 * Constructs a new 600ms-based event.
	 * @param delay 600ms * this value
	 */
	public Tickable(int delay) {
		super(delay * 600);
	}

}
