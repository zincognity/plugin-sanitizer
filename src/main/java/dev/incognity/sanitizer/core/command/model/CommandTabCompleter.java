package dev.incognity.sanitizer.core.command.model;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import lombok.experimental.UtilityClass;

/**
 * Utility class for command tab completion.
 * 
 * @author Incognity / 01/02/2026
 */
@UtilityClass
public final class CommandTabCompleter {

  /**
   * Get a list of player names that start with the given prefix
   * 
   * @param prefix the prefix to match
   * @return list of matching player names
   */
  public List<String> completePlayerName(String prefix) {
    String lowerPrefix = prefix.toLowerCase();

    return Bukkit.getOnlinePlayers().stream()
        .map(Player::getName)
        .filter(name -> name.toLowerCase().startsWith(lowerPrefix))
        .toList();
  }

  /**
   * Get a list of options that start with the given prefix
   * 
   * @param prefix  the prefix to match
   * @param options the list of options to filter
   * @return list of matching options
   */
  public List<String> completeFromList(String prefix, List<String> options) {
    String lowerPrefix = prefix.toLowerCase();

    return options.stream()
        .filter(option -> option.toLowerCase().startsWith(lowerPrefix))
        .toList();
  }

  /**
   * Complete with numbers in a range.
   * 
   * @param prefix The text the user has typed
   * @param min    Minimum number
   * @param max    Maximum number
   * @return List of numbers in range that match the prefix
   */
  public List<String> completeNumbers(String prefix, int min, int max) {
    List<String> completions = new ArrayList<>();
    try {
      int current = Integer.parseInt(prefix);
      for (int i = Math.max(current, min); i <= Math.min(current + 10, max); i++) {
        completions.add(String.valueOf(i));
      }
    } catch (NumberFormatException e) {
      // Si no es número válido, mostrar primeras opciones
      for (int i = min; i <= Math.min(min + 10, max); i++) {
        completions.add(String.valueOf(i));
      }
    }
    return completions;
  }

  /**
   * Complete with booleans (true/false, on/off, yes/no)
   * 
   * @param prefix The text the user has typed
   * @param format The format to use: "boolean", "onoff", "yesno"
   * @return List of boolean options
   */
  public List<String> completeBooleans(String prefix, String format) {
    List<String> options = new ArrayList<>();

    switch (format.toLowerCase()) {
      case "onoff" -> {
        options.add("on");
        options.add("off");
      }
      case "yesno" -> {
        options.add("yes");
        options.add("no");
      }
      default -> {
        options.add("true");
        options.add("false");
      }
    }
    ;

    return completeFromList(prefix, options);
  }
}
