package dev.incognity.sanitizer.core.command.model;

import java.util.List;

import javax.annotation.Nonnull;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import lombok.Getter;

/**
 * Abstract base class for custom commands in the Sanitizer plugin.
 * 
 * @author Incognity / 01/01/2026
 */
@Getter
public abstract class Command extends BukkitCommand {

  private boolean forPlayersOnly = false;
  private boolean async = false;

  public Command(@Nonnull String name) {
    super(name);
  }

  @Override
  public boolean execute(CommandSender sender, String alias, String[] arg2) {
    if (!(sender instanceof Player)) {
      if (this.forPlayersOnly) {
      }
    }

    return false;
  }

  @Override
  public String getUsage() {
    return ChatColor.translateAlternateColorCodes('&', super.getUsage());
  }

  // Feedback methods
  protected void sendSuccess(@Nonnull CommandSender sender, @Nonnull String message) {
    CommandFeedback.success(sender, message);

  }

  protected void sendSuccess(@Nonnull CommandSender sender, @Nonnull String message,
      @Nonnull String title) {
    CommandFeedback.success(sender, message, title);
  }

  protected void sendError(@Nonnull CommandSender sender, @Nonnull String message) {
    CommandFeedback.error(sender, message);

  }

  protected void sendError(@Nonnull CommandSender sender, @Nonnull String message,
      @Nonnull String title) {
    CommandFeedback.error(sender, message, title);
  }

  protected void sendWarning(@Nonnull CommandSender sender, @Nonnull String message) {
    CommandFeedback.warning(sender, message);

  }

  protected void sendWarning(@Nonnull CommandSender sender, @Nonnull String message,
      @Nonnull String title) {
    CommandFeedback.warning(sender, message, title);
  }

  protected void sendInfo(@Nonnull CommandSender sender, @Nonnull String message) {
    CommandFeedback.info(sender, message);

  }

  protected void sendInfo(@Nonnull CommandSender sender, @Nonnull String message,
      @Nonnull String title) {
    CommandFeedback.info(sender, message, title);
  }

  // Tab completion methods
  protected List<String> completePlayerName(String prefix) {
    return CommandTabCompleter.completePlayerName(prefix);
  }

  protected List<String> completeFromList(String prefix, List<String> options) {
    return CommandTabCompleter.completeFromList(prefix, options);
  }

  protected List<String> completeNumbers(String prefix, int min, int max) {
    return CommandTabCompleter.completeNumbers(prefix, min, max);
  }

  protected List<String> completeBooleans(String prefix, String format) {
    return CommandTabCompleter.completeBooleans(prefix, format);
  }
}
