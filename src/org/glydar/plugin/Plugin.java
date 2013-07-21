package org.glydar.plugin;

/**
 * Add this to your main plugin class
 * @author Glydar Team
 *
 */
public @interface Plugin {
	public String name(); 	
	public String id();
	public String version();
}
