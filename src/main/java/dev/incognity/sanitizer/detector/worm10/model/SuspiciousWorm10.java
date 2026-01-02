package dev.incognity.sanitizer.detector.worm10.model;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import dev.incognity.sanitizer.core.logger.model.Logger;
import dev.incognity.sanitizer.detector.interfaces.Detector;
import dev.incognity.sanitizer.engine.record.ScanContext;
import dev.incognity.sanitizer.engine.record.ScanResult;
import dev.incognity.sanitizer.severity.enums.Severity;

/**
 * Detector for Suspicious Worm 10 malware.
 * 
 * @author Incognity / 01/02/2026
 */
public class SuspiciousWorm10 implements Detector {

  @Override
  public String getName() {
    return "SuspiciousWorm10";
  }

  @Override
  public String getDescription() {
    return "Detects Suspicious Worm 10 malware patterns.";
  }

  @Override
  public Optional<ScanResult> scan(ScanContext context) {

    boolean found = false;
    int maxHits = 3;
    int hits = 0;

    for (File file : context.pluginsDirectory().listFiles()) {
      Logger.debug("Scanning file: " + file.getName());
      if (!file.getName().toLowerCase().endsWith(".jar")) {
        continue;
      }

      try (JarFile jarFile = new JarFile(file)) {
        Logger.debug("Opened JAR file: " + file.getName());
        for (JarEntry entry : jarFile.stream().toList()) {
          Logger.debug("Checking JAR entry: " + entry.getName());
          if (entry.getName().endsWith("L10.class")) {
            hits++;
            continue;
          }

          if (".l_ignore".equalsIgnoreCase(entry.getName())) {
            hits++;
            continue;
          }

          if (".l1".equalsIgnoreCase(entry.getName())) {
            hits++;
            continue;
          }

          if (hits >= maxHits) {
            found = true;
            break;
          }
        }

        if (found) {
          break;
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    Logger.debug("SuspiciousWorm10 detection result: " + found);

    if (found) {
      return Optional.of(
          new ScanResult(getName(), Severity.HIGH,
              "Suspicious Worm 10 malware pattern found."));
    }

    return Optional.empty();
  }
}
