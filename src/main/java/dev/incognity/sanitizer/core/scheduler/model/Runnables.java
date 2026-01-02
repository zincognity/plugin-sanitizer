package dev.incognity.sanitizer.core.scheduler.model;

import java.util.concurrent.Callable;

import javax.annotation.Nonnull;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import dev.incognity.sanitizer.core.logger.model.Logger;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class Runnables {

  private static BukkitScheduler scheduler = Bukkit.getScheduler();
  private static Plugin plugin;

  /**
   * Initialize the scheduler with a plugin instance
   * 
   * @param pluginInstance the plugin instance
   */
  public void initialize(JavaPlugin pluginInstance) {
    scheduler = pluginInstance.getServer().getScheduler();
    plugin = pluginInstance;
  }

  /**
   * Run a task asynchronously
   * 
   * @param callable the task to run
   */
  public BukkitTask run(@Nonnull Callable<Void> callable) {
    return scheduler.runTask(plugin, () -> {
      try {
        callable.call();
      } catch (Exception e) {
        e.printStackTrace();
        Logger.error("An error occurred while executing a scheduled task: " + e.getMessage());
      }
    });
  }

  /**
   * Run a task asynchronously
   * 
   * @param callable the task to run
   * @return the BukkitTask instance
   */
  public BukkitTask runAsync(@Nonnull Callable<Void> callable) {
    return scheduler.runTaskAsynchronously(plugin, () -> {
      try {
        callable.call();
      } catch (Exception e) {
        e.printStackTrace();
        Logger.error("An error occurred while executing a scheduled async task: " + e.getMessage());
      }
    });
  }

  /**
   * Run a task later
   * 
   * @param callable   the task to run
   * @param delayTicks the delay in ticks
   * @return the BukkitTask instance
   */
  public BukkitTask runLater(@Nonnull Callable<Void> callable, long delayTicks) {
    return scheduler.runTaskLater(plugin, () -> {
      try {
        callable.call();
      } catch (Exception e) {
        e.printStackTrace();
        Logger.error("An error occurred while executing a scheduled async task: " + e.getMessage());
      }
    }, delayTicks);
  }

  /**
   * Run a task later asynchronously
   * 
   * @param callable   the task to run
   * @param delayTicks the delay in ticks
   * @return the BukkitTask instance
   */
  public BukkitTask runLaterAsync(@Nonnull Callable<Void> callable, long delayTicks) {
    return scheduler.runTaskLaterAsynchronously(plugin, () -> {
      try {
        callable.call();
      } catch (Exception e) {
        e.printStackTrace();
        Logger.error("An error occurred while executing a scheduled async task: " + e.getMessage());
      }
    }, delayTicks);
  }

  /**
   * Run a repeating task
   * 
   * @param callable    the task to run
   * @param delayTicks  the delay in ticks
   * @param periodTicks the period in ticks
   * @return the BukkitTask instance
   */
  public BukkitTask runTimer(@Nonnull Callable<Void> callable, long delayTicks, long periodTicks) {
    return scheduler.runTaskTimer(plugin, () -> {
      try {
        callable.call();
      } catch (Exception e) {
        e.printStackTrace();
        Logger.error("An error occurred while executing a scheduled async task: " + e.getMessage());
      }
    }, delayTicks, periodTicks);
  }

  /**
   * Run a repeating task asynchronously
   * 
   * @param callable    the task to run
   * @param delayTicks  the delay in ticks
   * @param periodTicks the period in ticks
   * @return the BukkitTask instance
   */
  public BukkitTask runTimerAsync(@Nonnull Callable<Void> callable, long delayTicks, long periodTicks) {
    return scheduler.runTaskTimerAsynchronously(plugin, () -> {
      try {
        callable.call();
      } catch (Exception e) {
        e.printStackTrace();
        Logger.error("An error occurred while executing a scheduled async task: " + e.getMessage());
      }
    }, delayTicks, periodTicks);
  }

  /**
   * Cancel a scheduled task
   * 
   * @param taskId the task ID to cancel
   */
  public void cancelTask(@Nonnull Integer taskId) {
    scheduler.cancelTask(taskId);
  }

  public void cancelTask(@Nonnull BukkitTask task) {
    if (task == null) {
      return;
    }

    scheduler.cancelTask(task.getTaskId());
  }

  /**
   * Cancel all scheduled tasks for the plugin
   */
  public void cancelAllTasks() {
    scheduler.cancelAllTasks();
  }
}
