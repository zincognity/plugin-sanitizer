package dev.incognity.sanitizer.core.command.enums;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import org.bukkit.Sound;

import dev.incognity.sanitizer.core.sound.utils.SoundResolver;
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
  SUCCESS("&a", "✔", SoundResolver::success),
  /** Error style with red color and cross mark emoji */
  ERROR("&c", "✖", SoundResolver::error),
  /** Warning style with yellow color and warning sign emoji */
  WARNING("&e", "⚠", SoundResolver::warning),
  /** Info style with blue color and information emoji */
  INFO("&b", "ℹ", SoundResolver::info);

  private final String colorCode;
  private final String emoji;
  private final Supplier<Sound> sound;

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

  /**
   * Get the associated sound for the command style
   * 
   * @return the sound
   */
  public Sound getSound() {
    return sound.get();
  }
}
