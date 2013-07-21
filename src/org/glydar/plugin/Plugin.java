package org.glydar.plugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLClassLoader;

import org.glydar.exceptions.PluginException;
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

	public String getAuthor() {
		return null;
	}

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

	/**
	 * Gets this plugin's folder (plugins/PluginName) If the folder does not
	 * exist, it will be created.
	 * 
	 * @return The plugin's folder.
	 */
	public File getFolder() {
		File file = new File("plugins/" + getName());
		if (!file.exists()) {
			file.mkdirs();
		}
		return file;
	}

	public InputStream getResource(String name) {
		URLClassLoader cl = loader.getClassLoader(this);
		return cl.getResourceAsStream(name);
	}
	
	public void saveResource(String name) {
		File file = new File(getFolder(), name);
		saveResource(name, file);
	}
	
	public void saveResource(String name, File file) {
		InputStream in = null;
		OutputStream out = null;
		try {
			file.createNewFile();
			in = getResource(name);
			out = new FileOutputStream(file);
			if (in == null)
				throw new PluginException("Could not find resource " + file.getName());
			byte[] buffer = new byte[1024];
			int len = in.read(buffer);
			while (len != -1) {
				out.write(buffer, 0, len);
				len = in.read(buffer);
			}
		} catch (Exception e) {
			logger.warning("Error while saving file " + file.getName() + ": " + e.getMessage());
			e.printStackTrace();
			return;
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
			}
		}
	}

	protected final void initialize(CWServer server, PluginLoader loader, PluginLogger logger) {
		this.server = server;
		this.logger = logger;
		this.loader = loader;
	}

}
