package dev.incognity.sanitizer;

import org.bukkit.plugin.java.JavaPlugin;

import dev.incognity.sanitizer.core.logger.model.Logger;

/**
 * Main class of the Sanitizer plugin.
 * 
 * Handles initialization and core functionality.
 * 
 * @author Incognity / 01/01/2026
 */
public class SanitizerBukkit extends JavaPlugin {

  // Instance of the plugin
  public static SanitizerBukkit plugin;

  @Override
  public void onEnable() {
    // Set the static plugin instance
    plugin = this;

    // Customize the logger with the plugin instance and a color code
    Logger.customize(this, "&a");
  }

  @Override
  public void onDisable() {
    plugin = null;
  }
}
