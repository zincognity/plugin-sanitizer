package dev.incognity.sanitizer.detector.interfaces;

import dev.incognity.sanitizer.engine.record.ScanContext;
import dev.incognity.sanitizer.engine.record.ScanResult;

/**
 * Interface representing a detector for scanning plugins.
 * 
 * @author Incognity / 01/02/2026
 */
public interface Detector {
  ScanResult scan(ScanContext context);
}
