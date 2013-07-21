package org.glydar.plugin;

import org.glydar.Glydar;
import org.glydar.util.LogUtil;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class PluginLogger extends Logger {
	public LogUtil logger;
    private Plugin plugin;

    public PluginLogger(Plugin plugin) {
        super(plugin.getClass().getCanonicalName(), null);
        this.plugin = plugin;
        setParent(Glydar.getServer().getLogger());
        setLevel(Level.ALL);
    }

    public void log(LogRecord log) {
    	logger = new LogUtil();
		logger.output("[ " + plugin.getName() + "] " + log.getMessage());
        super.log(log);
    }

}
