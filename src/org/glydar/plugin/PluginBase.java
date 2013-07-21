package org.glydar.plugin;

import java.util.Set;

import org.glydar.network.CWServer;
import org.reflections.Reflections;

public abstract class PluginBase {
	private static PluginLoader loadere;
    private static PluginLogger loggere;
    private static CWServer servere; //TODO Change to API server
    private static boolean enabled = false;

    /**
     * Method called when the plugin is loaded
     */
    public static void onLoad() {
    }
    
    public void find() {
    	  Reflections reflections = new Reflections("my.project.prefix");

    	     Set<Class<? extends PluginBase>> subTypes = 
    	               reflections.getSubTypesOf(PluginBase.class);

    	     Set<Class<?>> annotated = 
    	               reflections.getTypesAnnotatedWith(Plugin.class);
    }

    /**
     * Method called when the plugin is unloaded
     */
    public static void onUnload() {
    }

    /**
     * Get the logger for this plugin.
     */
    public static PluginLogger getLogger() {
        return loggere;
    }

    /**
     * Gets the Plugin Loader that loaded this plugin
     */
    public PluginLoader getLoader() {
        return loadere;
    }

    /**
     * Gets the server that loaded this plugin
     */
    public CWServer getServer() {
        return servere;
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
     * @param b True to enable, false to disable.
     */
    public static void setEnabled(boolean b) {
        if (enabled && b) return;
        else if (!enabled && !b) return;
        else if (b) {
            enabled = b;
            onLoad();
        } else {
            enabled = b;
            onUnload();
        }
    }

    protected final static void initialize(CWServer server, PluginLoader loader, PluginLogger logger) {
        servere = server;
        loggere = logger;
        loadere = loader;
    }

}
