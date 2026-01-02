package dev.incognity.sanitizer.core.command.model;

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
}
