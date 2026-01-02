package dev.incognity.sanitizer;

import org.bukkit.plugin.java.JavaPlugin;

import dev.incognity.sanitizer.core.command.model.CommandFeedback;
import dev.incognity.sanitizer.core.logger.model.Logger;
import dev.incognity.sanitizer.core.scheduler.model.Runnable;

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
    Logger.initialize(this, "&b");
    // Initialize the scheduler with the plugin instance
    Runnable.initialize(this);
    // Initialize command feedback with the plugin instance and a color code
    CommandFeedback.initialize(this, "&b");
  }

  @Override
  public void onDisable() {
    plugin = null;
  }
}
