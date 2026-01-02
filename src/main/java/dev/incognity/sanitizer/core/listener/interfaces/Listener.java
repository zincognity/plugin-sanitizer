package dev.incognity.sanitizer.core.listener.interfaces;

public interface Listener extends org.bukkit.event.Listener {

  /**
   * Get the name of the listener
   * 
   * @return name of the listener
   */
  String getName();

  /**
   * Get the description of the listener
   * 
   * @return description of the listener
   */
  String getDescription();

  /**
   * Get the priority of the listener
   * 
   * @return priority of the listener
   */
  default int getPriority() {
    return 0;
  }

  /**
   * Check if the listener is enabled
   * 
   * @return true if enabled, false otherwise
   */
  default boolean isEnabled() {
    return true;
  }
}
