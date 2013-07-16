package org.sharpcube.jKube.entity;

public class Entity {
	
	public int id;
	
	public int xPos; //Uint
	public int yPos; //Uint
	public int zPos; //Uint
	
	public float xRot; //Pitch
	public float zRot; //Roll
	public float yRot; //Yaw
	
	public float xVel; //Velocity
	public float yVel; //""
	public float zVel; //""
	
	public float xAcc; //Acceleration
	public float yAcc; //""
	public float zAcc; //""
	
	public float xSkillVel;
	public float ySkillVel;
	public float zSkillVel;
	public float aimRot;
	
	public int pFlags; //Physics Flags (Uint). TODO Make enum type for this.
	
	public int debug;
	
	public int unknown; //Location 100 or 0x64, Int<4>
	
	public int currentAction; //TODO Enum
	
	public int msSinceCurrentAction; //Milliseconds since current action started
	
	public int hitCounter; //Amount of hits in chain
	
	public int msSinceSuccessHit; //Milliseconds since last successful hit
	
	public Appearance app; //Appearance
	
	ActionFlags af; //Action flags.
}
