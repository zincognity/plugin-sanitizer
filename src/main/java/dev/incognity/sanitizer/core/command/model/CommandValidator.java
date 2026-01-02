package dev.incognity.sanitizer.core.command.model;

import org.bukkit.Bukkit;

import lombok.experimental.UtilityClass;

/**
 * Utility class for command validation.
 * 
 * @author Incognity / 01/02/2026
 */
@UtilityClass
public final class CommandValidator {

  /**
   * Validate the size of command arguments.
   * 
   * @param args            the command arguments
   * @param minExpectedSize the minimum expected size
   * @param maxExpectedSize the maximum expected size (-1 for no maximum)
   * @return true if the size is valid, false otherwise
   */
  public boolean isValidArgumentsSize(String[] args, int minExpectedSize, int maxExpectedSize) {
    if (args.length < minExpectedSize) {
      return false;
    }

    if (maxExpectedSize != -1 && args.length > maxExpectedSize) {
      return false;
    }

    return true;
  }

  /**
   * Validate the length of a string argument.
   * 
   * @param arg               the argument to validate
   * @param minExpectedLength the minimum expected length
   * @param maxExpectedLength the maximum expected length
   * @return true if the length is valid, false otherwise
   */
  public boolean isValidLength(String arg, int minExpectedLength, int maxExpectedLength) {
    if (arg.length() < minExpectedLength) {
      return false;
    }

    if (arg.length() > maxExpectedLength) {
      return false;
    }

    return true;
  }

  /**
   * Validate if the argument is a valid integer.
   * 
   * @param arg the argument to validate
   * @return true if the argument is a valid integer, false otherwise
   */
  public boolean isValidInteger(String arg) {
    try {
      Integer.parseInt(arg);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  /**
   * Validate if the argument is a valid double.
   * 
   * @param arg the argument to validate
   * @return true if the argument is a valid double, false otherwise
   */
  public boolean isValidDouble(String arg) {
    try {
      Double.parseDouble(arg);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  /**
   * Validate if the argument is within the specified integer range.
   * 
   * @param arg         the argument to validate
   * @param minExpected the minimum expected value
   * @param maxExpected the maximum expected value
   * @return true if the argument is within the range, false otherwise
   */
  public boolean isValidRange(String arg, int minExpected, int maxExpected) {
    if (!isValidInteger(arg)) {
      return false;
    }

    int value = Integer.parseInt(arg);

    return value >= minExpected && value <= maxExpected;
  }

  /**
   * Validate if the player with the given name is online.
   * 
   * @param expectedPlayer the name of the player to validate
   * @return true if the player is online, false otherwise
   */
  public boolean isValidPlayer(String expectedPlayer) {
    return Bukkit.getPlayer(expectedPlayer) != null;
  }

  /**
   * Validate if the string is non-null and non-empty.
   * 
   * @param text the string to validate
   * @return true if the string is valid, false otherwise
   */
  public boolean isValidString(String text) {
    return text != null && !text.trim().isEmpty();
  }

  /**
   * Validate if the text matches the given regex pattern.
   * 
   * @param text    the string to validate
   * @param pattern the regex pattern to match
   * @return true if the text matches the pattern, false otherwise
   */
  public boolean isMatchPattern(String text, String pattern) {
    try {
      return text.matches(pattern);
    } catch (Exception e) {
      return false;
    }
  }
}
