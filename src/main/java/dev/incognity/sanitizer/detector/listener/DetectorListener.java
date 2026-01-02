package dev.incognity.sanitizer.detector.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

import dev.incognity.sanitizer.SanitizerBukkit;
import dev.incognity.sanitizer.core.listener.interfaces.Listener;
import dev.incognity.sanitizer.engine.record.ScanContext;
import dev.incognity.sanitizer.engine.record.ScanResult;
import dev.incognity.sanitizer.report.model.Report;

/**
 * Listener for detector events.
 */
public class DetectorListener implements Listener {

  @Override
  public String getName() {
    return "DetectorListener";
  }

  @Override
  public String getDescription() {
    return "Listener for handling detector-related events.";
  }

  public void onPlayerJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();

    SanitizerBukkit plugin = SanitizerBukkit.plugin;

    Report report = plugin.getEngineRegistry().scan(new ScanContext(plugin.getDataFolder()));

    for (ScanResult result : report.getResults()) {
      player.sendMessage("Vulnerabilidad detectada " + result.detectorName() + " " + result.description());
    }
  }
}
