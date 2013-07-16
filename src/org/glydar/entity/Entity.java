package org.glydar.entity;

import java.util.Vector;

public class Entity {
	
	public int id;
	
	private Location loc; //x, y, z, yaw, pitch, roll
	
	public Vector<Float> vel; //Velocity
	
	public Vector<Float> acc; //Acceleration
	
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
