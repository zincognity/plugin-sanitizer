package dev.incognity.sanitizer.core.logger.enums;

import javax.annotation.Nonnull;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum representing different logger colors.
 * 
 * @author Incognity / 01/01/2026
 */
@Getter
@AllArgsConstructor
public enum LoggerStyle {
  /** Debug level - Gray color with debug emoji */
  DEBUG("&7", "👾"),

  /** Info level - Blue color with info emoji */
  INFO("&b", "ⓘ"),

  /** Warning level - Yellow color with warning emoji */
  WARNING("&e", "⚠️"),

  /** Error level - Red color with error emoji */
  ERROR("&c", "❌"),

  /** Critical level - Dark red color with fire emoji */
  CRITICAL("&4", "🚨"),

  /** Reset color - No color or emoji */
  RESET("&r", "");

  private final String colorCode;
  private final String emoji;

  /**
   * Get LoggerStyle from LoggerLevel
   * 
   * @param level the logger level
   * @return the corresponding LoggerStyle
   */
  public static LoggerStyle fromLevel(@Nonnull LoggerLevel level) {
    return switch (level) {
      case DEBUG -> DEBUG;
      case INFO -> INFO;
      case WARNING -> WARNING;
      case ERROR -> ERROR;
      case CRITICAL -> CRITICAL;
      default -> RESET;
    };
  }

  /**
   * Get formatted prefix combining emoji and color code
   * 
   * @return the formatted prefix
   */
  public String getFormattedPrefix() {
    return (hasEmoji() ? "" : emoji + " ") + colorCode;
  }

  /**
   * Check if the LoggerColor has an associated emoji
   * 
   * @return true if it has an emoji, false otherwise
   */
  public boolean hasEmoji() {
    return !emoji.isEmpty();
  }
}
