package dev.incognity.sanitizer.core.logger.model;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import dev.incognity.sanitizer.core.logger.enums.LoggerLevel;
import dev.incognity.sanitizer.core.logger.enums.LoggerStyle;
import lombok.experimental.UtilityClass;

/**
 * Logger utility for sending colored log messages to the console.
 * 
 * @author Incognity / 01/01/2026
 */
@UtilityClass
public final class Logger {

  private static LoggerLevel lastLoggerLevel;
  private static String name = "Logger";
  private static String color = "&b";
  private static String colorKey = "&b";
  private static boolean isDebugEnabled = false;

  /**
   * Initialize the logger with a plugin instance and color
   * 
   * @param pluginInstance the plugin instance
   * @param colorInstance  the color code to use
   */
  public void initialize(@Nonnull JavaPlugin pluginInstance, @Nonnull String colorInstance) {
    initialize(pluginInstance, colorInstance, null);
  }

  /**
   * Initialize the logger with a plugin instance, color, and optional color key
   * 
   * @param pluginInstance   the plugin instance
   * @param colorInstance    the color code to use
   * @param colorKeyInstance the color code for keys (optional)
   */
  public void initialize(@Nonnull JavaPlugin pluginInstance, @Nonnull String colorInstance,
      @Nullable String colorKeyInstance) {
    name = pluginInstance.getName();
    color = colorInstance;

    if (colorKeyInstance != null && !colorKeyInstance.trim().isEmpty()) {
      colorKey = colorInstance;
    }

    FileConfiguration config = pluginInstance.getConfig();
    Object isDebugSet = config.get("logger.debug-mode");

    if (isDebugSet == null) {
      config.set("logger.debug-mode", false);
      pluginInstance.saveConfig();
    }

    isDebugEnabled = config.getBoolean("logger.debug-mode", false);
  }

  /**
   * Send an info log message to the console
   * 
   * @param message the main log message
   */
  public void debug(@Nonnull String message) {
    if (isDebugEnabled) {
      debug(message, null);
    }
  }

  /**
   * Send an info log message to the console
   * 
   * @param message the main log message
   * @param subMsg  an optional sub-message or category
   */
  public void debug(@Nonnull String message, @Nullable String subMsg) {
    sendColoredMessage(LoggerLevel.DEBUG, message, subMsg);
  }

  /**
   * Send an info log message to the console
   * 
   * @param message the main log message
   */
  public void info(@Nonnull String message) {
    info(message, null);
  }

  /**
   * Send an info log message to the console
   * 
   * @param message the main log message
   * @param subMsg  an optional sub-message or category
   */
  public void info(@Nonnull String message, @Nullable String subMsg) {
    sendColoredMessage(LoggerLevel.INFO, message, subMsg);
  }

  /**
   * Send a warning log message to the console
   * 
   * @param message the main log message
   */
  public void warning(@Nonnull String message) {
    warning(message, null);
  }

  /**
   * Send a warning log message to the console
   * 
   * @param message the main log message
   * @param subMsg  an optional sub-message or category
   */
  public void warning(@Nonnull String message, @Nullable String subMsg) {
    sendColoredMessage(LoggerLevel.WARNING, message, subMsg);
  }

  /**
   * Send an error log message to the console
   * 
   * @param message the main log message
   */
  public void error(@Nonnull String message) {
    error(message, null);
  }

  /**
   * Send an error log message to the console
   * 
   * @param message the main log message
   * @param subMsg  an optional sub-message or category
   */
  public void error(@Nonnull String message, @Nullable String subMsg) {
    sendColoredMessage(LoggerLevel.ERROR, message, subMsg);
  }

  /**
   * Send a critical log message to the console
   * 
   * @param message the main log message
   */
  public void critical(@Nonnull String message) {
    critical(message, null);
  }

  /**
   * Send a critical log message to the console
   * 
   * @param message the main log message
   * @param subMsg  an optional sub-message or category
   */
  public void critical(@Nonnull String message, @Nullable String subMsg) {
    sendColoredMessage(LoggerLevel.CRITICAL, message, subMsg);
  }

  /**
   * Send a log message to the console with the specified color and optional
   * sub-message
   * 
   * @param loggerColor the color to use for the log message
   * @param message     the main log message
   * @param subMsg      an optional sub-message or category
   */
  private void sendColoredMessage(@Nonnull LoggerLevel loggerLevel, @Nonnull String message, @Nullable String subMsg) {

    if (lastLoggerLevel != null && lastLoggerLevel != loggerLevel) {
      Bukkit.getConsoleSender().sendMessage(LoggerStyle.RESET.getFormattedPrefix());
    }

    lastLoggerLevel = loggerLevel;

    String category = "";

    if (subMsg != null && !subMsg.trim().isEmpty()) {
      category = String.format("(%s)", subMsg.trim());
    }

    String formattedMessage = String.format("%s[%s%s%s] %s%s %s",
        colorKey,
        color,
        name,
        colorKey,
        LoggerStyle.fromLevel(loggerLevel).getFormattedPrefix(),
        category + LoggerStyle.RESET.getColorCode(),
        message);

    // Convert & color codes to § color codes
    String coloredMessage = ChatColor.translateAlternateColorCodes('&', formattedMessage);

    Bukkit.getConsoleSender().sendMessage(coloredMessage);
  }
}
