package dev.incognity.sanitizer.detector.worm10.model;

import java.io.File;
import java.io.IOException;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import dev.incognity.sanitizer.detector.interfaces.Detector;
import dev.incognity.sanitizer.engine.record.ScanContext;
import dev.incognity.sanitizer.engine.record.ScanResult;

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
  public ScanResult scan(ScanContext context) {

    boolean found = false;

    for (File file : context.pluginsDirectory().listFiles()) {

      try (JarFile jarFile = new JarFile(file)) {

        for (JarEntry entry : jarFile.stream().toList()) {
          if (entry.getName().endsWith("10.class")) {
            found = true;
          }

          break;
        }

        if (found) {
          break;
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return null;
  }
}
