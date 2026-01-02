package dev.incognity.sanitizer.engine.registry;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import dev.incognity.sanitizer.core.logger.model.Logger;
import dev.incognity.sanitizer.detector.interfaces.Detector;
import dev.incognity.sanitizer.detector.worm10.model.SuspiciousWorm10;
import dev.incognity.sanitizer.engine.record.ScanContext;
import dev.incognity.sanitizer.report.model.Report;
import lombok.Getter;

/**
 * Registry for managing detectors within the Sanitizer engine.
 * 
 * @author Incognity / 01/02/2026
 */
public class EngineRegistry {

  @Getter
  private final List<Detector> detectors = new ArrayList<>();

  /**
   * Constructor to initialize the EngineRegistry and add default detectors
   */
  public EngineRegistry() {
    addAll(new SuspiciousWorm10());
  }

  /**
   * Add a detector to the registry
   * 
   * @param detector the detector to add
   * @return the EngineRegistry instance
   */
  public EngineRegistry add(@Nonnull Detector detector) {
    detectors.add(detector);
    return this;
  }

  /**
   * Add multiple detectors to the registry
   * 
   * @param detectors the detectors to add
   * @return the EngineRegistry instance
   */
  public EngineRegistry addAll(@Nonnull Detector... detectors) {
    if (detectors.length == 0) {
      return this;
    }

    for (Detector detector : detectors) {
      add(detector);
    }
    return this;
  }

  /**
   * Scan the given context with all registered detectors
   * 
   * @param context the scan context
   * @return the scan report
   */
  public Report scan(ScanContext context) {
    Report report = new Report();

    Logger.info("Starting scan with " + detectors.size() + " detectors.");

    for (Detector detector : detectors) {
      detector.scan(context).ifPresent(report::add);
    }

    Logger.info("Scan completed. Found " + report.getResults().size() + " issues.");

    return report;
  }
}
