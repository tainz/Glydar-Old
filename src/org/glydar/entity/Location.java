/**
 * Location.java
 * 
 * Version 0.1
 * 
 * Copyright 2013 Jonas Seibert
 */

package org.glydar.entity;

/**
 * Represents a 3-dimensional location in a world
 */
public class Location {
	
	private int x;
	private int y;
	private int z;
	
	private float yaw;
	private float pitch;
	private float roll;
	
	/**
	 * Constructs a new Location based on the given parameters
	 * @param x The x coordinate of the new Location
	 * @param y The y coordinate of the new Location
	 * @param z The z coordinate of the new Location
	 * @param yaw The yaw rotation of the new Location
	 * @param pitch The pitch rotation of the new Location
	 * @param roll The roll rotation of the new Location
	 */
	public Location(int x, int y, int z, float pitch, float roll, float yaw){
		this.setX(x);
		this.setY(y);
		this.setZ(z);
		
		this.setYaw(yaw);
		this.setPitch(pitch);
		this.setRoll(roll);
	}
	
	/**
	 * Constructs a new Location based on the given parameters
	 * @param x The x coordinate of the new Location
	 * @param y The y coordinate of the new Location
	 * @param z The z coordinate of the new Location
	 */
	public Location(int x, int y, int z){
		this(x, y, z, 0, 0, 0);
	}
	
	/**
	 * Gets the x coordinate of this Location
	 * @return The x coordinate of this Location
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the x coordinate of this Location
	 * @param The new x coordinate for this Location
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Gets the y coordinate of this Location
	 * @return The y coordinate of this Location
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the y coordinate of this Location
	 * @param The new y coordinate for this Location
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Gets the z coordinate of this Location
	 * @return The z coordinate of this Location
	 */
	public int getZ() {
		return z;
	}

	/**
	 * Sets the z coordinate of this Location
	 * @param The new z coordinate for this Location
	 */
	public void setZ(int z) {
		this.z = z;
	}
	
	/**
	 * Gets the yaw of this Location
	 * @return The yaw of this Location
	 */
	public float getYaw() {
		return yaw;
	}
	
	/**
	 * Sets the yaw of this Location
	 * @param The new yaw for this Location
	 */
	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

	/**
	 * Gets the pitch of this Location
	 * @return The pitch coordinate of this Location
	 */
	public float getPitch() {
		return pitch;
	}

	/**
	 * Sets the pitch of this Location
	 * @param The new pitch for this Location
	 */
	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	/**
	 * Gets the roll of this Location
	 * @return roll of this Location
	 */
	public float getRoll() {
		return roll;
	}

	/**
	 * Sets the roll of this Location
	 * @param The new roll for this Location
	 */
	public void setRoll(float roll) {
		this.roll = roll;
	}

}
