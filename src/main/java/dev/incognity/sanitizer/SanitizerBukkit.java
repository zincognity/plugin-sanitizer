package dev.incognity.sanitizer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import dev.incognity.sanitizer.core.command.model.CommandFeedback;
import dev.incognity.sanitizer.core.command.registry.CommandRegistry;
import dev.incognity.sanitizer.core.listener.registry.ListenerRegistry;
import dev.incognity.sanitizer.core.logger.model.Logger;
import dev.incognity.sanitizer.core.scheduler.model.Runnables;
import dev.incognity.sanitizer.detector.listener.DetectorListener;
import dev.incognity.sanitizer.engine.registry.EngineRegistry;
import lombok.Getter;

/**
 * Main class of the Sanitizer plugin.
 * 
 * Handles initialization and core functionality.
 * 
 * @author Incognity / 01/01/2026
 */
@Getter
public class SanitizerBukkit extends JavaPlugin {

  // Instance of the plugin
  public static SanitizerBukkit plugin;

  // Registries
  private ListenerRegistry listenerRegistry;
  private CommandRegistry commandRegistry;
  private EngineRegistry engineRegistry;

  @Override
  public void onEnable() {
    // Set the static plugin instance
    plugin = this;

    // Customize the logger with the plugin instance and a color code
    Logger.initialize(this, "&b");
    // Initialize the scheduler with the plugin instance
    Runnables.initialize(this);
    // Initialize command feedback with the plugin instance and a color code
    CommandFeedback.initialize(this, "&b");

    // Initialize registries
    initializeRegistries();
  }

  @Override
  public void onDisable() {
    plugin = null;

    disable();
  }

  /**
   * Initialize the registries
   */
  private void initializeRegistries() {
    this.listenerRegistry = new ListenerRegistry(this);

    listenerRegistry.add(new DetectorListener());

    this.commandRegistry = new CommandRegistry(this);
    this.engineRegistry = new EngineRegistry();
  }

  private void disable() {
    Bukkit.getScheduler().cancelTasks(this);
    Bukkit.getPluginManager().disablePlugin(this);
  }
}
