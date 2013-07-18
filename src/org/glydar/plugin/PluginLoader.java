package org.glydar.plugin;

import org.glydar.exceptions.PluginException;

import java.io.File;
import java.util.List;

public interface PluginLoader //TODO Move to API
{

    public void loadPlugin(File file) throws PluginException;

    public List<Plugin> getPlugins();

    public void enablePlugin(Plugin plugin);

    public void disablePlugin(Plugin plugin);

}
