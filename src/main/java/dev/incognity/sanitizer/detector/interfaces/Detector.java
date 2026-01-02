package dev.incognity.sanitizer.detector.interfaces;

import java.util.Optional;

import dev.incognity.sanitizer.engine.record.ScanContext;
import dev.incognity.sanitizer.engine.record.ScanResult;

/**
 * Interface representing a detector for scanning plugins.
 * 
 * @author Incognity / 01/02/2026
 */
public interface Detector {
  /**
   * Get the name of the detector
   * 
   * @return the name of the detector
   */
  String getName();

  /**
   * Get the description of the detector
   * 
   * @return the description of the detector
   */
  String getDescription();

  /**
   * Scan the given context and return a scan result
   * 
   * @param context the scan context
   * @return the scan result
   */
  Optional<ScanResult> scan(ScanContext context);
}
