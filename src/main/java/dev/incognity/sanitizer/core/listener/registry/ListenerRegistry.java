package dev.incognity.sanitizer.core.listener.registry;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import org.bukkit.plugin.PluginManager;

import dev.incognity.sanitizer.SanitizerBukkit;
import dev.incognity.sanitizer.core.listener.interfaces.Listener;
import lombok.Getter;

/**
 * Registry for managing listeners within the Sanitizer plugin.
 * 
 * @author Incognity / 01/01/2026
 */
public class ListenerRegistry {

  private final SanitizerBukkit plugin;
  @Getter
  private final List<Listener> listeners = new ArrayList<>();
  private final Map<String, Boolean> enabledState = new HashMap<>();

  public ListenerRegistry(@Nonnull SanitizerBukkit plugin) {
    this.plugin = plugin;
    initializeDefaultListeners();
  }

  /**
   * Initialize default listeners
   */
  private void initializeDefaultListeners() {
    addAll();
  }

  /**
   * Check if a listener is active
   * 
   * @param name the name of the listener
   * @return true if the listener is active, false otherwise
   */
  public boolean isActive(String name) {
    return enabledState.getOrDefault(name, false);
  }

  /**
   * Get the total number of registered listeners
   * 
   * @return the count of registered listeners
   */
  public int getListenerCount() {
    return listeners.size();
  }

  /**
   * Get the number of active listeners
   * 
   * @return the count of active listeners
   */
  public int getActiveListenerCount() {
    return (int) listeners.stream()
        .filter(Listener::isEnabled)
        .count();
  }

  /**
   * Get the number of inactive listeners
   * 
   * @return the count of inactive listeners
   */
  public int getInactiveListenerCount() {
    return (int) listeners.stream()
        .filter(listener -> !listener.isEnabled())
        .count();
  }

  /**
   * Find a listener by its name
   * 
   * @param name the name of the listener
   * @return the Listener instance or null if not found
   */
  public Listener findByName(@Nonnull String name) {
    if (name.isEmpty()) {
      return null;
    }

    return listeners.stream()
        .filter(listener -> listener.getName().equalsIgnoreCase(name))
        .findFirst()
        .orElse(null);
  }

  /**
   * Add a listener to the registry
   * 
   * @param listener the listener to add
   * @return the ListenerRegistry instance
   */
  public ListenerRegistry add(@Nonnull Listener listener) {
    listeners.add(listener);

    return this;
  }

  /**
   * Add multiple listeners to the registry
   * 
   * @param listeners the listeners to add
   * @return the ListenerRegistry instance
   */
  public ListenerRegistry addAll(@Nonnull Listener... listeners) {
    if (listeners.length == 0) {
      return this;
    }

    for (Listener listener : listeners) {
      add(listener);
    }

    return this;
  }

  /**
   * Remove a listener from the registry
   * 
   * @param listener the listener to remove
   * @return the ListenerRegistry instance
   */
  public ListenerRegistry remove(@Nonnull Listener listener) {
    listeners.remove(listener);

    return this;
  }

  /**
   * Register all listeners with the plugin manager
   */
  public void registerAll() {
    if (listeners.isEmpty()) {
      return;
    }

    List<Listener> sortedByPriority = getListenersSortedByPriority();

    for (Listener listener : sortedByPriority) {
      registerListener(listener);
    }
  }

  /**
   * Register a listener with the plugin manager
   * 
   * @param listener the listener to register
   */
  private void registerListener(@Nonnull Listener listener) {
    try {
      PluginManager manager = plugin.getServer().getPluginManager();

      manager.registerEvents(listener, plugin);
      enabledState.put(listener.getName(), true);
    } catch (Exception e) {
      enabledState.put(listener.getName(), false);
      e.printStackTrace();
    }
  }

  /**
   * Get listeners sorted by priority
   * 
   * @return list of listeners sorted by priority
   */
  private List<Listener> getListenersSortedByPriority() {
    return listeners.stream()
        .filter(Listener::isEnabled)
        .sorted(Comparator.comparingInt(Listener::getPriority))
        .collect(Collectors.toList());
  }
}
