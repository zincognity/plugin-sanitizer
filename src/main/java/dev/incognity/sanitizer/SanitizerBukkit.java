package dev.incognity.sanitizer;

import org.bukkit.plugin.java.JavaPlugin;

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
    plugin = this;
  }

  @Override
  public void onDisable() {
    plugin = null;
  }
}
