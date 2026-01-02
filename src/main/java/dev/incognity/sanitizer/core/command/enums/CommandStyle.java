package dev.incognity.sanitizer.core.command.enums;

import javax.annotation.Nonnull;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum representing different command styles.
 * 
 * @author Incognity / 01/01/2026
 */
@Getter
@AllArgsConstructor
public enum CommandStyle {
  /** Success style with green color and check mark emoji */
  SUCCESS("&a", "✔"),
  /** Error style with red color and cross mark emoji */
  ERROR("&c", "✖"),
  /** Warning style with yellow color and warning sign emoji */
  WARNING("&e", "⚠"),
  /** Info style with blue color and information emoji */
  INFO("&b", "ℹ");

  private final String colorCode;
  private final String emoji;

  public static CommandStyle fromLevel(@Nonnull CommandLevel level) {
    return switch (level) {
      case SUCCESS -> SUCCESS;
      case ERROR -> ERROR;
      case WARNING -> WARNING;
      case INFO -> INFO;
      default -> INFO;
    };
  }

  /**
   * Get formatted prefix combining emoji and color code
   * 
   * @return the formatted prefix
   */
  public String getFormattedPrefix(String colorKey) {
    return String.format("%s[%s%s%s]", colorKey, colorCode, emoji, colorKey);
  }
}
