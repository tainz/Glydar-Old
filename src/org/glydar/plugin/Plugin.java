package org.glydar.plugin;

import org.glydar.network.CWServer;

public abstract class Plugin {

	private PluginLoader loader;
	private PluginLogger logger;
	private CWServer server; // TODO Change to API server
	private boolean enabled = false;

	/**
	 * Method called when the plugin is loaded
	 */
	public void onLoad() {
	}

	/**
	 * Method called when the plugin is unloaded
	 */
	public void onUnload() {
	}

	/**
	 * This method gets the version of the plugin
	 * 
	 * @return The version in string form
	 */
	public abstract String getVersion();

	/**
	 * This method gets the name of the plugin
	 * 
	 * @return The name in string form
	 */
	public abstract String getName();

	/**
	 * Get the logger for this plugin.
	 */
	public PluginLogger getLogger() {
		return logger;
	}

	/**
	 * Gets the Plugin Loader that loaded this plugin
	 */
	public PluginLoader getLoader() {
		return loader;
	}

	/**
	 * Gets the server that loaded this plugin
	 */
	public CWServer getServer() {
		return server;
	}

	/**
	 * Whether or not the plugin is currently enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Enables or disables the plugin
	 * 
	 * @param b
	 *            True to enable, false to disable.
	 */
	public void setEnabled(boolean b) {
		if (enabled && b)
			return;
		else if (!enabled && !b)
			return;
		else if (b) {
			enabled = b;
			onLoad();
		} else {
			enabled = b;
			onUnload();
		}
	}

	protected final void initialize(CWServer server, PluginLoader loader,
			PluginLogger logger) {
		this.server = server;
		this.logger = logger;
		this.loader = loader;
	}

}
