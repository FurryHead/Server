package org.hyperion.rs2.util;

import java.util.Random;

import org.hyperion.rs2.model.Location;

/**
 * Holds miscelleanous methods.
 * @author Canownueasy
 *
 */
public class Misc {
	
	public static boolean intToBoolean(int i) {
		return i > 0 ? true : false;
	}
	public static int booleanToInt(boolean b) {
		return b ? 1 : 0;
	}
	
    /**
     * @param range The range to randomize from.
     * @return Returns a random number from 0 to range+.
     */
    public static int random(int range) {
        return (int)(Math.random() * (range + 1));
    }
    
    /**
     * Returns a random integer with min as the inclusive
     * lower bound and max as the exclusive upper bound.
     *
     * @param min The inclusive lower bound.
     * @param max The exclusive upper bound.
     * @return Random integer min <= n < max.
     */
    public static int random(int min, int max) {
    	Random random = new Random();
        int n = Math.abs(max - min);
        return Math.min(min, max) + (n == 0 ? 0 : random.nextInt(n));
    }
    
	/**
	 * @return Returns the distance between two positions.
	 */
	public static int getDistance(int coordX1, int coordY1, int coordX2,
			int coordY2) {
		int deltaX = coordX2 - coordX1;
		int deltaY = coordY2 - coordY1;
		return ((int) Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)));
	}
	
	public static int getDistance(Location loc1, Location loc2) {
		int deltaX = loc2.getX() - loc1.getX();
		int deltaY = loc2.getY() - loc1.getY();
		return ((int) Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)));
	}
	public static double random(double range) {
        return (Math.random() * (range + 1));
	}

}