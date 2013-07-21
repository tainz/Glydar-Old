package org.glydar.plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.glydar.Glydar;
import org.glydar.exceptions.PluginException;

public class CubePluginLoader implements PluginLoader {

	private List<Plugin> loadedPlugins = new ArrayList<Plugin>();
	private List<Plugin> pending = new ArrayList<Plugin>();
	private Map<String, URL> jarMap = new HashMap<String, URL>();

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
					Glydar.getServer().getLogger().warning("Error initializing plugin " + p.getName() + "!");
					e.printStackTrace();
				}
			}
		}
		Glydar.getServer().getLogger().info("Loaded " + loadedPlugins.size() + " plugins!");
	}

	public void unloadPlugins() {
		for (Plugin plugin : loadedPlugins) {
			Glydar.getServer().getLogger().info("Disabling " + plugin.getName() + " v" + plugin.getVersion());
			disablePlugin(plugin);
		}
	}

	@Override
	public void loadPlugin(File file) throws PluginException {
		if (file == null)
			throw new PluginException("Plugin cannot be null!");

		Plugin plugin = null;
		try {
			plugin = getPlugin(file);
		} catch (Exception e) {
			Glydar.getServer().getLogger().warning("Failed to load file " + file.getName() + "! " + e.getMessage());
			e.printStackTrace();
			return;
		}

		plugin.initialize(Glydar.getServer(), this, new PluginLogger(plugin));
		StringBuffer sb = new StringBuffer();
		try {
			jarMap.put(plugin.getName(), file.toURI().toURL());
		} catch (MalformedURLException e) {
			Glydar.getServer().getLogger().warning("Failed to load plugin " + plugin.getName() + "! " + e.getMessage());
			e.printStackTrace();
			return;
		}
		sb.append("Loading " + plugin.getName() + " v" + plugin.getVersion());
		if (plugin.getAuthor() != null) {
			sb.append(" by " + plugin.getAuthor());
		}
		Glydar.getServer().getLogger().info(sb.toString());
		pending.add(plugin);
	}

	@SuppressWarnings({ "unchecked" })
	private Plugin getPlugin(File file) throws PluginException, NoSuchMethodException, SecurityException, IOException,
		InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (!file.getName().endsWith(".jar"))
			throw new PluginException("File must be a jar file!");
		JarFile jFile = new JarFile(file.getPath());
		URL[] urls = { file.toURI().toURL() };
		URLClassLoader cl = new URLClassLoader(urls, getClass().getClassLoader());
		Enumeration<JarEntry> e = jFile.entries();
		Class<? extends Plugin> clazz = null;
		try {
			InputStream is = cl.getResourceAsStream("plugin.properties");
			Properties props = new Properties();
			props.load(is);
			String main = props.getProperty("main");
			try {
				Class<?> c = cl.loadClass(main);
				if (c.getSuperclass() == Plugin.class) {
					clazz = (Class<? extends Plugin>) c;
					while (e.hasMoreElements()) {
						JarEntry entry = e.nextElement();
						if (entry.isDirectory() || !entry.getName().endsWith(".class"))
							continue;
						String className = entry.getName().substring(0, entry.getName().length() - 6);
						className = className.replace('/', '.');
						try {
							cl.loadClass(className);
						} catch (Exception ex) {
							Glydar.getServer().getLogger().warning("Error loading plugin class from " + className);
						}
					}
				} else {
					throw new PluginException("File plugin.properties main class for " + file.getName() + " is invalid!");
				}
			} catch (Exception ex) {
				Glydar.getServer().getLogger().warning(ex.getMessage());
			}
		} catch (Exception ex) {
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
					}
				} catch (ClassNotFoundException e1) {
					Glydar.getServer().getLogger().warning("Error loading plugin class from " + className);
				}
			}
		}
		jFile.close();
		cl.close();
		if (clazz == null)
			throw new PluginException("Plugin " + file.getName().replace(".jar", "") + " does not contain a main class!");
		Constructor<? extends Plugin> constructor = clazz.asSubclass(Plugin.class).getConstructor();
		Plugin plugin = constructor.newInstance();
		return plugin;
	}

	@Override
	public List<Plugin> getPlugins() {
		return loadedPlugins;
	}

	@Override
	public void enablePlugin(Plugin plugin) {
		try {
			plugin.setEnabled(true);
		} catch (Exception e) {

		}
		loadedPlugins.add(plugin);
	}

	@Override
	public void disablePlugin(Plugin plugin) {
		try {
			plugin.setEnabled(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		loadedPlugins.remove(plugin);
	}

	public URLClassLoader getClassLoader(Plugin plugin) {
		URLClassLoader cl = new URLClassLoader(new URL[] { jarMap.get(plugin.getName()) });
		return cl;
	}

}
