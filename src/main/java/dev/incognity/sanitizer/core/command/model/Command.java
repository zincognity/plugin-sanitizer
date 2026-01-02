package dev.incognity.sanitizer.core.command.model;

import java.util.List;

import javax.annotation.Nonnull;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import dev.incognity.sanitizer.core.scheduler.model.Runnables;

/**
 * Abstract base class for custom commands in the Sanitizer plugin.
 * 
 * @author Incognity / 01/01/2026
 */
public abstract class Command extends BukkitCommand {

  private boolean forPlayersOnly = false;
  private boolean async = false;
  private boolean autoValidateUsage = false;

  public Command(@Nonnull String name) {
    super(name);
  }

  /**
   * Set whether the command can only be executed by players.
   * 
   * @param sender the command sender
   * @return the name of the sender
   */
  public String getSenderName(CommandSender sender) {
    if (sender instanceof Player) {
      return ((Player) sender).getName();
    } else {
      return "Console";
    }
  }

  @Override
  public boolean execute(CommandSender sender, String alias, String[] args) {
    if (!(sender instanceof Player)) {
      if (this.forPlayersOnly) {
        sendError(sender, "This command can only be executed by players.");
        return true;
      }

      if (this.async) {
        Runnables.runAsync(() -> {
          execute(sender, args, alias);
          return null;
        });
      } else {
        execute(sender, args, alias);
      }
    }

    Player player = (Player) sender;

    if (!isSenderPermitted(player, getPermission())) {
      sendError(player, "You do not have permission to execute this command.");
      return false;
    }

    if (!isValidArgumentsSize(player, args)) {
      sendError(player, "Invalid command usage. Correct usage: " + getUsage());
      return false;
    }

    if (this.async) {
      Runnables.runAsync(() -> {
        execute(player, args, alias);
        return null;
      });
    } else {
      execute(player, args, alias);
    }

    return true;
  }

  /**
   * Execute the command logic.
   * 
   * @param sender the command sender
   * @param args   the command arguments
   * @param alias  the command alias used
   */
  public void execute(CommandSender sender, String[] args, String alias) {
  }

  /**
   * Check if the sender has the required permission.
   * 
   * @param sender     the command sender
   * @param permission the required permission
   * @return true if the sender has permission, false otherwise
   */
  private boolean isSenderPermitted(CommandSender sender, String permission) {
    if (!(sender instanceof Player)) {
      return true;
    }

    if (permission == null || permission.isEmpty()) {
      return true;
    }

    return sender.hasPermission(permission);
  }

  /**
   * Validate the size of command arguments.
   * 
   * @param sender the command sender
   * @param args   the command arguments
   * @return true if the arguments size is valid, false otherwise
   */
  private boolean isValidArgumentsSize(CommandSender sender, String[] args) {
    if (!autoValidateUsage) {
      return true;
    }

    String usage = getUsage().trim();

    int requiredArgsCount = countRequiredArguments(usage);

    return CommandValidator.isValidArgumentsSize(args, 0, requiredArgsCount);
  }

  /**
   * Count the number of required arguments from the usage string.
   * <required> = 1 required
   * [optional] = 0 required
   * <arg1> <arg2> = 2 required
   * 
   * If no brackets found, counts space-separated words as arguments
   * 
   * @param usage the usage string
   * @return the count of required arguments
   */
  private int countRequiredArguments(String usage) {
    if (usage == null || usage.trim().isEmpty()) {
      return 0;
    }

    int count = 0;
    boolean hasBrackets = usage.contains("<") || usage.contains("[");

    // If usage has brackets, count only <required> ones
    if (hasBrackets) {
      int i = 0;
      while (i < usage.length()) {
        if (usage.charAt(i) == '<') {
          count++;
          while (i < usage.length() && usage.charAt(i) != '>') {
            i++;
          }
        }
        i++;
      }
    } else {
      // No brackets: count space-separated words
      String[] parts = usage.trim().split("\\s+");
      count = parts.length > 0 && !parts[0].isEmpty() ? parts.length : 0;
    }

    return count;
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

  // Getters and Setters
  public boolean isForPlayersOnly() {
    return this.forPlayersOnly;
  }

  public void setForPlayersOnly(boolean forPlayersOnly) {
    this.forPlayersOnly = forPlayersOnly;
  }

  public boolean isAsync() {
    return this.async;
  }

  public void setAsync(boolean async) {
    this.async = async;
  }

  public boolean isAutoValidateUsage() {
    return this.autoValidateUsage;
  }

  public void setAutoValidateUsage(boolean autoValidateUsage) {
    this.autoValidateUsage = autoValidateUsage;
  }
}