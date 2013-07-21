package org.glydar.plugin;

import java.io.File;
import java.util.List;

import org.glydar.exceptions.PluginException;

public interface PluginLoader // TODO Move to API
{

	public void loadPlugin(File file) throws PluginException;

	public List<Plugin> getPlugins();

	public void enablePlugin(Plugin plugin);

	public void disablePlugin(Plugin plugin);

}
