package org.glydar.plugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.glydar.Glydar;
import org.glydar.exceptions.PluginException;

/**
 * Plugin loader for Glydar
 *
 */
public class CubePluginLoader implements PluginLoader {

	private List<Plugin> loadedPlugins = new ArrayList<Plugin>();
	private List<Plugin> pending = new ArrayList<Plugin>();
	
	public void loadPlugins() {
		File pluginDir = new File("plugins");
		if (!pluginDir.exists()) {
			pluginDir.mkdirs();
			return;
		}
		Glydar.getServer().getLogger().info("Loading plugins...");
		for (File file : pluginDir.listFiles()) {
			if (!file.isDirectory() && !file.getName().startsWith(".")) {
				try {
					loadPlugin(file);
				} catch (PluginException ex) {
					Glydar.getServer().getLogger().warning(ex.getMessage());
				}
			}
		}
		if (Glydar.getServer().isRunning()) {
			for (Plugin p : pending) {
				try {
					enablePlugin(p);
				} catch (Exception e) {
					Glydar.getServer().getLogger().warning("Error initializing plugin " + p.name() + "!");
					e.printStackTrace();
				}
			}
		}
		Glydar.getServer().getLogger().info("Loaded " + loadedPlugins.size() + " plugins!");
	}

	public void unloadPlugins() {
		for (Plugin plugin : loadedPlugins) {
			Glydar.getServer().getLogger().info("Disabling " + plugin.name() + " v" + plugin.version());
			disablePlugin(plugin);
		}
	}

	@Override
	public void loadPlugin(File file) throws PluginException {
		if (file == null)
			throw new PluginException("Plugin cannot be null!");

		Plugin plugin = null;
		try {
			plugin = getPluginFile(file);
		} catch (Exception e) {
			Glydar.getServer().getLogger().warning("Failed to load file " + file.getName() + "! " + e.getMessage());
			e.printStackTrace();
			return;
		}

		PluginBase.initialize(Glydar.getServer(), this, new PluginLogger(plugin));
		PluginBase.getLogger().info("Loading " + plugin.name() + " v" + plugin.version());
		pending.add(plugin);
	}

	@SuppressWarnings({ "unchecked", "resource" })
	public Plugin getPluginFile(File file) throws PluginException, NoSuchMethodException, SecurityException, IOException,
		InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (!file.getName().endsWith(".jar"))
			throw new PluginException("File must be a jar file!");
		JarFile jFile;
		URL[] urls = { file.toURI().toURL() };
		ClassLoader cl;
		jFile = new JarFile(file.getPath());
		cl = new URLClassLoader(urls, getClass().getClassLoader()); // TODO
																	// Figure
																	// out why
																	// this says
																	// potential
																	// resource
																	// leak...
		Enumeration<JarEntry> e = jFile.entries();

		Class<? extends Plugin> clazz = null;
		while (e.hasMoreElements()) {
			JarEntry entry = e.nextElement();
			if (entry.isDirectory() || !entry.getName().endsWith(".class"))
				continue;
			String className = entry.getName().substring(0, entry.getName().length() - 6);
			className = className.replace('/', '.');
			try {
				Class<?> c = cl.loadClass(className);
				if (c.getSuperclass() == Plugin.class) {
					clazz = (Class<? extends Plugin>) c;
					break;
				}
			} catch (ClassNotFoundException e1) {
				Glydar.getServer().getLogger().warning("Error loading plugin class from " + className);
			}
		}
		jFile.close();
		if (clazz == null)
			throw new PluginException("PLugin " + file.getName().replace(".jar", "") + " does not contain a main class!");

		Constructor<? extends Plugin> constructor = clazz.asSubclass(Plugin.class).getConstructor();
		return constructor.newInstance();
	}

	@Override
	public List<Plugin> getPlugins() {
		return loadedPlugins;
	}

	@Override
	public void enablePlugin(Plugin plugin) {
		PluginBase.setEnabled(true);
		loadedPlugins.add(plugin);
	}

	@Override
	public void disablePlugin(Plugin plugin) {
		PluginBase.setEnabled(false);
		loadedPlugins.remove(plugin);
	}

}
