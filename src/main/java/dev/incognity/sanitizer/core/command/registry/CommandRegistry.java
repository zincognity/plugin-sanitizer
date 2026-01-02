package dev.incognity.sanitizer.core.command.registry;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import dev.incognity.sanitizer.core.command.model.Command;
import dev.incognity.sanitizer.core.logger.model.Logger;
import lombok.Getter;

/**
 * Registry for managing commands within the Sanitizer plugin.
 * 
 * @author Incognity / 01/02/2026
 */
public class CommandRegistry {

  private final JavaPlugin plugin;
  @Getter
  private final List<Command> commands = new ArrayList<>();
  private CommandMap commandMap;

  public CommandRegistry(@Nonnull JavaPlugin plugin) {
    this.plugin = plugin;

    initializeCommandMap();
  }

  /**
   * Initialize the CommandMap
   */
  private void initializeCommandMap() {
    try {
      Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

      bukkitCommandMap.setAccessible(true);

      this.commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
    } catch (Exception e) {
      Logger.error("Failed to initialize CommandMap" + e, "CommandRegistry");
    }
  }

  /**
   * Get the total number of registered commands
   * 
   * @return the count of registered commands
   */
  public int getCommandCount() {
    return commands.size();
  }

  /**
   * Add a command to the registry
   * 
   * @param command the command to add
   * @return the CommandRegistry instance
   */
  public CommandRegistry add(@Nonnull Command command) {
    if (commandMap == null) {
      Logger.error("CommandMap was not initialized properly", "CommandRegistry");
      return null;
    }

    commandMap.register(plugin.getName(), command);
    commands.add(command);

    return this;
  }

  /**
   * Add multiple commands to the registry
   * 
   * @param commands the commands to add
   * @return the CommandRegistry instance
   */
  public CommandRegistry addAll(@Nonnull Command... commands) {
    if (commands.length == 0) {
      return this;
    }

    for (Command command : commands) {
      add(command);
    }

    return this;
  }
}
