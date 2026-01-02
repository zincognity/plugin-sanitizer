package dev.incognity.sanitizer.core.command.model;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import dev.incognity.sanitizer.core.command.enums.CommandLevel;
import dev.incognity.sanitizer.core.command.enums.CommandStyle;
import lombok.experimental.UtilityClass;

/**
 * Utility class for sending command feedback messages.
 * 
 * @author Incognity / 01/02/2026
 */
@UtilityClass
public final class CommandFeedback {

  private static String name = "Feedback";
  private static String color = "&b";
  private static String colorKey = "&b";

  /**
   * Initialize the feedback utility with a plugin instance and color
   * 
   * @param pluginInstance the plugin instance
   * @param colorInstance  the color code to use
   */
  public void initialize(@Nonnull JavaPlugin pluginInstance, @Nonnull String colorInstance) {
    initialize(pluginInstance, colorInstance, null);
  }

  /**
   * Initialize the feedback utility with a plugin instance, color, and optional
   * color key
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
  }

  /**
   * Send error feedback to a sender
   * 
   * @param sender  the command sender
   * @param message the message
   */
  public void success(@Nonnull CommandSender sender, @Nonnull String message) {
    success(sender, message, null);
  }

  /**
   * Send success feedback to a sender
   * 
   * @param sender  the command sender
   * @param message the message
   * @param title   an optional title
   */
  public void success(@Nonnull CommandSender sender, @Nonnull String message, @Nullable String title) {
    sendColoredFeedback(sender, CommandLevel.SUCCESS, message, title);
  }

  /**
   * Send error feedback to a sender
   * 
   * @param sender  the command sender
   * @param message the message
   */
  public void error(@Nonnull CommandSender sender, @Nonnull String message) {
    error(sender, message, null);
  }

  /**
   * Send error feedback to a sender
   * 
   * @param sender  the command sender
   * @param message the message
   * @param title   an optional title
   */
  public void error(@Nonnull CommandSender sender, @Nonnull String message, @Nullable String title) {
    sendColoredFeedback(sender, CommandLevel.ERROR, message, title);
  }

  /**
   * Send warning feedback to a sender
   * 
   * @param sender  the command sender
   * @param message the message
   */
  public void warning(@Nonnull CommandSender sender, @Nonnull String message) {
    warning(sender, message, null);
  }

  /**
   * Send warning feedback to a sender
   * 
   * @param sender  the command sender
   * @param message the message
   * @param title   an optional title
   */
  public void warning(@Nonnull CommandSender sender, @Nonnull String message, @Nullable String title) {
    sendColoredFeedback(sender, CommandLevel.WARNING, message, title);
  }

  /**
   * Send info feedback to a sender
   * 
   * @param sender  the command sender
   * @param message the message
   */
  public void info(@Nonnull CommandSender sender, @Nonnull String message) {
    info(sender, message, null);
  }

  /**
   * Send info feedback to a sender
   * 
   * @param sender  the command sender
   * @param message the message
   * @param title   an optional title
   */
  public void info(@Nonnull CommandSender sender, @Nonnull String message, @Nullable String title) {
    sendColoredFeedback(sender, CommandLevel.INFO, message, title);
  }

  /**
   * Send command feedback to a sender
   * 
   * @param sender       the command sender
   * @param commandLevel the command level
   * @param message      the message
   * @param subMsg       an optional sub-message
   */
  private void sendColoredFeedback(@Nonnull CommandSender sender, @Nonnull CommandLevel commandLevel,
      @Nonnull String message, @Nullable String subMsg) {

    String title = "";

    if (subMsg != null && !subMsg.trim().isEmpty()) {
      title = String.format(" &6(%s)", subMsg.trim());
    }

    CommandStyle style = CommandStyle.fromLevel(commandLevel);

    String formattedMessage = String.format("%s %s[%s%s%s]&r%s &r%s",
        style.getFormattedPrefix(colorKey),
        colorKey,
        color,
        name,
        colorKey,
        title,
        style.getColorCode() + message);

    // Convert & color codes to Bukkit ChatColor
    String coloredMessage = ChatColor.translateAlternateColorCodes('&', formattedMessage);

    if (sender instanceof Player) {
      Player player = (Player) sender;

      player.playSound(player.getLocation().clone(), style.getSound(), 1.0f, 1.2f);
    }

    sender.sendMessage(coloredMessage);
  }
}
