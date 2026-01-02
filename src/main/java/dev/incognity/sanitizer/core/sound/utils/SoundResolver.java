package dev.incognity.sanitizer.core.sound.utils;

import org.bukkit.Sound;

import lombok.experimental.UtilityClass;

/**
 * Utility class for resolving sound names across different Minecraft versions.
 * 
 * @author Incognity / 01/02/2026
 */
@UtilityClass
public final class SoundResolver {

  public Sound success() {
    return resolve("ENTITY_PLAYER_LEVELUP", "LEVEL_UP");
  }

  public Sound error() {
    return resolve("ENTITY_PLAYER_ATTACK_STRONG", "IRONGOLEM_HIT");
  }

  public Sound warning() {
    return resolve("BLOCK_NOTE_BLOCK_BELL", "NOTE_PLING");
  }

  public Sound info() {
    return resolve("BLOCK_NOTE_BLOCK_PLING", "CLICK");
  }

  private Sound resolve(String modern, String legacy) {
    try {
      return Sound.valueOf(modern);
    } catch (IllegalArgumentException ignored) {
      try {
        return Sound.valueOf(legacy);
      } catch (IllegalArgumentException e) {
        return null;
      }
    }
  }
}